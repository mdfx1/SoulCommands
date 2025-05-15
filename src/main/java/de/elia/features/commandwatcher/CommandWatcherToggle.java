package de.elia.features.commandwatcher;

import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandWatcherToggle extends Command {

  public static ArrayList<Player> cwPlayers = new ArrayList<>();

  private static final String BYPASS_PERMISSION = "soulsmp.commandwatch";


  public CommandWatcherToggle() {
    this("commandwatcher");
    if (Bukkit.getPluginManager().getPermission(BYPASS_PERMISSION) == null) {
      Bukkit.getPluginManager().addPermission(
        new Permission(BYPASS_PERMISSION, "", PermissionDefault.OP)
      );
    }
  }

  protected CommandWatcherToggle(@NotNull String name) {
    super(name);
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {

    if(!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }
    if(!player.hasPermission(BYPASS_PERMISSION)){
      ErrorMessage.noPermission(player);
      return false;
    }
    // Toggle the players CommandWatcher
    if(!cwPlayers.contains(player)){
      //add player to list
      cwPlayers.add(player);
      Message.mainPrefix("Du siehst nun die Commands aller Spieler!", player);
      return true;
    } else {
      //remove player from list
      cwPlayers.remove(player);
      Message.mainPrefix("Du siehst nun nicht mehr alle Commands!", player);
      return true;
    }
  }
}
