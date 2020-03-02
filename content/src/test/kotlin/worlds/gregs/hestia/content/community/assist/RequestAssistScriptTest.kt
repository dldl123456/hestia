package worlds.gregs.hestia.content.community.assist

import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.jupiter.api.Test
import worlds.gregs.hestia.content.activity.skill.Experience
import worlds.gregs.hestia.content.activity.skill.Skill
import worlds.gregs.hestia.core.action.model.EntityAction
import worlds.gregs.hestia.core.display.client.model.events.Chat
import worlds.gregs.hestia.core.display.dialogue.model.ChatType
import worlds.gregs.hestia.core.display.interfaces.api.Interfaces
import worlds.gregs.hestia.core.display.interfaces.model.PlayerOptions.ASSIST
import worlds.gregs.hestia.core.display.interfaces.model.events.InterfaceInteraction
import worlds.gregs.hestia.core.display.interfaces.model.events.PlayerOption
import worlds.gregs.hestia.core.display.interfaces.model.events.request.OpenInterface
import worlds.gregs.hestia.core.display.request.logic.RequestSystem
import worlds.gregs.hestia.core.display.request.model.Request
import worlds.gregs.hestia.core.display.request.model.components.Assistance
import worlds.gregs.hestia.core.display.request.model.components.Assisting
import worlds.gregs.hestia.core.display.request.model.events.AcceptedRequest
import worlds.gregs.hestia.core.display.request.model.events.RequestResponse
import worlds.gregs.hestia.core.display.update.model.components.DisplayName
import worlds.gregs.hestia.core.display.variable.api.Variables
import worlds.gregs.hestia.core.display.variable.model.events.SendVariable
import worlds.gregs.hestia.core.display.variable.model.events.SetVariable
import worlds.gregs.hestia.core.display.variable.model.events.ToggleVariable
import worlds.gregs.hestia.core.entity.entity.model.components.Position
import worlds.gregs.hestia.core.entity.entity.model.events.Animation
import worlds.gregs.hestia.core.entity.entity.model.events.Graphic
import worlds.gregs.hestia.core.task.model.await.Ticks
import worlds.gregs.hestia.core.task.model.await.WithinRange
import worlds.gregs.hestia.core.world.movement.model.MovementType
import worlds.gregs.hestia.core.world.movement.model.components.types.Movement
import worlds.gregs.hestia.core.world.movement.model.events.Moved
import worlds.gregs.hestia.game.Engine
import worlds.gregs.hestia.network.client.encoders.messages.InterfaceVisibility
import worlds.gregs.hestia.script.ScriptTester
import java.util.concurrent.TimeUnit

internal class RequestAssistScriptTest : ScriptTester<RequestAssist>() {

    val targetId = 0

    @RelaxedMockK
    lateinit var variables: Variables

    @Test
    fun `Can't request if too soon since last request`() {
        //Given
        val action = mockAction<PlayerOption>()
        every { action.option } returns ASSIST
        every { action.target } returns targetId
        action.mockTask()
        val assisting = mockk<Assisting>(relaxed = true)
        with(action) {
            every { any<Int>().get(Assisting::class) } returns assisting
        }
        Engine.ticks = 10
        every { assisting.lastRequest } returns 5
        val script = spyk(script, recordPrivateCalls = true)
        //When
        send(PlayerOption(0, ASSIST))
        //Then
        verify(exactly = 0) {
            script["update"](any<EntityAction>(), assisting)
            world.getSystem(RequestSystem::class.java)
        }
    }

    @Test
    fun `Can't request if target max assist`() {
        //Given
        val assisting = mockk<Assisting>(relaxed = true)
        val targetAssisting = mockk<Assisting>(relaxed = true)
        val action = mockAction<PlayerOption>()
        every { action.option } returns ASSIST
        every { action.target } returns targetId
        action.mockTask()
        with(action) {
            every { entityId.get(Assisting::class) } returns assisting
            every { targetId.get(Assisting::class) } returns targetAssisting
            every { any<Int>().get(DisplayName::class) } returns mockk(relaxed = true)
        }
        every { assisting.lastRequest } returns 5
        Engine.ticks = 15
        setSystem(Variables::class, variables)
        every { variables.get(targetId, "total_xp_earned", 0) } returns 30000
        //When
        send(action)
        //Then
        verify {
            variables.get(targetId, "total_xp_earned", 0)
        }
        verify(exactly = 0) { world.getSystem(RequestSystem::class.java) }
    }

    @Test
    fun `Request sent`() {
        //Given
        val assisting = mockk<Assisting>(relaxed = true)
        val targetAssisting = mockk<Assisting>(relaxed = true)
        val action = mockAction<PlayerOption>()
        every { action.option } returns ASSIST
        every { action.target } returns targetId
        action.mockTask()
        val req = mockk<RequestSystem>(relaxed = true)
        with(action) {
            every { entityId.get(Assisting::class) } returns assisting
            every { targetId.get(Assisting::class) } returns targetAssisting
            every { system(RequestSystem::class) } returns req//FIXME one or the other
        }
        every { assisting.lastRequest } returns 5
        Engine.ticks = 15
        setSystem(Variables::class, variables)
        every { variables.get(targetId, "total_xp_earned", 0) } returns 0
        val map = getMapper(DisplayName::class)
        every { map.get(targetId) } returns mockk<DisplayName>(relaxed = true).apply { name = "Name" }
        with(task) {
            coEvery { await(WithinRange(targetId, 1)) } answers { true }
        }
        setSystem(RequestSystem::class, req)//FIXME one or the other
        //When
        send(action)
        //Then
        with(task) {
            coVerify { await(WithinRange(targetId, 1)) }
        }
        verify {
            req.sendRequest(entityId, targetId, Request.ASSIST)
            assisting.lastRequest = any()
        }
    }

    @Test
    fun `Request accepted`() {
        //Given
        val assist = mockk<Assistance>(relaxed = true)
        val action = mockAction<RequestResponse>()
        every { action.request } returns Request.ASSIST
        every { action.target } returns targetId
        action.mockTask()
        mockkStatic("worlds.gregs.hestia.artemis.ExtensionFunctionsKt")
        with(action) {
            every { entityId.create(Assistance::class) } returns assist
            every { targetId.get(DisplayName::class) } returns mockk(relaxed = true)
            every { targetId.get(Position::class) } returns mockk(relaxed = true)
        }
        with(task) {
            coEvery { await(any<Ticks>()) } answers {}
        }
        //When
        send(action)
        //Then
        with(action) {
            verify { entityId.send(InterfaceVisibility(Interfaces.AreaStatusIcon, 2, false)) }
        }
        with(task) {
            coVerify { await(Ticks(2)) }
        }
        with(action) {
            verify {
                entityId.perform(Animation(7299))
            }
        }
    }

    @Test
    fun `Response accepted`() {
        //Given
        val action = mockAction<AcceptedRequest>()
        every { action.request } returns Request.ASSIST
        every { action.target } returns targetId
        action.mockTask()
        with(action) {
            every { entityId.get(Assisting::class) } returns mockk(relaxed = true)
            every { targetId.get(DisplayName::class) } returns mockk(relaxed = true)
            every { targetId.get(Position::class) } returns mockk(relaxed = true)
        }
        with(task) {
            coEvery { await(any<Ticks>()) } answers {}
        }
        //When
        send(action)
        //Then
        with(action) {
            verify {
                entityId.perform(any<Chat>())
                entityId.perform(OpenInterface(Interfaces.AssistXP))
                entityId.send(InterfaceVisibility(Interfaces.AreaStatusIcon, 2, false))
                entityId perform SendVariable("total_xp_earned")
                entityId.perform(Animation(7299))
                entityId.perform(Graphic(1247))
            }
        }
    }

    @Test
    fun `Interface interaction`() {
        //Given
        val action = mockAction<InterfaceInteraction>()
        every { action.id } returns Interfaces.AssistXP
        every { action.component } returns 82
        //When
        send(action)
        //Then
        with(action) {
            verify {
                entityId.perform(ToggleVariable("assist_toggle_8"))
            }
        }
    }

    @Test
    fun `Invalid interface interaction`() {
        //Given
        val action = mockAction<InterfaceInteraction>()
        every { action.id } returns Interfaces.AssistXP
        every { action.component } returns 83
        //When
        send(action)
        //Then
        with(action) {
            verify(exactly = 0) { entityId.create(Assisting::class) }
        }
    }

    @Test
    fun `Within range`() {
        //Given
        val action = mockAction<Moved>()
        val assistance = mockk<Assistance>(relaxed = true)
        val movement = mockk<Movement>(relaxed = true)
        with(action) {
            every { entityId.has(Assistance::class) } returns true
            every { entityId.get(Assistance::class)} returns assistance
            every { entityId.get(Movement::class)} returns movement
            every { entityId.get(Position::class)} returns Position(15, 15, 0)
            every { any<Int>().get(DisplayName::class)} returns mockk(relaxed = true)
        }
        every { assistance.point } returns Position(10, 10, 0)
        every { movement.actual } returns MovementType.Walk
        //When
        send(action)
        //Then
        with(action) {
            verify(exactly = 0) { entityId.remove(Assistance::class) }
        }
    }

    @Test
    fun `Exact range`() {
        //Given
        val action = mockAction<Moved>()
        val assistance = mockk<Assistance>(relaxed = true)
        val movement = mockk<Movement>(relaxed = true)
        with(action) {
            every { entityId.has(Assistance::class) } returns true
            every { entityId.get(Assistance::class)} returns assistance
            every { entityId.get(Movement::class)} returns movement
            every { entityId.get(Position::class)} returns Position(30, 10, 0)
            every { any<Int>().get(DisplayName::class)} returns mockk(relaxed = true)
        }
        every { assistance.point } returns Position(10, 10, 0)
        every { movement.actual } returns MovementType.Walk
        //When
        send(action)
        //Then
        with(action) {
            verify(exactly = 0) { entityId.remove(Assistance::class) }
        }
    }

    @Test
    fun `Outside of range cancels`() {
        //Given
        val action = mockAction<Moved>()
        val assistance = mockk<Assistance>(relaxed = true)
        val movement = mockk<Movement>(relaxed = true)
        with(action) {
            every { entityId.has(Assistance::class) } returns true
            every { entityId.get(Assistance::class)} returns assistance
            every { entityId.get(Movement::class)} returns movement
            every { entityId.get(Position::class)} returns Position(35, 10, 0)
            every { any<Int>().get(DisplayName::class)} returns mockk(relaxed = true)
        }
        every { assistance.point } returns Position(10, 10, 0)
        every { movement.actual } returns MovementType.Walk
        //When
        send(action)
        //Then
        with(action) {
            verify { entityId.remove(Assistance::class) }
        }
    }

    @Test
    fun `Teleport moves range point`() {
        //Given
        val action = mockAction<Moved>()
        val assistance = mockk<Assistance>(relaxed = true)
        val movement = mockk<Movement>(relaxed = true)
        val position = Position(50, 50, 0)
        with(action) {
            every { entityId.has(Assistance::class) } returns true
            every { entityId.get(Assistance::class)} returns assistance
            every { entityId.get(Movement::class)} returns movement
            every { entityId.get(Position::class)} returns position
            every { any<Int>().get(DisplayName::class)} returns mockk(relaxed = true)
        }
        val point = spyk(Position(10, 10, 0))
        every { assistance.point } returns point
        every { movement.actual } returns MovementType.Move
        //When
        send(action)
        //Then
        with(action) {
            verify { point.set(position) }
            verify(exactly = 0) { entityId.remove(Assistance::class) }
        }
    }

    @Test
    fun `Xp time greater than max`() {
        //Given
        val hours = 4L
        val action = mockAction<InterfaceInteraction>()
        every { action.id } returns Interfaces.FilterButtons
        every { action.component } returns 16
        every { action.option } returns 9
        val assisting = mockk<Assisting>(relaxed = true)
        setSystem(Variables::class, variables)
        every { variables.get(entityId, "total_xp_earned", 0) } returns 35000
        every { assisting.timeout } returns System.currentTimeMillis() - TimeUnit.HOURS.toMillis(hours)
        with(action) {
            every { entityId.get(Assisting::class) } returns assisting
        }
        //When
        send(action)
        //Then
        with(action) {
            verify {
                entityId perform Chat("You've earned the maximum XP (30,000 Xp) from the Assist System within a 24-hour period.", ChatType.GameAssist)
                entityId perform Chat("You can assist again in $hours hours.", ChatType.GameAssist)
            }
        }
    }

    @Test
    fun `Xp time exactly max`() {
        //Given
        val hours = 4L
        val action = mockAction<InterfaceInteraction>()
        every { action.id } returns Interfaces.FilterButtons
        every { action.component } returns 16
        every { action.option } returns 9
        val assisting = mockk<Assisting>(relaxed = true)
        setSystem(Variables::class, variables)
        every { variables.get(entityId, "total_xp_earned", 0) } returns 30000
        every { assisting.timeout } returns System.currentTimeMillis() - TimeUnit.HOURS.toMillis(hours)
        with(action) {
            every { entityId.get(Assisting::class) } returns assisting
        }
        //When
        send(action)
        //Then
        with(action) {
            verify {
                entityId.perform(Chat("You've earned the maximum XP (30,000 Xp) from the Assist System within a 24-hour period.", ChatType.GameAssist))
                entityId.perform(Chat("You can assist again in $hours hours.", ChatType.GameAssist))
            }
        }
    }

    @Test
    fun `Xp time less than max`() {
        //Given
        val hours = 4
        val action = mockAction<InterfaceInteraction>()
        every { action.id } returns Interfaces.FilterButtons
        every { action.component } returns 16
        every { action.option } returns 9
        val assisting = mockk<Assisting>(relaxed = true)
        setSystem(Variables::class, variables)
        every { variables.get(entityId, "total_xp_earned", 0) } returns 10000
        with(action) {
            every { entityId.get(Assisting::class) } returns assisting
        }
        //When
        send(action)
        //Then
        with(action) {
            verify {
                entityId.perform(Chat("You have earned 10000 Xp. The Assist system is available to you.", ChatType.GameAssist))
            }
        }
    }

    @Test
    fun `Intercepts xp if assisting, skill active and not over max`() {
        //Given
        val skill = Skill.CRAFTING
        val increase = 500
        val action = mockAction<Experience>()
        every { action.skill } returns skill
        every { action.increase } returns increase
        val assistance = mockk<Assistance>(relaxed = true)
        assistance.helper = targetId
        val assisting = mockk<Assisting>(relaxed = true)
        setSystem(Variables::class, variables)
        every { variables.get(targetId, "assist_toggle_1", false) } returns true
        every { variables.get(targetId, "total_xp_earned", 0) } returns 1000
        with(action) {
            every { entityId.has(Assistance::class) } returns true
            every { entityId.get(Assistance::class) } returns assistance
            every { targetId.get(Assisting::class) } returns assisting
        }
        //When
        send(action)
        //Then
        with(action) {
            verify {
                targetId perform Experience(skill, increase)
                targetId perform SetVariable("total_xp_earned", 6000)//1000 + 500 * 10
            }
        }
    }

    @Test
    fun `Doesn't intercept xp if skill not active`() {
        //Given
        val skill = Skill.CRAFTING
        val increase = 500
        val action = mockAction<Experience>()
        every { action.skill } returns skill
        every { action.increase } returns increase
        val assistance = mockk<Assistance>(relaxed = true)
        assistance.helper = targetId
        val assisting = mockk<Assisting>(relaxed = true)
        setSystem(Variables::class, variables)
        every { variables.get(targetId, "assist_toggle_1", false) } returns false
        every { variables.get(targetId, "total_xp_earned", 0) } returns 1000
        with(action) {
            every { entityId.has(Assistance::class) } returns true
            every { entityId.get(Assistance::class) } returns assistance
            every { targetId.get(Assisting::class) } returns assisting
        }
        //When
        send(action)
        //Then
        with(action) {
            verify(exactly = 0) {
                targetId perform Experience(skill, increase)
                targetId perform SetVariable("total_xp_earned", 6000)
            }
        }
    }

    @Test
    fun `Doesn't intercept xp if not assisting`() {
        //Given
        val skill = Skill.CRAFTING
        val increase = 500
        val action = mockAction<Experience>()
        with(action) {
            every { entityId.has(Assistance::class) } returns false
        }
        //When
        send(action)
        //Then
        with(action) {
            verify(exactly = 0) {
                targetId perform Experience(skill, increase)
                targetId perform SetVariable("total_xp_earned", 5000)
            }
        }
    }

    @Test
    fun `Doesn't intercept xp if over max`() {
        //Given
        val skill = Skill.CRAFTING
        val increase = 500
        val action = mockAction<Experience>()
        every { action.skill } returns skill
        every { action.increase } returns increase
        val assistance = mockk<Assistance>(relaxed = true)
        assistance.helper = targetId
        val assisting = mockk<Assisting>(relaxed = true)
        setSystem(Variables::class.java, variables)
        every { variables.get(targetId, "assist_toggle_1", false) } returns true
        every { variables.get(targetId, "total_xp_earned", 0) } returns 35000
        with(action) {
            every { entityId.has(Assistance::class) } returns true
            every { entityId.get(Assistance::class) } returns assistance
            every { targetId.get(Assisting::class) } returns assisting
        }
        //When
        send(action)
        //Then
        with(action) {
            verify(exactly = 0) {
                targetId perform Experience(skill, increase)
                targetId perform SetVariable("total_xp_earned", 40000)
            }
        }
    }

}