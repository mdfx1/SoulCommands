package de.elia.features.heal;

import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import io.papermc.paper.configuration.GlobalConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.Console;
import java.util.Arrays;
import java.util.List;

public class HealCommand extends Command {
  protected HealCommand(@NotNull String name) {
    super(name);
  }

  public HealCommand(){
    this("heal");
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String @NotNull [] args) {
    if(!sender.hasPermission("soulsmp.admin") || !sender.isOp()){
      ErrorMessage.noPermission(sender);
      return false;
    }

    switch (args.length){
      case 0:
        if (!(sender instanceof Player player)){
          ErrorMessage.noPlayer(sender);
          break;
        }
        player.setHealth(20);
        player.setFoodLevel(20);

        //TODO clear bad effects (like honey)

        Message.mainPrefix("Du hast dich geheilt!", player);
        return true;
      case 1:
        Player target = Bukkit.getPlayerExact(args[0]);
        if(target == null){
          ErrorMessage.standard("Dieser Spieler existiert nicht", sender);
          break;
        }
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
