package de.elia;

import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

//This class loads the Plugin
public class Main extends JavaPlugin {

  private static Main main;

  @Override
  public void onEnable(){main = this;
    CommandRegister.registerCommands(this.getServer());
    EventRegister.registerEvents(this);
  }

  @Override
  public void onDisable(){
    CommandRegister.unregisterCommands(this.getServer());
  }

  @NotNull
  public static Main pluginMain(){
    return main;
  }
}
