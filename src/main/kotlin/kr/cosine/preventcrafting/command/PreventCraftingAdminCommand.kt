package kr.cosine.preventcrafting.command

import kr.cosine.preventcrafting.service.PreventCraftingService
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class PreventCraftingAdminCommand(
    private val preventCraftingService: PreventCraftingService
) : CommandExecutor, TabCompleter {

    private val commands = arrayOf(
        "§6[HQPreventCrafting] §f명령어 도움말",
        "/조합금지관리",
        " §7┗━§f리로드 §7config.yml을 리로드합니다."
    )

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            printHelp(sender)
            return true
        }
        checker(sender, args)
        return true
    }

    private fun printHelp(sender: CommandSender) {
        commands.forEach(sender::sendMessage)
    }

    private fun checker(sender: CommandSender, args: Array<out String>) {
        when (args[0]) {
            "리로드" -> reload(sender)
            else -> printHelp(sender)
        }
    }

    private fun reload(sender: CommandSender) {
        preventCraftingService.reload()
        sender.sendMessage("§aconfig.yml을 리로드하였습니다.")
    }

    private val tabs = listOf("리로드")

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): List<String> {
        if (args.size == 1) {
            return tabs
        }
        return emptyList()
    }
}