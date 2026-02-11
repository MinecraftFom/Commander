package com.fomdev.commander.api;

import com.fomdev.commander.event.CommandExecutedEvent;
import com.fomdev.commander.event.CommandExecutingEvent;
import com.fomdev.sasm.api.PluginClassUtil;
import com.fomdev.translation.api.LangUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Commander implements Listener {
    private static Map<String, CommandExecutor> commands;
    private static Map<String, String> help;
    private static Map<String, Boolean> op;

    @EventHandler
    public void onCommand(AsyncPlayerChatEvent event) {
        if (!event.getMessage().strip().startsWith("#/")) {
            return;
        }
        event.setCancelled(true);
        String[] argv = event.getMessage().split(" ");



        for (int i = 0; i < argv.length; i++) {
            argv[i] = argv[i].strip();
        }

        if (event.getMessage().strip().equals("#/")) {
            printHelp(event.getPlayer());
            return;
        }

        argv[0] = argv[0].replaceFirst("#/", "");

        if (!commands.containsKey(argv[0])) {
            event.getPlayer().sendMessage(ChatColor.RED + LangUtil.getTranslation("tile.commander.cmdnf.err"));
            printHelp(event.getPlayer());
            return;
        }

        if (op.get(argv[0]) && !event.getPlayer().isOp()) {
            event.getPlayer().sendMessage(ChatColor.DARK_RED + LangUtil.getTranslation("tile.langutil.chat.permission.err"));
            return;
        }

        Bukkit.getPluginManager().callEvent(new CommandExecutingEvent(argv[0], argv, event.getPlayer(), commands.get(argv[0])));
        boolean result;

        if (!(result = commands.get(argv[0]).onCommand(event.getPlayer(), null, argv[0], argv))) {
            printHelp(event.getPlayer());
        }

        Bukkit.getPluginManager().callEvent(new CommandExecutedEvent(argv[0], argv, result, event.getPlayer(), commands.get(argv[0])));
    }

    public static void syncCommand() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Class<?> k: PluginClassUtil.getAllMatch(Command.class)) {
            String id = k.getAnnotation(Command.class).id();
            if (commands.containsKey(id)) {
                return;
            }

            commands.put(id, (CommandExecutor) k.getConstructor().newInstance());
            help.put(id, k.getAnnotation(Command.class).help());
            op.put(id, k.getAnnotation(Command.class).op());
            Bukkit.getLogger().info("Registered command @0. Info: (help:@1, op:@2)"
                    .replace("@0", id)
                    .replace("@1", k.getAnnotation(Command.class).help())
                    .replace("@2", k.getAnnotation(Command.class).op()? "true": "false"));
        }
    }

    public static void resyncCommand() {
        commands = new HashMap<>();
        help = new HashMap<>();
        op = new HashMap<>();
        try {
            syncCommand();
        } catch (Exception ignored) {

        }
    }

    public static void printHelp(CommandSender sender, String... argv) {
        if (argv.length != 1) {
            for (String c : commands.keySet()) {
                sender.sendMessage(ChatColor.AQUA + c + ChatColor.YELLOW + ": " + ChatColor.LIGHT_PURPLE + help.get(c) + (op.get(c)? ChatColor.GOLD + " [OP REQUIRED]": ""));
            }
        } else {
            sender.sendMessage(help.get(argv[0]));
        }
    }

    public static Set<String> listCommand() {
        return commands.keySet();
    }

    static {
        commands = new HashMap<>();
        help     = new HashMap<>();
        op       = new HashMap<>();
    }
}