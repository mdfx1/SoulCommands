package de.elia.systemclasses.register;

import de.elia.features.bedrock.fullbright.FbRespawnListener;
import de.elia.features.chat.ChatListener;
import de.elia.features.commandwatcher.CommandWatcherListener;

import de.elia.features.minimap.MiniMapDisable;
import de.elia.features.teleport.back.TeleportListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

//This class load all Events
public class EventRegister {

  private static final @NotNull Set<Listener> EVENTS =  new HashSet<>();

  static {
    EVENTS.add(new CommandWatcherListener());
    EVENTS.add(new ChatListener());
    EVENTS.add(new TeleportListener());
    EVENTS.add(new FbRespawnListener());
    EVENTS.add(new MiniMapDisable());
  }

  public static void registerEvents(@NotNull PluginManager pluginManager, @NotNull Plugin plugin){
    EVENTS.forEach(listener -> pluginManager.registerEvents(listener, plugin));
  }

  public static void registerEvents(@NotNull Plugin plugin){
    EVENTS.forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, plugin));
  }

  public static void registerEvent(@NotNull PluginManager pluginManager, @NotNull Listener listener, @NotNull Plugin plugin){
    EVENTS.add(listener);
    pluginManager.registerEvents(listener, plugin);
  }

  public static void registerEvent(@NotNull Listener listener, @NotNull Plugin plugin){
    Bukkit.getPluginManager().registerEvents(listener, plugin);
  }

}
