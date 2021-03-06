package worlds.gregs.hestia.game.update.blocks

import worlds.gregs.hestia.game.update.UpdateBlock

/**
 * @param id Graphic id
 * @param trajectory speed & height
 * @param details rotation, slot & refresh
 */
data class GraphicBlock(override val flag: Int, val type: Int, val id: Int, val trajectory: Int, val details: Int) : UpdateBlock