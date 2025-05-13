package de.elia.features.playerhead;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerHeadListener implements Listener {
  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent event) {
    Player player = event.getPlayer();

    ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
    SkullMeta meta = (SkullMeta) skull.getItemMeta();
    meta.setOwner(player.getName());
    skull.setItemMeta(meta);

    player.getWorld().dropItem(player.getLocation().add(0, 1, 0), skull);
  }
}
