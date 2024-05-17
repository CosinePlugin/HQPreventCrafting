package kr.cosine.preventcrafting.config

import kr.cosine.preventcrafting.data.PreventItem
import kr.cosine.preventcrafting.enums.Type
import kr.cosine.preventcrafting.registry.PreventItemRegistry
import org.bukkit.ChatColor
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File

class SettingConfig(
    plugin: Plugin,
    private val preventItemRegistry: PreventItemRegistry
) {

    private val file: File
    private val config: YamlConfiguration

    init {
        val path = "config.yml"
        val newFile = File(plugin.dataFolder, path)
        if (!newFile.exists() && plugin.getResource(path) != null) {
            plugin.saveResource(path, false)
        }
        file = newFile
        config = YamlConfiguration.loadConfiguration(newFile)
    }

    var preventedItemMessage = ""
        private set

    fun load() {
        val message = config.getString("message.prevented-item") ?: ""
        preventedItemMessage = ChatColor.translateAlternateColorCodes('&', message)
        config.getConfigurationSection("prevent-crafting")?.apply {
            getKeys(false).forEach { key ->
                getConfigurationSection(key)?.apply {
                    val typeText = getString("type")?.uppercase() ?: "MATERIAL"
                    val type = Type.of(typeText) ?: return@forEach
                    val materialText = getString("material")?.uppercase() ?: return@forEach
                    val durability = getInt("durability").toShort()
                    val preventItem = PreventItem(type, materialText, durability)
                    preventItemRegistry.addPreventItem(preventItem)
                }
            }
        }
    }

    fun reload() {
        preventItemRegistry.clear()
        config.load(file)
        load()
    }
}