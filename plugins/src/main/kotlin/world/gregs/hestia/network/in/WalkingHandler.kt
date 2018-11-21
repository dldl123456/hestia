package world.gregs.hestia.network.`in`

import world.gregs.hestia.game.component.movement.navigate
import world.gregs.hestia.network.login.Packets
import world.gregs.hestia.core.network.Session
import world.gregs.hestia.core.network.packets.Packet
import world.gregs.hestia.core.network.packets.PacketOpcode
import world.gregs.hestia.core.network.packets.PacketSize
import world.gregs.hestia.network.game.GamePacket

@PacketSize(-1)
@PacketOpcode(Packets.WALKING, Packets.MINI_MAP_WALKING)
class WalkingHandler : GamePacket() {
    override fun read(session: Session, packet: Packet, length: Int): Boolean {
        val size = if (packet.opcode == Packets.MINI_MAP_WALKING) length - 13 else length
        val baseX = packet.readLEShortA()
        val baseY = packet.readLEShortA()
        val running = packet.readByte() == 1
        val steps = Math.min((size - 5) / 2, 25)

        if (steps <= 0) {
            return false
        }


        var x = 0
        var y = 0

        for (step in 0 until steps) {
            x = baseX + packet.readUnsignedByte()
            y = baseY + packet.readUnsignedByte()
        }

        entity?.navigate(x, y)
        return true
    }

}