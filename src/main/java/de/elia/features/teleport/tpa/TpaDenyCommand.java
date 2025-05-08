package de.elia.features.teleport.tpa;

import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.sql.Struct;
import java.util.Map;

public class TpaDenyCommand extends Command {
  protected TpaDenyCommand(@NotNull String name) {
    super(name);
  }

  public TpaDenyCommand(){
    this("tpadeny");
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
    if(!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }
    switch (args.length){
      case 0:
        boolean requested = false;
        //deny all open tpa and tpahere requests
        // Handle TPA requests
        for (Map.Entry<Player, Player> entry : TpaCommand.getPending().entrySet()) {
          if (entry.getValue().equals(player)) {
            Player targetPlayer = entry.getKey();
            TpaCommand.getPending().remove(targetPlayer, player);
            Message.standard("<grey>anfrage von <#FF9BDF>" + targetPlayer.getName() + "</#FF9BDF> abgelehnt", player);
            Message.mainPrefix("deine Anfrage an <#FF9BDF>" + player.getName() + "</#FF9BDF> wurde abgelehnt", targetPlayer);
            requested = true;
          }
        }
        // Handle TPAHere requests
        for (Map.Entry<Player, Player> entry : TpaCommand.getPendingHere().entrySet()) {
          if (entry.getValue().equals(player)) {
            Player targetPlayer = entry.getKey();
            TpaCommand.getPendingHere().remove(targetPlayer, player);
            Message.standard("<grey>tpahere von <#FF9BDF>" + targetPlayer.getName() + "</#FF9BDF> abgelehnt", player);
            Message.mainPrefix("deine tpahere an <#FF9BDF>" + player.getName() + "</#FF9BDF> wurde abgelehnt", targetPlayer);
            requested = true;
          }
        }
        if(!requested){
          ErrorMessage.standard("Niemand möchte sich zu dir teleportieren oder dich zu sich teleportieren", player);
        }
        break;
      case 1:
        //deny tpa or tpahere request from targetPlayer
        Player targetPlayer = Bukkit.getPlayerExact(args[0]);
        if (targetPlayer == null) {
          ErrorMessage.standard("Dieser Spieler existiert nicht", player);
          return false;
        }
        // Check for TPA request
        if(TpaCommand.getPending().get(targetPlayer) != null && TpaCommand.getPending().get(targetPlayer).equals(player)){
          TpaCommand.getPending().remove(targetPlayer, player);
          Message.standard("<grey>anfrage abgelehnt", player);
          Message.mainPrefix("deine Anfrage an <#FF9BDF>" + player.getName() + "</#FF9BDF> wurde abgelehnt", targetPlayer);
          return true;
        }
        // Check for TPAHere request
        if(TpaCommand.getPendingHere().get(targetPlayer) != null && TpaCommand.getPendingHere().get(targetPlayer).equals(player)){
          TpaCommand.getPendingHere().remove(targetPlayer, player);
          Message.standard("<grey>tpahere abgelehnt", player);
          Message.mainPrefix("deine tpahere an <#FF9BDF>" + player.getName() + "</#FF9BDF> wurde abgelehnt", targetPlayer);
          return true;
        }

        ErrorMessage.standard("Dieser Spieler möchte sich nicht zu dir teleportieren oder dich zu sich teleportieren", player);
        break;
      default:
        ErrorMessage.usage("/tpadeny [player]", player);
        return false;
    }
    return true;
  }
}
