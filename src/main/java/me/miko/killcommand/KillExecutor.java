package me.miko.killcommand;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jspecify.annotations.NonNull;

public class KillExecutor implements CommandExecutor {
    private static final DamageSource KILL_DAMAGE_SOURCE = DamageSource.builder(DamageType.GENERIC_KILL).build();
    private static final Component NO_PERMISSION_MESSAGE = Component.text(
            "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.",
            NamedTextColor.RED
    );
    private static final Component PLAYER_NOT_FOUND_MESSAGE = Component.text("Player not found.", NamedTextColor.RED);
    private static final Component CONSOLE_USAGE_MESSAGE = Component.text("Specify a player when using this command from console.", NamedTextColor.RED);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String @NonNull [] args) {
        if (!sender.hasPermission("killcommand.kill") || (args.length > 0 && !sender.hasPermission("killcommand.kill.others"))) {
            sender.sendMessage(NO_PERMISSION_MESSAGE);
            return true;
        }

        Player player = (args.length == 0 && sender instanceof Player) ? (Player) sender : (args.length > 0 ? Bukkit.getPlayer(args[0]) : null);
        if (player == null) {
            sender.sendMessage(args.length == 0 ? CONSOLE_USAGE_MESSAGE : PLAYER_NOT_FOUND_MESSAGE);
            return true;
        }

        player.damage(player.getHealth() + player.getAbsorptionAmount() + 1.0D, KILL_DAMAGE_SOURCE);
        return true;
    }
}
