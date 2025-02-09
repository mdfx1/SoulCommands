package de.elia.features.teleport.back;

import de.elia.Main;
import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BackCommand extends Command {
  protected BackCommand(@NotNull String name) {
    super(name);
  }

  public BackCommand(){
    this("back");
  }

  private BackManager backManager = Main.getBackManager();

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
    if(!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }
    //only teleport if player has a previous location
    if(!backManager.hasTeleport(player)) {
      ErrorMessage.standard("Du kannst dich zu keiner vorherigen Position teleportieren", player);
      return false;
    }
    //teleport player to previous location
    player.teleport(backManager.getTeleportLocation(player));
    Message.standard("<grey>teleportiere...", player);
    return true;
  }
}
