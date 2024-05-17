package kr.cosine.preventcrafting.enums

enum class Type {
    MATERIAL,
    MATERIAL_CONTAIN,
    LEGACY;

    companion object {
        fun of(text: String): Type? {
            return runCatching { valueOf(text.uppercase()) }.getOrNull()
        }
    }
}