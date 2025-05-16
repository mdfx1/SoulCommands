package de.elia.features.sign;

import de.elia.utils.ErrorMessage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SignCommand extends Command {

  private static final String BYPASS_PERMISSION = "soulsmp.sign";

  protected SignCommand(String name) {
    super(name);
  }

  public SignCommand() {
    this("sign");
    if (Bukkit.getPluginManager().getPermission(BYPASS_PERMISSION) == null) {
      Bukkit.getPluginManager().addPermission(
        new Permission(BYPASS_PERMISSION, "", PermissionDefault.OP)
      );
    }
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
    if (!(sender instanceof Player player)) {
      ErrorMessage.noPlayer(sender);
      return false;
    }
    if (!player.hasPermission(BYPASS_PERMISSION)) {
      ErrorMessage.noPermission(player);
      return false;
    }
    if (args.length == 0) {
      ErrorMessage.usage("/sign [text]", player);
      return false;
    }
    //get item in main hand
    ItemStack item = player.getInventory().getItemInMainHand();

    if (item.getType().isAir()) {
      player.sendMessage("Du musst ein Item in der Hand halten, um es zu signieren.");
      return true;
    }

    //get item meta
    ItemMeta meta = item.getItemMeta();
    if (meta == null) {
      ErrorMessage.standard("Das Item kann nicht signiert werden.", player);
      return false;
    }
    List<String> lore = new ArrayList<>();

    //set lore / item meta
    String signatureText = String.join(" ", args);
    String coloredSignatureText = signatureText.replace("&", "§");

    lore.add(coloredSignatureText);
    meta.setLore(lore);
    item.setItemMeta(meta);

    return true;
  }
}
