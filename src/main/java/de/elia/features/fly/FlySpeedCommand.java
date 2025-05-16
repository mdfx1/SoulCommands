package de.elia.features.fly;

import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import io.papermc.paper.configuration.type.fallback.FallbackValue;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class FlySpeedCommand extends Command {

  private static final String BYPASS_PERMISSION = "soulsmp.flyspeed";

  protected FlySpeedCommand(@NotNull String name) {
    super(name);
  }

  public FlySpeedCommand(){
    this("flyspeed");
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
    if(args.length == 1) {
      try {
        //set speed
        int speed = Integer.parseInt(args[0]);
        //check if speed is between 0 and 100
        if (speed > 99) {
          ErrorMessage.standard("Please enter a number less than 100%", player);
          return false;
        }
        //convert speed to float
        float floatSpeed = (float) (speed * .01);
        //set fly speed
        player.setFlySpeed(floatSpeed);
        Message.mainPrefix("du fliegst nun mit " + speed + "% Geschwindigkeit. <grey>(Standard: 10)", player);
        return true;
      } catch (NumberFormatException e) {
        ErrorMessage.standard(e.getMessage(), player);
        return false;
      }
    } else {
      ErrorMessage.usage("/flyspeed [speed]", player);
      return false;
    }
  }
}
