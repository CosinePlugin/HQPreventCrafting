package kr.cosine.preventcrafting.service

import kr.cosine.preventcrafting.config.SettingConfig
import kr.cosine.preventcrafting.registry.PreventItemRegistry
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.inventory.ItemStack

class PreventCraftingService(
    private val settingConfig: SettingConfig,
    private val preventItemRegistry: PreventItemRegistry
) {

    fun cancelIfPreventItem(event: Cancellable, player: Player, itemStack: ItemStack) {
        if (!player.isOp && preventItemRegistry.isPreventItem(itemStack)) {
            event.isCancelled = true
            val preventedItemMessage = settingConfig.preventedItemMessage
            if (preventedItemMessage.isNotEmpty()) {
                player.sendMessage(preventedItemMessage)
            }
        }
    }

    fun reload() {
        settingConfig.reload()
    }
}