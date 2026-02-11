package com.fomdev.commander.event;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CommandExecutedEvent extends Event {
    protected final String command;
    protected final String[] commands;
    protected final boolean status;
    protected final CommandSender executor;
    protected final CommandExecutor execution;

    public CommandExecutedEvent(String command, String[] commands, boolean status, CommandSender executor, CommandExecutor execution) {
        this.command = command;
        this.commands = commands;
        this.status = status;
        this.executor = executor;
        this.execution = execution;
    }

    public String getCommand() {
        return command;
    }

    public String[] getCommands() {
        return commands;
    }

    public boolean state() {
        return status;
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