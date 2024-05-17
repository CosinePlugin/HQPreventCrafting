package kr.cosine.preventcrafting.data

import kr.cosine.preventcrafting.enums.Type
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

data class PreventItem(
    private val type: Type,
    private val materialText: String,
    private val durability: Short
) {

    private val material by lazy { Material.getMaterial(materialText) }
    private val legacyMaterialCode by lazy { materialText.toInt() }

    fun isItem(itemStack: ItemStack): Boolean {
        return when (type) {
            Type.MATERIAL -> itemStack.type == material
            Type.MATERIAL_CONTAIN -> itemStack.type.name.contains(materialText)
            Type.LEGACY -> runCatching {
                itemStack.type.id == legacyMaterialCode && (itemStack.durability == durability || durability == ALL)
            }.getOrNull() == true
        }
    }

    private companion object {
        const val ALL: Short = -1
    }
}