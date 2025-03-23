package de.elia.features.teleport.otp;

import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OfflineTpCommand extends Command {
  protected OfflineTpCommand(@NotNull String name) {
    super(name);
  }

    public OfflineTpCommand(){
        this("otp");
    }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
    if(!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }
    if (!player.hasPermission("soulsmp.offlinetp")){
      ErrorMessage.noPermission(sender);
      return false;
    }
    if(args.length != 1){
      ErrorMessage.usage("/otp [player]", player);
      return false;
    }
    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
    if(offlinePlayer == null){
      ErrorMessage.standard("Spieler nicht gefunden", player);
      return false;
    }
    if(offlinePlayer.getName().equals(player.getName())){
      ErrorMessage.standard("Du kannst dich nicht zu dir selbst teleportieren", player);
      return false;
    }
    //teleport player to offlinePlayer's location
    Message.standard("<grey>teleportiere...", player);
    player.teleport(offlinePlayer.getLocation());
    return true;
  }
}
