package de.elia.features.teleport;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TeleportUtils {

  public static Location centerPlayerOnBlock(Player player) {
    Location playerLocation = player.getLocation();

    // Get the coordinates of the block below the player
    int blockX = playerLocation.getBlockX();
    int blockY = playerLocation.getBlockY() - 1; // Subtract 1 to get the block below
    int blockZ = playerLocation.getBlockZ();

    // Create a new location at the center of the block
    Location centeredLocation = new Location(player.getWorld(), blockX + 0.5, blockY + 1, blockZ + 0.5);

    return centeredLocation;
  }

  public static int normalizeYaw(float yaw) {
    // Stelle sicher, dass der Yaw-Wert im Bereich von -180 bis 180 liegt.
    yaw = (yaw % 360 + 360) % 360;
    if (yaw > 180) {
      yaw -= 360;
    }

    // Runde auf die nächste 90-Grad-Zahl.
    int normalizedYaw = Math.round(yaw / 90) * 90;

    return normalizedYaw;
  }
}
