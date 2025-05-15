package de.elia.features.teleport.warp;

import de.elia.Main;
import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DeleteWarpCommand extends Command {

  private static final String BYPASS_PERMISSION = "soulsmp.delwarp";

  protected DeleteWarpCommand(@NotNull String name) {
    super(name);
  }

  public DeleteWarpCommand(){
    this("warp");
    if (Bukkit.getPluginManager().getPermission(BYPASS_PERMISSION) == null) {
      Bukkit.getPluginManager().addPermission(
        new Permission(BYPASS_PERMISSION, "", PermissionDefault.OP)
      );
    }
  }

  private WarpManager warpManager = Main.getWarpManager();

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
    if(args.length != 1){
      ErrorMessage.usage("/delwarp [warp]", player);
      return false;
    }
    //remove warp if it exists
    if(warpManager.removeWarp(args[0])){
      //save warps
      warpManager.saveWarps();
      Message.mainPrefix("Du hast den Warp <#FF9BDF>" + args[0] + "</#FF9BDF> gelöscht", player);
      return true;
    }
    ErrorMessage.standard("Dieser Warp existiert nicht", player);
    return false;
  }
  @Override
  public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
    if(args.length == 1) {
      List<String> list = new ArrayList<String>(warpManager.getWarps().keySet());
      return list;
    }
    return null;
  }
}
