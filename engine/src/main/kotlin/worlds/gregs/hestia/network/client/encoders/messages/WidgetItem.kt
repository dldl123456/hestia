package worlds.gregs.hestia.network.client.encoders.messages

import world.gregs.hestia.core.network.codec.message.Message

/**
 * Sends an item to display on a window widget
 * @param id The id of the parent window
 * @param widget The index of the widget
 * @param item The item id
 * @param amount The number of the item
 */
data class WidgetItem(val id: Int, val widget: Int, val item: Int, val amount: Int) : Message