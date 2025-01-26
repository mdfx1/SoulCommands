package de.elia.commandwatcher;

import de.elia.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static de.elia.api.messages.builder.MessageBuilder.*;

public class CommandWatcherListener implements Listener {



  @EventHandler
  public void onPlayerCommand(PlayerCommandPreprocessEvent event){
    Player sender = event.getPlayer();
    String command = event.getMessage();

    if(CommandWatcherToggle.cwPlayers.isEmpty()){
      return;
    }

    Bukkit.getOnlinePlayers().forEach(watchPlayer -> {
      if(watchPlayer.hasPermission("soulsmp.commandwatcher") && CommandWatcherToggle.cwPlayers.contains(watchPlayer)) {
        message(watchPlayer, darkAqua(sender.getName()).append(darkGray(" » ")).append(gray(command)));
        Message.cwPrefix("<dark_aqua>" + sender.getName() + "</dark_aqua> <dark_grey>»</dark_grey> <grey>" + command  + "</grey>", watchPlayer);
      }
    });
  }
}
