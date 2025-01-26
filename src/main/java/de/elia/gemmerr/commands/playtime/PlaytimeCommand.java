package de.elia.gemmerr.commands.playtime;

import de.elia.gemmerr.utils.ErrorMessages;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import static de.elia.api.messages.builder.MessageBuilder.*;

//This command sends the play time of a player
public class PlaytimeCommand extends Command {

  public PlaytimeCommand(){
    this("playtime", "Send the playtime of a player", "Use /playtime [PLAYER]", Arrays.asList("pt", "timeplayed"));
  }

  public PlaytimeCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  private void sendUsage(Player player){
    ErrorMessages.sendUsage("/playtime", player);
  }

  @NotNull
  private String shortInteger(int duration, @NotNull Player player) {
    String string = "";
    int hours = 0;
    int minutes = 0;
    int seconds = 0;
    if(duration / 60 / 60 >=1) {
      hours = duration / 60 /60;
      duration = duration - ((duration / 60 / 60) * 60 * 60);
    }
    if(duration / 60 >= 1) {
      minutes = duration / 60;
      duration = duration - ((duration /60)*60);
    }
    if(duration >=1) {
      seconds = duration;
    }
    if(hours!=0) {
      if (hours <= 9) {
        string = string + "0" + hours + "h ";
      } else {
        string = string + hours + "h ";
      }
    }else {
      message(player, red("Time is null!"));
    }
    if(minutes!=0) {
      if (minutes <= 9) {
        string = string + "0" + minutes + "m ";
      } else {
        string = string + minutes + "m ";
      }
    }else{
      message(player, red("Time is null!"));
    }
    if(seconds <=9) {
      string= string+"0"+seconds+"s";
    }else{
      string= string+seconds+"s";
    }
    return string;
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {if (sender instanceof Player player) {
      if(args.length == 0) {
        int playtime = player.getStatistic(Statistic.PLAY_ONE_MINUTE);
        playtime = playtime / 20;
        messageWithPrefix(player, gray("<gray>du hast eine Spielzeit von ").append(darkPurple("<#9545a3>" + shortInteger(playtime, player))).append(gray(".")));
      }else if(args.length == 1) {
        if(player.hasPermission("soulcommands.admin") || player.isOp()) {
          Player target = Bukkit.getPlayer(args[0]);
          if (target == null) {
            ErrorMessages.sendNull(args[0] + " ist nicht online oder existiert nicht!", player);
            return false;
          }
          int playtime = target.getStatistic(Statistic.PLAY_ONE_MINUTE);
          playtime = playtime / 20;
          messageWithPrefix(player, gray("<gray>du hast eine Spielzeit von ").append(darkPurple("<#9545a3>" + shortInteger(playtime, player))).append(gray(".")));
          return true;
        }else {
          ErrorMessages.noPermission(player);
          return false;
        }
      }else {
        this.sendUsage(player);
        return false;
      }
    }
    return false;
  }
}
