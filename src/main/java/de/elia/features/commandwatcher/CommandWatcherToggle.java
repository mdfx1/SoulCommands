package de.elia.features.commandwatcher;

import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandWatcherToggle extends Command {

  public static ArrayList<Player> cwPlayers = new ArrayList<>();

  public CommandWatcherToggle() {
    this("commandwatcher");
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
    if(!player.hasPermission("soulsmp.commandwatch") && !player.isOp()){
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
