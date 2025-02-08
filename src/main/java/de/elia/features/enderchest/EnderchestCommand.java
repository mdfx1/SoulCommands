package de.elia.features.enderchest;

import de.elia.utils.ErrorMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EnderchestCommand extends Command {
  public EnderchestCommand(){
    this("enderchest", "", "", List.of("ec"));
  }

  protected EnderchestCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String @NotNull [] args) {
    if(!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }
    //open ender chest for player as inventory
    player.openInventory(player.getEnderChest());
    return false;
  }
}
