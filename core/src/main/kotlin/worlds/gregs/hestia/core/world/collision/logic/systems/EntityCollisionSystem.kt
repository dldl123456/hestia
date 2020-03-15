package worlds.gregs.hestia.core.world.collision.logic.systems

import com.artemis.ComponentMapper
import com.artemis.annotations.Wire
import world.gregs.hestia.core.services.int
import worlds.gregs.hestia.core.entity.entity.model.components.Position
import worlds.gregs.hestia.core.entity.entity.model.components.Size
import worlds.gregs.hestia.core.entity.entity.model.components.height
import worlds.gregs.hestia.core.entity.entity.model.components.width
import worlds.gregs.hestia.core.entity.npc.api.NpcChunk
import worlds.gregs.hestia.core.entity.player.api.PlayerChunk
import worlds.gregs.hestia.core.world.collision.api.EntityCollision
import worlds.gregs.hestia.core.world.collision.model.components.Ghost
import worlds.gregs.hestia.core.world.collision.model.components.Permeable
import worlds.gregs.hestia.core.world.movement.model.components.Shift
import worlds.gregs.hestia.core.world.movement.model.components.Steps

@Wire(failOnNull = false)
class EntityCollisionSystem : EntityCollision() {

    private lateinit var positionMapper: ComponentMapper<Position>
    private lateinit var sizeMapper: ComponentMapper<Size>
    private lateinit var shiftMapper: ComponentMapper<Shift>
    private lateinit var stepsMapper: ComponentMapper<Steps>
    private lateinit var ghostMapper: ComponentMapper<Ghost>
    private lateinit var permeableMapper: ComponentMapper<Permeable>

    private var playerChunk: PlayerChunk? = null
    private var npcChunk: NpcChunk? = null

    private val array = Array(MAP_SIZE) { BooleanArray(MAP_SIZE) }
    private var ghost = false
    private var offsetX = 0
    private var offsetY = 0

    override fun load(entityId: Int, position: Position) {
        //Ghosts don't need entity collision
        ghost = ghostMapper.has(entityId)
//        if (ghost) {
//            return
//        }

        //Clear
        for (a in array) {
            a.fill(false)
        }

        //Offset is the overlap of the chunk area checking and region
        this.offsetX = position.x - MAP_SIZE / 2
        this.offsetY = position.y - MAP_SIZE / 2

        //Add all entities in within (CHUNK_RADIUS * 2 + 1) chunk diameter
        val players = playerChunk?.get(position, CHUNK_RADIUS)
        if (players != null) {
            apply(entityId, players)
        }
        val npcs = npcChunk?.get(position, CHUNK_RADIUS)
        if (npcs != null) {
            apply(entityId, npcs)
        }
    }

    private fun apply(entityId: Int, list: List<Int>) {
        //For all other entities within area that are collide-able
        list.filterNot { it == entityId || permeableMapper.has(it) }.forEach {
            //Check size
            val width = sizeMapper.width(it)
            val height = sizeMapper.height(it)

            //Check position
            val position = positionMapper.get(it)
            var startX = position.x
            var startY = position.y

            //Apply any future movements
            if (shiftMapper.has(it)) {
                val shift = shiftMapper.get(it)
                //If they're moving plane we don't need to worry about colliding with them
                if (shift.plane != 0) {
                    return@forEach
                }
                startX += shift.x
                startY += shift.y
            } else if (stepsMapper.has(it)) {
                val steps = stepsMapper.get(it)
                if (steps.hasNext) {
                    startX = steps.lastX ?: startX
                    startY = steps.lastY ?: startY
                }
            }

            //Apply the offset
            startX -= offsetX
            startY -= offsetY

            //Fill array
            for (x in startX until startX + width) {
                for (y in startY until startY + height) {
                    if(x < MAP_SIZE && y < MAP_SIZE) {
                        array[x][y] = true
                    }
                }
            }
        }
    }

    override fun collides(x: Int, y: Int, flag: Int): Boolean {
        //Ghosts don't collide with entities
        if (ghost) {
            return false
        }

        val x = x - offsetX
        val y = y - offsetY

        //Check negative out of bounds
        if (x < 0 || y < 0) {
            return false
        }

        //Check positive out of bounds
        if (x >= MAP_SIZE || y >= MAP_SIZE) {
            return false
        }

        //Return if an entity exists
        return array[x][y]
    }

    companion object {
        private const val CHUNK_RADIUS = 2
        private const val MAP_SIZE = (CHUNK_RADIUS * 2 + 1) * 8//40

        private fun print(array: Array<BooleanArray>, pointX: Int, pointY: Int) {
            for (y in array[0].indices.reversed()) {
                for (x in array.indices) {
                    print(if (x == pointX && y == pointY) "X" else array[x][y].int)
                    print(" ")
                }
                println()
            }
            println()
        }
    }

}