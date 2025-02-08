package de.elia.features.teleport.warp;

import de.elia.Main;
import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import io.papermc.paper.configuration.GlobalConfiguration;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WarpCommand extends Command {
  protected WarpCommand(@NotNull String name) {
    super(name);
  }

  public WarpCommand(){
    this("warp");
  }

  FileConfiguration config = Main.getInstance().getConfig();
  private WarpManager warpManager = Main.getWarpManager();

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
    if (!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }
    if(args.length == 1){
      ErrorMessage.usage("/warp [warp]", player);
      return false;
    }
    //get warp location
    Location warpLocation = warpManager.getWarp(args[0]);
    //teleport player to warp location if warp exists
    if(warpLocation != null){
      player.teleport(warpLocation);
      Message.standard("<grey>teleportiere...", player);
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
