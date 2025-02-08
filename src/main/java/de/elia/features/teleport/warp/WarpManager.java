package de.elia.features.teleport.warp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WarpManager {

  private Map<String, Location> warps = new HashMap<>();
  private FileConfiguration config;
  private File configFile;

  public WarpManager(Plugin plugin) {
    configFile = new File(plugin.getDataFolder(), "warps.yml");
    config = YamlConfiguration.loadConfiguration(configFile);
    loadWarps();
  }

  public void loadWarps() {
    warps.clear();
    if (config.contains("warps")) {
      for (String warpName : config.getConfigurationSection("warps").getKeys(false)) {
        Location location = (Location) config.getConfigurationSection("warps").get(warpName);
        warps.put(warpName, location);
      }
    }
  }

  public void saveWarps() {
    config.set("warps", null);
    for (Map.Entry<String, Location> entry : warps.entrySet()) {
      config.set("warps." + entry.getKey(), entry.getValue());
    }
    try {
      config.save(configFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Location getWarp(String warpName) {
    if(warps.containsKey(warpName)){
      return warps.get(warpName);
    }
    return null;
  }

  public boolean setWarp(String warpName, Location location) {
    if(warps.containsValue(location) && warps.containsKey(warpName)){
      return false;
    }
    warps.put(warpName, location);
    return true;
  }

  public boolean removeWarp(String warpName) {
    if(!warps.containsKey(warpName)){
      return false;
    }
    warps.remove(warpName);
    return true;
  }

  public Map<String, Location> getWarps() {
    return warps;
  }
}
