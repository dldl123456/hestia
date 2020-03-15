package worlds.gregs.hestia.core.world.movement.model.components

import com.artemis.Component
import com.artemis.annotations.PooledWeaver
import worlds.gregs.hestia.core.display.update.model.Direction
import java.util.concurrent.ConcurrentLinkedQueue

@PooledWeaver
class Steps : Component() {
    var lastX: Int? = null
    var lastY: Int? = null

    var path = -1

    private val directions = ConcurrentLinkedQueue<Direction>()

    val hasNext: Boolean
        get() = directions.isNotEmpty()

    val nextDirection: Direction
        get() = directions.poll()

    val peek: Direction
        get() = directions.peek() ?: Direction.NONE

    fun add(direction: Direction, nextX: Int, nextY: Int) {
        directions.add(direction)
        lastX = nextX
        lastY = nextY
    }

    fun clear() {
        directions.clear()
        lastX = null
        lastY = null
    }
}