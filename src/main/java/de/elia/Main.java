package de.elia;

import de.elia.systemclasses.register.CommandRegister;
import de.elia.systemclasses.register.EventRegister;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
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
