package de.elia.features.teleport.back;

import de.elia.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;  
import org.bukkit.entity.Player;

public class TeleportListener implements Listener {
    private BackManager backManager = Main.getBackManager();

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();  
        backManager.addTeleport(player, player.getLocation());  
    }
}
