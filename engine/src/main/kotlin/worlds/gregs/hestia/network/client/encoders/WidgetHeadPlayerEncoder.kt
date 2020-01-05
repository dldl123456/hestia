package worlds.gregs.hestia.network.client.encoders

import world.gregs.hestia.core.network.codec.message.MessageEncoder
import world.gregs.hestia.core.network.codec.packet.Endian
import world.gregs.hestia.core.network.codec.packet.PacketBuilder
import world.gregs.hestia.core.network.protocol.ClientOpcodes.WIDGET_PLAYER_HEAD
import worlds.gregs.hestia.network.client.encoders.messages.WidgetHeadPlayer

class WidgetHeadPlayerEncoder : MessageEncoder<WidgetHeadPlayer>() {

    override fun encode(builder: PacketBuilder, message: WidgetHeadPlayer) {
        val (id, widget) = message
        builder.apply {
            writeOpcode(WIDGET_PLAYER_HEAD)
            writeInt(id shl 16 or widget, order = Endian.LITTLE)
        }
    }

}