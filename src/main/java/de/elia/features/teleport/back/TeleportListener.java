package de.elia.features.teleport.back;

import de.elia.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TeleportListener implements Listener {
  private BackManager backManager = Main.getBackManager();
  @EventHandler
  public void onTeleport(PlayerTeleportEvent event){
    //add teleport location to BackManager
    backManager.addTeleport(event.getPlayer(), event.getFrom());
  }
}
