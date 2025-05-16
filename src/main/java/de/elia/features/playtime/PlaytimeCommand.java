package de.elia.features.playtime;

import de.elia.utils.ErrorMessage;

import de.elia.utils.Message;
import de.elia.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import static de.elia.api.messages.builder.MessageBuilder.*;

//This command sends the play time of a player
public class PlaytimeCommand extends Command {

  private static final String BYPASS_PERMISSION = "soulsmp.playtime";

  public PlaytimeCommand(){
    this("playtime");
    if (Bukkit.getPluginManager().getPermission(BYPASS_PERMISSION) == null) {
      Bukkit.getPluginManager().addPermission(
        new Permission(BYPASS_PERMISSION, "", PermissionDefault.OP)
      );
    }
  }

  public PlaytimeCommand(@NotNull String name) {
    super(name);
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
    boolean hasPermission = false;
    if(!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }
    //set permission for later use
    if(player.hasPermission(BYPASS_PERMISSION)){
      hasPermission = true;
    }
    switch (args.length){
      case 0:
        //get playtime of player
        int playtime = player.getStatistic(Statistic.PLAY_ONE_MINUTE);
        playtime = playtime / 20;
        //send converted playtime to hours and minutes
        Message.mainPrefix("du hast eine Spielzeit von <#FF9BDF>" + MessageUtils.shortInteger(playtime) + "<reset>.", player);
        return true;
      case 1:
        if(!hasPermission){
          ErrorMessage.noPermission(player);
          break;
        }
        Player targetPlayer = Bukkit.getPlayerExact(args[0]);
        if (targetPlayer == null) {
          ErrorMessage.standard("Dieser Spieler existiert nicht", player);
          break;
        }
        //get playtime of target player
        int targetPlaytime = targetPlayer.getStatistic(Statistic.PLAY_ONE_MINUTE);
        targetPlaytime = targetPlaytime / 20;
        //send converted playtime to hours and minutes of target player
        Message.mainPrefix("<#FF9BDF>" + targetPlayer.getName() + "</#FF9BDF> hat eine Spielzeit von <#FF9BDF>" + MessageUtils.shortInteger(targetPlaytime) + "<reset>.", player);
        return true;

      default:
        //send usage according to permission
        if(hasPermission){
          ErrorMessage.usage("/playtime [Player]", player);
          break;
        }
        ErrorMessage.usage("/playtime", player);
        break;
    }
    return false;
  }


}
