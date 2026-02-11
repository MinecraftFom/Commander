package com.fomdev.commander.command;

import com.fomdev.commander.api.Command;
import com.fomdev.commander.api.Commander;
import com.fomdev.sasm.api.PluginClassUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

@Command(id = "commander", help = "#/commander help | list | resync")
public class CommandCmder implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        switch (args.length) {
            case 2 -> {
                 switch (args[1]) {
                     case "help" -> {
                         Commander.printHelp(sender);
                         return true;
                     }

                     case "list" -> {
                         for (String s: Commander.listCommand()) {
                             sender.sendMessage(ChatColor.AQUA + s);
                         }
                         return true;
                     }

                     case "resync" -> {
                         PluginClassUtil.rescanCache();
                         Commander.resyncCommand();
                         return true;
                     }

                     default -> {
                         return false;
                     }
                 }
            }

            default -> {
                return false;
            }
        }
    }
}