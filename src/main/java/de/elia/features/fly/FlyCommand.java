package de.elia.features.fly;

import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FlyCommand extends Command {
  protected FlyCommand(@NotNull String name) {
    super(name);
  }

  public FlyCommand(){
    this("fly");
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
    if(!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }
    if(!player.hasPermission("soulsmp.fly") && !player.isOp()){
      ErrorMessage.noPermission(player);
      return false;
    }
    //toggle fly for player
    if(player.getAllowFlight()){
      //player can fly
      player.setAllowFlight(false);
      Message.mainPrefix("du kannst nun nicht mehr fliegen", player);
    } else {
      //player can't fly
      player.setAllowFlight(true);
      Message.mainPrefix("du kannst nun fliegen", player);
    }
    return true;

    //TODO fly after relogging
  }
}
