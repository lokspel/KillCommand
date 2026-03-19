package me.miko.killcommand;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class KillCommand extends JavaPlugin {
    @Override
    public void onEnable() {
        PluginCommand killCommand = Objects.requireNonNull(getCommand("kill"));
        KillExecutor executor = new KillExecutor();
        killCommand.setExecutor(executor);
        killCommand.setTabCompleter(executor);
    }
}
