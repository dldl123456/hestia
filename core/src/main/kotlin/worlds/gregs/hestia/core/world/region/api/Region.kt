package worlds.gregs.hestia.core.world.region.api

import com.artemis.Aspect
import worlds.gregs.hestia.artemis.SubscriptionSystem

abstract class Region(builder: Aspect.Builder) : SubscriptionSystem(builder) {

    /**
     * Loads region if hasn't been loaded already
     * @param regionId The region to load
     */
    abstract fun load(regionId: Int)

    /**
     * Unloads region map & land if the region is loaded
     * @param entityId The region to unload
     */
    abstract fun unload(entityId: Int)

}