package de.elia.features.teleport.warp;

import de.elia.Main;
import de.elia.features.teleport.TeleportUtils;
import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.N;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SetWarpCommand extends Command {
    protected SetWarpCommand(@NotNull String name) {
        super(name);
    }

    public SetWarpCommand(){
        this("warp");
    }

    private WarpManager warpManager = Main.getWarpManager();

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
        if(!(sender instanceof Player player)){
            ErrorMessage.noPlayer(sender);
            return false;
        }
        if(!player.hasPermission("soulsmp.admin") || !player.isOp()){
            ErrorMessage.noPermission(player);
            return false;
        }
        if(args.length != 1){
            ErrorMessage.usage("/setwarp [warp]", player);
            return false;
        }
        //center warp location on block
        Location warpLocation;
        warpLocation = TeleportUtils.centerPlayerOnBlock(player);
        warpLocation.setPitch(0);
        warpLocation.setYaw(TeleportUtils.normalizeYaw(player.getLocation().getYaw()));

        //set warp
        if(warpManager.setWarp(args[0], warpLocation)){
            warpManager.saveWarps();
            Message.mainPrefix("du hast den Warp <#FF9BDF>" + args[0] + "</#FF9BDF> bei <#FF9BDF>" + warpLocation.getX() + ", " + warpLocation.getY() + ", " + warpLocation.getZ() + " (" + warpLocation.getYaw() + ") </#FF9BDF> gesetzt", player);
            return true;
        }
        ErrorMessage.standard("dieser Warp existiert bereits an dieser Stelle", player);
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

