package worlds.gregs.hestia.core.display.update.logic.block.factory.player

import com.artemis.ComponentMapper
import worlds.gregs.hestia.artemis.Aspect
import worlds.gregs.hestia.core.display.update.api.BlockFactory
import worlds.gregs.hestia.core.display.update.logic.DirectionUtils
import worlds.gregs.hestia.core.display.update.model.components.Renderable
import worlds.gregs.hestia.core.display.update.model.components.direction.FaceUpdate
import worlds.gregs.hestia.core.display.update.model.components.direction.Facing
import worlds.gregs.hestia.game.update.blocks.player.FaceDirectionPlayerBlock

class FaceDirectionPlayerBlockFactory(flag: Int) : BlockFactory<FaceDirectionPlayerBlock>(Aspect.all(Renderable::class, FaceUpdate::class), true, flag = flag) {

    private lateinit var facingMapper: ComponentMapper<Facing>

    override fun create(player: Int, other: Int): FaceDirectionPlayerBlock {
        val face = facingMapper.get(other)
        return FaceDirectionPlayerBlock(flag, if (face != null) DirectionUtils.getFaceDirection(face.x, face.y) else 0)
    }

}