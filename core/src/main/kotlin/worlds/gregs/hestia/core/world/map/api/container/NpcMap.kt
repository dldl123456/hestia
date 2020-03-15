package worlds.gregs.hestia.core.world.map.api.container

import com.artemis.ComponentMapper
import worlds.gregs.hestia.artemis.Aspect
import worlds.gregs.hestia.core.entity.entity.model.components.Position
import worlds.gregs.hestia.core.entity.entity.model.components.Size
import worlds.gregs.hestia.core.entity.entity.model.components.height
import worlds.gregs.hestia.core.entity.entity.model.components.width
import worlds.gregs.hestia.core.entity.npc.api.Npc
import worlds.gregs.hestia.core.world.movement.model.components.Shift

/**
 * Contains position of all npcs
 */
open class NpcMap : EntityMap(Aspect.all(Npc::class)) {

    private lateinit var sizeMapper: ComponentMapper<Size>
    private lateinit var positionMapper: ComponentMapper<Position>

    override fun inserted(entityId: Int) {
        val position = positionMapper.get(entityId)
        val width = sizeMapper.width(entityId)
        val height = sizeMapper.height(entityId)
        insert(entityId, position.x, position.y, position.plane, width, height)
        setTile(entityId, position.locationHash30Bit)
    }

    override fun update(entityId: Int, shift: Shift) {
        val position = positionMapper.get(entityId)
        val size = sizeMapper.get(entityId)
        update(entityId, position.x, position.y, position.plane, size.sizeX, size.sizeY)
        setTile(entityId, position.locationHash30Bit)
        removeFromTile(entityId, Position.hash30Bit(position.x - shift.x, position.y - shift.y, position.plane - shift.plane))
    }

    override fun removed(entityId: Int) {
        val position = positionMapper.get(entityId)
        remove(entityId, position.plane)
        removeFromTile(entityId, position.locationHash30Bit)
    }
}