package de.elia.features.fly;

import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;

public class FlyCommand extends Command {

  private static final String BYPASS_PERMISSION = "soulsmp.fly";

  protected FlyCommand(@NotNull String name) {
    super(name);
  }

  public FlyCommand(){
    this("fly");
    if (Bukkit.getPluginManager().getPermission(BYPASS_PERMISSION) == null) {
      Bukkit.getPluginManager().addPermission(
        new Permission(BYPASS_PERMISSION, "", PermissionDefault.OP)
      );
    }
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
    if(!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }
    if(!player.hasPermission(BYPASS_PERMISSION)){
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
