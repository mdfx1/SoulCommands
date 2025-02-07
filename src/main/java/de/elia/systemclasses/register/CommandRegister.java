package de.elia.systemclasses.register;

import de.elia.features.discord.DiscordCommand;

import de.elia.features.commandwatcher.CommandWatcherToggle;
import de.elia.features.enderchest.CraftCommand;
import de.elia.features.enderchest.EnderchestCommand;
import de.elia.features.fly.FlyCommand;
import de.elia.features.fly.FlySpeedCommand;
import de.elia.features.heal.HealCommand;
import de.elia.features.invsee.InvseeCommand;
import de.elia.features.playtime.PlaytimeCommand;
import de.elia.features.teleport.spawn.SetSpawnCommand;
import de.elia.features.teleport.spawn.SpawnCommand;
import de.elia.features.teleport.tpa.TpaAcceptCommand;
import de.elia.features.teleport.tpa.TpaCommand;
import de.elia.features.teleport.tpa.TpaDenyCommand;
import de.elia.features.whois.WhoisCommand;
import io.papermc.paper.configuration.WorldConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;

import org.jetbrains.annotations.NotNull;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

//This class register all commands
public class CommandRegister {

  private static final @NotNull Map<String, Command> COMMANDS = new HashMap<>();

  static {
    COMMANDS.put("discord", new DiscordCommand());
    COMMANDS.put("playtime", new PlaytimeCommand());
    COMMANDS.put("commandwatcher", new CommandWatcherToggle());
    COMMANDS.put("heal", new HealCommand());
    COMMANDS.put("craft", new CraftCommand());
    COMMANDS.put("enderchest", new EnderchestCommand());
    COMMANDS.put("whois", new WhoisCommand());
    COMMANDS.put("spawn", new SpawnCommand());
    COMMANDS.put("setspawn", new SetSpawnCommand());
    COMMANDS.put("tpa", new TpaCommand());
    COMMANDS.put("tpadeny", new TpaDenyCommand());
    COMMANDS.put("tpaaccept", new TpaAcceptCommand());
    COMMANDS.put("fly", new FlyCommand());
    COMMANDS.put("flyspeed", new FlySpeedCommand());
  }

  public static void registerCommand(@NotNull String name, @NotNull Command command, @NotNull Server server){
    COMMANDS.put(name, command);
    CommandRegister.registerCommands(server);
  }

  public static void registerCommand(@NotNull String name, @NotNull Command command){
    COMMANDS.put(name, command);
    CommandRegister.registerCommands();
  }

  public static void registerCommands(@NotNull Server server){
    COMMANDS.forEach(((s, command) -> server.getCommandMap().register(s, "SoulCommands", command)));
  }

  public static void registerCommands(){
    CommandRegister.registerCommands(Bukkit.getServer());
  }

  public static void unregisterCommands(@NotNull Server server){
    server.getCommandMap().clearCommands();
  }

  public static void unregisterCommands(){
    CommandRegister.unregisterCommands(Bukkit.getServer());
  }


}
