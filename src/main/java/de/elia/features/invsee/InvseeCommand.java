package de.elia.features.invsee;

import de.elia.utils.ErrorMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class InvseeCommand extends Command {
  public InvseeCommand() {
    this("invsee");
  }

  protected InvseeCommand(@NotNull String name) {
    super(name);
  }

  public ArrayList<View> views = new ArrayList<>();

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String @NotNull [] args) {
    if(!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }
    if(!player.hasPermission("soulsmp.admin") || !player.isOp()){
      ErrorMessage.noPermission(player);
      return false;
    }
    if(args.length != 1){
      ErrorMessage.usage("/invsee [player]", player);
      return false;
    }
    Player targetPlayer = Bukkit.getPlayerExact(args[0]);
    if (targetPlayer == null) {
      ErrorMessage.standard("Dieser Spieler existiert nicht", player);
      return false;
    }

    InventoryHolder inventoryHolder = new PlayerInventoryHolder(targetPlayer);
    player.openInventory(inventoryHolder.getInventory());
    views.add(new View(player, targetPlayer));
    return true;
  }
}
