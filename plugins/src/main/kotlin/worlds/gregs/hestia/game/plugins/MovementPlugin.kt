package worlds.gregs.hestia.game.plugins

import com.artemis.WorldConfigurationBuilder
import worlds.gregs.hestia.game.plugin.Plugin
import worlds.gregs.hestia.game.plugin.Plugin.Companion.MOBILE_PRIORITY
import worlds.gregs.hestia.game.plugin.Plugin.Companion.MOVE_PRIORITY
import worlds.gregs.hestia.game.plugin.Plugin.Companion.NAVIGATION_PRIORITY
import worlds.gregs.hestia.game.plugin.Plugin.Companion.RUN_PRIORITY
import worlds.gregs.hestia.game.plugin.Plugin.Companion.SHIFT_PRIORITY
import worlds.gregs.hestia.game.plugin.Plugin.Companion.WALK_PRIORITY
import worlds.gregs.hestia.game.systems.movement.*

class MovementPlugin : Plugin {

    override fun setup(b: WorldConfigurationBuilder) {
        //Movement
        b.with(MOBILE_PRIORITY, MobileSystem())
        b.with(MOVE_PRIORITY, MoveSystem())
        b.with(NAVIGATION_PRIORITY, NavigationSystem())
        b.with(NAVIGATION_PRIORITY, InteractionSystem())
        b.with(WALK_PRIORITY, WalkSystem())
        b.with(RUN_PRIORITY, RunSystem())
        b.with(SHIFT_PRIORITY, PositionShiftSystem())
    }

}