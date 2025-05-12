package de.elia.features.minimap;

import de.elia.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class MiniMapDisable implements Listener {

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    handlePlayerMode(player, player.getWorld().getName(), null);
  }

  private void handlePlayerMode(Player player, String toWorldName, String fromWorldName) {
    StringBuilder messageBuilder = new StringBuilder();
    messageBuilder.append("§7teleportiere... §f§a§i§r§x§a§e§r§o");
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + player.getName() + " \"" + messageBuilder.toString() + "\"");

  }
}
