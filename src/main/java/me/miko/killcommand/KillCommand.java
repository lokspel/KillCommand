package me.miko.killcommand;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class KillCommand extends JavaPlugin {
    private final CommandExecutor killExecutor = new KillExecutor();

    @Override
    public void onEnable() {
        PluginCommand killCommand = Objects.requireNonNull(getCommand("kill"));
        killCommand.setExecutor(killExecutor);
    }
}
