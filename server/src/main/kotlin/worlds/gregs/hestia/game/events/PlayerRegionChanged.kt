package worlds.gregs.hestia.game.events

import net.mostlyoriginal.api.event.common.Event

data class PlayerRegionChanged(val entityId: Int, val from: Int?, val to: Int?) : Event