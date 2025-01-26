package de.elia.gemmerr.joinleave;

import de.elia.gemmerr.commandwatcher.CommandWatcherToggle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.MetadataValue;

import org.jetbrains.annotations.NotNull;

import static de.elia.api.messages.builder.MessageBuilder.*;

//This class calls a join/quit event to send a message if a player joins or leaves
//Coded by Gemmerr -> Edited by Elia (GetMessageBuilder)
public class JoinLeave implements Listener {

  @EventHandler
  public void onJoin(@NotNull PlayerJoinEvent event) {
    Player player = event.getPlayer();
    event.joinMessage(null);
    if(isVanished(player)) {
      //TODO: Fix color methode call error
      //broadcast(color("#9545a3", player.getName()).append(gray(" hat den Server betreten!")));
    }
    if(!player.hasPlayedBefore()) {
      //TODO: Fix color methode call error
      //messageWithPrefix(player, color("#9545a3", "Willkommen ").append(gray("auf dem")).append(color("#9545a3", "SoulSMP")).append(gray(".  Trete gerne unserem ")).append(blue("Discord")).append(gray(" Server über den /discord Command bei.")));
    }
  }
  @EventHandler
  public void onLeave(@NotNull PlayerQuitEvent event) {
    Player player = event.getPlayer();
    event.quitMessage(null);
    if(isVanished(player)) {
      //TODO: Fix color methode call error
      //broadcast(color("#9545a3", player.getName()).append(gray(" hat den Server verlassen!")));
    }
  }

  public boolean isVanished(@NotNull Player player){
    for (MetadataValue meta : player.getMetadata("vanished")) {
      if (meta.asBoolean()) return false;
    }
    return true;
  }
}
