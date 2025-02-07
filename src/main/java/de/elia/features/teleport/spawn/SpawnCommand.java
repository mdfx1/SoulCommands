package de.elia.features.teleport.spawn;

import de.elia.Main;
import de.elia.utils.ErrorMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand extends Command {
  protected SpawnCommand(@NotNull String name) {
    super(name);
  }

  public SpawnCommand() {
    this("spawn");
  }

  FileConfiguration config = Main.getInstance().getConfig();

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
    if(!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }
    Location spawn = Bukkit.getWorld(config.getString("default-world")).getSpawnLocation();
    player.teleport(spawn);
    return false;
  }
}
