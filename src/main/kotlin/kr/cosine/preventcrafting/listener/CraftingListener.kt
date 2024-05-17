package kr.cosine.preventcrafting.listener

import kr.cosine.preventcrafting.service.PreventCraftingService
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.CraftItemEvent

class CraftingListener(
    private val preventCraftingService: PreventCraftingService
) : Listener {

    @EventHandler
    fun onCrafting(event: CraftItemEvent) {
        val itemStack = event.currentItem ?: return
        val player = event.whoClicked as Player
        preventCraftingService.cancelIfPreventItem(event, player, itemStack)
    }
}