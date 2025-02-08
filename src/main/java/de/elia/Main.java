package de.elia;

import de.elia.features.teleport.warp.WarpManager;
import de.elia.systemclasses.DatabaseManager;
import de.elia.systemclasses.register.CommandRegister;
import de.elia.systemclasses.register.EventRegister;
import de.elia.api.logging.PluginLogger;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

//This class loads the Plugin
public class Main extends JavaPlugin {

  private static Main instance;
  public static Server server;
  final PluginLogger soulLogger = new PluginLogger("SoulCommand-Logger");
  private static WarpManager warpManager;

  @Override
  public void onEnable(){
    //set instance
    instance = this;
    server = this.getServer();

    //set WarpManager
    warpManager = new WarpManager(instance);

    //load Commands and Events
    CommandRegister.registerCommands(this.getServer());
    EventRegister.registerEvents(this);

    //connect to Database
    DatabaseManager.connect();

    //remove restart Command
    this.getServer().getCommandMap().getKnownCommands().remove("restart");
  }

  @Override
  public void onDisable(){
    CommandRegister.unregisterCommands(this.getServer());

    DatabaseManager.disconnect();
  }

  @NotNull
  public static WarpManager getWarpManager() {
    return warpManager;
  }

  @NotNull
  public static Main getInstance() {
    return instance;
  }

  public @NotNull PluginLogger getSoulLogger() {
    return soulLogger;
  }

}
