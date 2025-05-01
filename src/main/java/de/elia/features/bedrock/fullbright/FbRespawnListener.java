package de.elia.features.bedrock.fullbright;

import de.elia.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FbRespawnListener implements Listener {

  @EventHandler
  public void onPlayerRespawn(PlayerRespawnEvent event) {
    Player player = event.getPlayer();
    if (FullbrightCommand.fbPlayers.contains(player.getUniqueId())) {
      Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
        if (player.isOnline()) {
          PotionEffect nightVision = new PotionEffect(
            PotionEffectType.NIGHT_VISION,
            Integer.MAX_VALUE,
            1,
            false,
            false,
            false
          );
          player.addPotionEffect(nightVision);
        }
      }, 20L * 5);
    }
  }
}
