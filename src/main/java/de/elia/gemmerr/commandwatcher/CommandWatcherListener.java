package de.elia.gemmerr.commandwatcher;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static de.elia.api.messages.builder.MessageBuilder.*;

public class CommandWatcherListener implements Listener {
  @EventHandler
  public void onPlayerCommand(PlayerCommandPreprocessEvent event){
    Player player = event.getPlayer();
    String command = event.getMessage();
    Bukkit.getOnlinePlayers().forEach(player1 -> {
      if(player1.hasPermission("soulsmp.commandwatcher") && CommandWatcherToggle.players.contains(player1)) {
        message(player1, darkAqua(player.getName()).append(darkGray(">>")).append(gray(command)));
      }
    });
  }
}
