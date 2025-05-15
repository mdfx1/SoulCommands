package de.elia.features.bypassFull;

import de.elia.utils.ErrorMessage;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;

public class BypassListener implements Listener {
    private static final String BYPASS_PERMISSION = "soulsmp.bypass";
    private static final int MAX_PLAYERS = 200;

    public BypassListener() {
      // Register the bypass permission if it doesn't exist
      if (Bukkit.getPluginManager().getPermission(BYPASS_PERMISSION) == null) {
        Bukkit.getPluginManager().addPermission(
          new Permission(BYPASS_PERMISSION, "", PermissionDefault.OP)
        );
      }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerLogin(@NotNull PlayerLoginEvent event) {
      // Check if server is full
      if (Bukkit.getOnlinePlayers().size() >= MAX_PLAYERS) {
        // Check if player has bypass permission
        if (event.getPlayer().hasPermission(BYPASS_PERMISSION)) {
          event.allow();
          return;
        }

        event.disallow(
          PlayerLoginEvent.Result.KICK_FULL,
          "Der Server ist voll!"
        );
      }
    }
}
