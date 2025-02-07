package de.elia.features.teleport.tpa;

import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
        for (Map.Entry<Player, Player> entry : TpaCommand.getPending().entrySet()) {
          if (entry.getValue().equals(player)) {
            TpaCommand.getPending().remove(entry.getKey(), player);
            Message.standard("<grey>anfrage(n) abgelehnt", player);
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

        if(TpaCommand.getPending().get(targetPlayer) != null &&   TpaCommand.getPending().get(targetPlayer).equals(player)){
          TpaCommand.getPending().remove(targetPlayer, player);
          Message.standard("<grey>anfrage abgelehnt", player);
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
