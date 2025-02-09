package de.elia.features.teleport.back;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BackManager {
  private static Map<Player, Location> teleports = new HashMap<>();


  //add teleport location for player
  public void addTeleport(Player player, Location location) {
    if(teleports.containsKey(player)){
      teleports.remove(player);
    }
    teleports.put(player, location);
  }

  //check if player has teleport location
  public boolean hasTeleport(Player player) {
    return teleports.containsKey(player);
  }

  //get teleport location for player
  public Location getTeleportLocation(Player player) {
    return teleports.get(player);
  }
}
