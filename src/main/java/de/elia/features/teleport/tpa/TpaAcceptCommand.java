package de.elia.features.teleport.tpa;

import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import de.elia.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class TpaAcceptCommand extends Command {
  protected TpaAcceptCommand(@NotNull String name) {
    super(name);
  }

  public TpaAcceptCommand() {
    this("tpaaccept");
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
    if(!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }

    switch (args.length){
      case 0:
        //accept all open tpa requests
        boolean requested = false;
        for (Map.Entry<Player, Player> entry : TpaCommand.getPending().entrySet()) {
          if (entry.getValue().equals(player)) {
            //all entry's with player as target
            entry.getKey().teleport(player);
            TpaCommand.getPending().remove(entry.getKey(), player);
            Message.standard("<grey>teleportiere...", entry.getKey());
            requested = true;
          }
        }
        if(!requested){
          ErrorMessage.standard("Niemand möchte sich zu dir teleportieren", player);
        }
        break;
      case 1:
        Player targetPlayer = Bukkit.getPlayerExact(args[0]);
        if (targetPlayer == null) {
          ErrorMessage.standard("Dieser Spieler existiert nicht", player);
          return false;
        }

        //only accept if player has a pending request from targetPlayer
        if(TpaCommand.getPending().get(targetPlayer) != null && TpaCommand.getPending().get(targetPlayer).equals(player)){
          TpaCommand.getPending().remove(targetPlayer, player);
          targetPlayer.teleport(player);
          Message.standard("<grey>teleportiere...", targetPlayer);
        } else{
          ErrorMessage.standard("Dieser Spieler möchte sich nicht zu dir teleportieren", player);
        }
        break;
      default:
        ErrorMessage.usage("/tpaaccept [player]", player);
        return false;
    }
    return true;
  }
}
