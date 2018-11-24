package world.gregs.hestia.game.archetypes

import com.artemis.ArchetypeBuilder
import world.gregs.hestia.game.component.*
import world.gregs.hestia.game.component.entity.ClientIndex
import world.gregs.hestia.game.component.entity.Player
import world.gregs.hestia.game.component.map.*
import world.gregs.hestia.game.component.movement.Mobile
import world.gregs.hestia.game.component.update.appearance.AppearanceData
import world.gregs.hestia.game.component.update.DisplayName
import world.gregs.hestia.services.add

class PlayerFactory : ArchetypeFactory {

    override fun getBuilder(): ArchetypeBuilder {
        return ArchetypeBuilder().add(Player::class, ClientIndex::class, Renderable::class, Position::class, AppearanceData::class, DisplayName::class, Viewport::class, PlayerViewDistance::class, MobViewDistance::class, Mobile::class, LastLoadedRegion::class)
    }
}