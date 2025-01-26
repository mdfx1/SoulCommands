package de.elia.gemmerr.commandwatcher;

import de.elia.api.messages.builder.MessageBuilder;
import de.elia.gemmerr.utils.ErrorMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandWatcherToggle extends Command {

  public static ArrayList<Player> players = new ArrayList<>();

  public CommandWatcherToggle() {
    this("commandwatcher", "You can enable or disable the command watcher", "usageMessage", new ArrayList<>());
  }

  protected CommandWatcherToggle(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
    if(sender instanceof Player player) {
      if(player.hasPermission("soulsmp.commandwatcher")) {
        if (args.length == 0) {
          //Spieler sieht alle Commands:
          if (players.contains(player)) {
            players.remove(player);
            MessageBuilder.message(player, MessageBuilder.gray("Du siehst nun nicht mehr alle Commands!"));
          }
          //Spieler sieht nicht mehr alle Commands
          else {
            players.add(player);
            MessageBuilder.message(player, MessageBuilder.gray("Du siehst nun die Commands aller Spieler!"));
          }
        }else {
          ErrorMessages.sendUsage("/commandwatcher", player);
        }
      }else{
        ErrorMessages.noPermission(player);
      }
    }
    else {
      ErrorMessages.noPlayer(sender);
    }
    return false;
  }
}
