package worlds.gregs.hestia.network.client.encoders.messages

import world.gregs.hestia.core.network.codec.message.Message

/**
 * Sends a custom region of the map to load
 * @param chunkX The x coordinate of the chunk to start loading
 * @param chunkY The y coordinate of the chunk to start loading
 * @param forceReload Whether to reload existing chunks
 * @param mapSize The map size in tiles
 * @param mapHash The radius of chunks to load
 * @param positions 18 Hash positions of every player (first login only)
 * @param location 30 player hash position (first login only)
 * @param chunks List of chunk position hashes
 * @param chunkCount Total dynamic chunk count
 */
data class MapRegionDynamic(val chunkX: Int, val chunkY: Int, val forceReload: Boolean, val mapSize: Int, val mapHash: Int, val positions: IntArray?, val location: Int?, val chunks: List<Int?>, val chunkCount: Int) : Message {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MapRegionDynamic

        if (chunkX != other.chunkX) return false
        if (chunkY != other.chunkY) return false
        if (forceReload != other.forceReload) return false
        if (mapSize != other.mapSize) return false
        if (mapHash != other.mapHash) return false
        if (positions != null) {
            if (other.positions == null) return false
            if (!positions.contentEquals(other.positions)) return false
        } else if (other.positions != null) return false
        if (location != other.location) return false
        if (chunks != other.chunks) return false
        if (chunkCount != other.chunkCount) return false

        return true
    }

    override fun hashCode(): Int {
        var result = chunkX
        result = 31 * result + chunkY
        result = 31 * result + forceReload.hashCode()
        result = 31 * result + mapSize
        result = 31 * result + mapHash
        result = 31 * result + (positions?.contentHashCode() ?: 0)
        result = 31 * result + (location ?: 0)
        result = 31 * result + chunks.hashCode()
        result = 31 * result + chunkCount
        return result
    }
}