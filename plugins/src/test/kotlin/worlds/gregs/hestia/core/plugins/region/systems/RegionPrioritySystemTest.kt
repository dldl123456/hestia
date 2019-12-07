package worlds.gregs.hestia.core.plugins.region.systems

import com.artemis.WorldConfigurationBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import worlds.gregs.hestia.artemis.events.CreateRegion
import worlds.gregs.hestia.core.archetypes.RegionFactory
import worlds.gregs.hestia.core.plugins.region.components.RegionPriorities
import worlds.gregs.hestia.core.plugins.region.systems.load.RegionCreation
import worlds.gregs.hestia.game.GameTest
import worlds.gregs.hestia.game.archetypes.EntityFactory
import worlds.gregs.hestia.game.entity.components.Position.Companion.regionId
import worlds.gregs.hestia.services.getComponent
import worlds.gregs.hestia.services.getSystem

internal class RegionPrioritySystemTest : GameTest(WorldConfigurationBuilder().with(RegionPrioritySystem(), RegionCreation(), RegionsSystem())) {


    @BeforeEach
    override fun setup() {
        super.setup()
        EntityFactory.add(RegionFactory())
        es.dispatch(CreateRegion(regionId(0, 0)))
        tick()
    }

    @Test
    fun add() {
        val system = world.getSystem(RegionPrioritySystem::class)
        system.add(regionId(0, 0))
        val region = world.getEntity(0)
        assertThat(region.getComponent(RegionPriorities::class)?.priority ?: -1).isEqualTo(1)
    }

    @Test
    fun remove() {
        val system = world.getSystem(RegionPrioritySystem::class)
        val regionId = regionId(0, 0)
        system.add(regionId)
        tick()
        val region = world.getEntity(0)
        val priorities = region.getComponent(RegionPriorities::class)!!
        priorities.priority = 2
        //Deduction
        system.remove(regionId)
        assertThat(priorities.priority).isEqualTo(1)
        //Removal
        system.remove(regionId)
        assertThat( region.getComponent(RegionPriorities::class)).isNull()
    }
}