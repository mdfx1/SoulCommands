package de.elia.commandwatcher;

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
    this("commandwatcher", "You can enable or disable the command watcher", "usageMessage", new ArrayList<>());
  }

  protected CommandWatcherToggle(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
    if(!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }
    if(!player.hasPermission("soulsmp.commandwatcher") || !player.isOp()){
      ErrorMessage.noPermission(player);
      return false;
    }
    if(!cwPlayers.contains(player)){
      cwPlayers.add(player);
      Message.mainPrefix("Du siehst nun die Commands aller Spieler!", player);
      return true;
    } else {
      cwPlayers.remove(player);
      Message.mainPrefix("Du siehst nun nicht mehr alle Commands!", player);
      return true;
    }
  }
}
