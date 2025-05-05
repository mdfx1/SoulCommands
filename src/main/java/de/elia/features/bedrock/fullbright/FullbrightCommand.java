package de.elia.features.bedrock.fullbright;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FullbrightCommand extends Command implements CommandExecutor {
  public static final Set<UUID> fbPlayers = new HashSet<>();

  public FullbrightCommand() {
    super("fullbright");
    this.setDescription("Toggle FullBright");
    this.setUsage("/fullbright");
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
    return onCommand(sender, this, label, args);
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
    if (!(sender instanceof Player player)) {
      sender.sendMessage("§cOnly players can use this command!");
      return true;
    }

    if (!player.getName().contains(".")) {
      player.sendMessage("§cThis command is only for Bedrock players!");
      return true;
    }

    UUID playerId = player.getUniqueId();

    if (fbPlayers.contains(playerId)) {
      player.removePotionEffect(PotionEffectType.NIGHT_VISION);
      fbPlayers.remove(playerId);
      player.sendMessage("§aFullbright disabled");
    } else {
      PotionEffect nightVision = new PotionEffect(
        PotionEffectType.NIGHT_VISION,
        Integer.MAX_VALUE,
        0,
        false,
        false,
        true
      );
      player.addPotionEffect(nightVision);
      fbPlayers.add(playerId);
      player.sendMessage("§aFullbright enabled");
    }

    return true;
  }

  public static void reapplyEffect(Player player) {
    if (fbPlayers.contains(player.getUniqueId())) {
      player.removePotionEffect(PotionEffectType.NIGHT_VISION);
      PotionEffect nightVision = new PotionEffect(
        PotionEffectType.NIGHT_VISION,
        Integer.MAX_VALUE,
        0,
        false,
        false,
        true
      );
      player.addPotionEffect(nightVision);
    }
  }
}
