package worlds.gregs.hestia.content.interaction.player

import worlds.gregs.hestia.core.display.window.model.PlayerOptions.FOLLOW
import worlds.gregs.hestia.core.display.window.model.events.PlayerOption
import worlds.gregs.hestia.core.world.movement.model.events.Follow

on<PlayerOption> {
    where { option == FOLLOW }
    fun PlayerOption.task() = queue(TaskPriority.High) {
        entity perform Follow(target)
    }
    then(PlayerOption::task)
}