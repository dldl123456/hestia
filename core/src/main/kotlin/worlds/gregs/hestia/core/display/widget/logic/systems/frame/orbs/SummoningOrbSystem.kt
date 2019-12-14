package worlds.gregs.hestia.core.display.widget.logic.systems.frame.orbs

import worlds.gregs.hestia.core.display.widget.model.components.frame.orbs.SummoningOrb
import worlds.gregs.hestia.core.display.widget.logic.systems.BaseFrame
import worlds.gregs.hestia.network.client.encoders.messages.Config
import worlds.gregs.hestia.artemis.send

class SummoningOrbSystem : BaseFrame(SummoningOrb::class) {
    override fun getId(entityId: Int): Int {
        return TAB_ID
    }

    override fun getIndex(resizable: Boolean): Int {
        return if(resizable) RESIZABLE_INDEX else FIXED_INDEX
    }

    override fun open(entityId: Int) {
        es.send(entityId, Config(1160, -1))
        super.open(entityId)
    }

    override fun click(entityId: Int, interfaceHash: Int, componentId: Int, fromSlot: Int, toSlot: Int, option: Int) {
    }

    companion object {
        private const val TAB_ID = 747
        private const val RESIZABLE_INDEX = 180
        private const val FIXED_INDEX = 188
    }
}