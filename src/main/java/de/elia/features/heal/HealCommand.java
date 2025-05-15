package de.elia.features.heal;

import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import io.papermc.paper.configuration.GlobalConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;

import java.io.Console;
import java.util.Arrays;
import java.util.List;

public class HealCommand extends Command {

  private static final String BYPASS_PERMISSION = "soulsmp.heal";

  protected HealCommand(@NotNull String name) {
    super(name);
  }

  public HealCommand(){
    this("heal");
    if (Bukkit.getPluginManager().getPermission(BYPASS_PERMISSION) == null) {
      Bukkit.getPluginManager().addPermission(
        new Permission(BYPASS_PERMISSION, "", PermissionDefault.OP)
      );
    }
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String @NotNull [] args) {
    if(!sender.hasPermission(BYPASS_PERMISSION)){
      ErrorMessage.noPermission(sender);
      return false;
    }

    switch (args.length){
      case 0:
        if (!(sender instanceof Player player)){
          ErrorMessage.noPlayer(sender);
          break;
        }
        //set health and food level to 20 for sender
        player.setHealth(20);
        player.setFoodLevel(20);
        Message.mainPrefix("Du hast dich geheilt!", player);
        return true;
      case 1:
        Player target = Bukkit.getPlayerExact(args[0]);
        if(target == null){
          ErrorMessage.standard("Dieser Spieler existiert nicht", sender);
          break;
        }
        //set health and food level to 20 for target
        target.setHealth(20);
        target.setFoodLevel(20);
        Message.mainPrefix("Du hast " + target.getName() + " geheilt!", sender);
        return true;
      default:
        ErrorMessage.usage("/heal [player]", sender);
        break;
    }
    return false;
  }

}
