package kr.cosine.preventcrafting

import kr.cosine.preventcrafting.command.PreventCraftingAdminCommand
import kr.cosine.preventcrafting.config.SettingConfig
import kr.cosine.preventcrafting.listener.CraftingListener
import kr.cosine.preventcrafting.registry.PreventItemRegistry
import kr.cosine.preventcrafting.service.PreventCraftingService
import org.bukkit.plugin.java.JavaPlugin

class HQPreventCrafting : JavaPlugin() {

    override fun onEnable() {
        val preventItemRegistry = PreventItemRegistry()

        val settingConfig = SettingConfig(this, preventItemRegistry)
        settingConfig.load()

        val preventCraftingService = PreventCraftingService(settingConfig, preventItemRegistry)

        server.pluginManager.registerEvents(CraftingListener(preventCraftingService), this)
        getCommand("조합금지관리")?.setExecutor(PreventCraftingAdminCommand(preventCraftingService))
    }
}