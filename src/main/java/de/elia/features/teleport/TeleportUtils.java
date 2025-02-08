package de.elia.features.teleport;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TeleportUtils {

  public static Location centerPlayerOnBlock(Player player) {
    Location playerLocation = player.getLocation();

    //get the coordinates of the block below the player
    int blockX = playerLocation.getBlockX();
    int blockY = playerLocation.getBlockY() - 1; //subtract 1 to get the block below
    int blockZ = playerLocation.getBlockZ();

    //create a new location at the center of the block
    Location centeredLocation = new Location(player.getWorld(), blockX + 0.5, blockY + 1, blockZ + 0.5);

    return centeredLocation;
  }

  public static int normalizeYaw(float yaw) {
    //normalize the yaw value to be between -180 and 180 degrees
    yaw = (yaw % 360 + 360) % 360;
    if (yaw > 180) {
      yaw -= 360;
    }

    //round the yaw to the nearest multiple of 90 degrees
    int normalizedYaw = Math.round(yaw / 90) * 90;

    return normalizedYaw;
  }
}
