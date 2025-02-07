package de.elia.features.teleport.spawn;

import de.elia.Main;
import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetSpawnCommand extends Command {
  protected SetSpawnCommand(@NotNull String name) {
    super(name);
  }

  public SetSpawnCommand (){
    this("setspawn");
  }

  FileConfiguration config = Main.getInstance().getConfig();

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
    if (!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }
    if(!player.hasPermission("soulsmp.admin") || !player.isOp()){
      ErrorMessage.noPermission(player);
      return false;
    }
    int x;
    int y;
    int z;
    Float angle;
    switch (args.length) {
      case 0:
        x = player.getLocation().getBlockX();
        y = player.getLocation().getBlockY();
        z = player.getLocation().getBlockZ();
        angle = player.getLocation().getYaw();
        Bukkit.getWorld(config.getString("default-world")).setSpawnLocation(x, y, z, angle);
        Message.mainPrefix("der Spawn wurde bei " + x + ", " + y + ", " + ", " + z + " (" + angle + ") gesetzt", player);
        break;
      case 3:
        try {
          x = Integer.parseInt(args[0]);
          y = Integer.parseInt(args[0]);
          z = Integer.parseInt(args[0]);
        } catch (NumberFormatException e){
          ErrorMessage.standard(e.getMessage(), player);
          return false;
        }
        Bukkit.getWorld(config.getString("default-world")).setSpawnLocation(x, y, z);
        Message.mainPrefix("der Spawn wurde bei " + x + ", " + y + ", " + ", " + z + " gesetzt", player);
        break;
      case 4:
        try {
          x = Integer.parseInt(args[0]);
          y = Integer.parseInt(args[0]);
          z = Integer.parseInt(args[0]);
          angle = Float.parseFloat(args[0]);
        } catch (NumberFormatException e){
          ErrorMessage.standard(e.getMessage(), player);
          return false;
        }
        Bukkit.getWorld(config.getString("default-world")).setSpawnLocation(x, y, z, angle);
        Message.mainPrefix("der Spawn wurde bei " + x + ", " + y + ", " + ", " + z + " (" + angle + ") gesetzt", player);
        break;
      default:
        ErrorMessage.usage("/setspawn [x] [y] [z] [angle]", player);
    }
    return true;
  }
}
