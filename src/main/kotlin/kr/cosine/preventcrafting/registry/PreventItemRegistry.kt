package kr.cosine.preventcrafting.registry

import kr.cosine.preventcrafting.data.PreventItem
import org.bukkit.inventory.ItemStack

class PreventItemRegistry {

    private val preventItems = mutableListOf<PreventItem>()

    fun isPreventItem(itemStack: ItemStack): Boolean {
        return preventItems.any { it.isItem(itemStack) }
    }

    fun addPreventItem(preventItem: PreventItem) {
        preventItems.add(preventItem)
    }

    fun clear() {
        preventItems.clear()
    }
}