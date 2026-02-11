package com.fomdev.commander.event;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CommandExecutingEvent extends Event {
    protected final String command;
    protected final String[] commands;
    protected final CommandSender executor;
    protected final CommandExecutor execution;

    public CommandExecutingEvent(String command, String[] commands, CommandSender executor, CommandExecutor execution) {
        this.command = command;
        this.commands = commands;
        this.executor = executor;
        this.execution = execution;
    }

    public String getCommand() {
        return command;
    }

    public String[] getCommands() {
        return commands;
    }

    public CommandSender getExecutor() {
        return executor;
    }

    public CommandExecutor getExecution() {
        return execution;
    }

    @Override
    public HandlerList getHandlers() {
        return new HandlerList();
    }
}