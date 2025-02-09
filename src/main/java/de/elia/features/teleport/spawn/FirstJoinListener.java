package de.elia.features.teleport.spawn;

import de.elia.utils.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.net.http.WebSocket;

public class FirstJoinListener implements Listener {
  @EventHandler
  public void onFirstJoin(PlayerJoinEvent event){
    Player player = event.getPlayer();
    if(player.hasPlayedBefore()){
      return;
    }
    Message.standard("Willkommen auf dem Server! Joine gerne unserem Discord für mehr Information oder such dir gleich eine Nation", player);
    Message.discord(player);
  }
}
