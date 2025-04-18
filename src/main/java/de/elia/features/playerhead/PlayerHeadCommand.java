package de.elia.features.playerhead;

import de.elia.features.teleport.otp.OfflineTpCommand;
import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

public class PlayerHeadCommand extends Command {
  protected PlayerHeadCommand(@NotNull String name) {
    super(name);
  }

  public PlayerHeadCommand() {
    this("playerhead");
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
    if(!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }
    if (!player.hasPermission("soulsmp.playerhead") || !player.isOp()) {
      ErrorMessage.noPermission(player);
      return false;
    }
    if (args.length != 1) {
      ErrorMessage.usage("/playerhead [player]", player);
      return false;
    }
    OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(args[0]);
    if(targetPlayer == null){
      ErrorMessage.standard("Spieler nicht gefunden", player);
      return false;
    }

    //create a skull item with the target player's head
    ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
    SkullMeta meta = (SkullMeta) skull.getItemMeta();
    meta.setOwner(targetPlayer.getName());
    skull.setItemMeta(meta);

    //add the skull to the player's inventory
    player.getInventory().addItem(skull);

    Message.mainPrefix("Du hast den Kopf von <#FF9BDF>" + targetPlayer.getName() + "</#FF9BDF> erhalten", player);
    return true;
  }
}
