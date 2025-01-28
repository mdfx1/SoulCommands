package de.elia.features.whois;

import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WhoisCommand extends Command {
  public WhoisCommand(){
    this("whois");
  }

  protected WhoisCommand(@NotNull String name) {
    super(name);
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String @NotNull [] args) {
    if(!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }
    if(!player.hasPermission("soulsmp.admin") || !player.isOp()){
      ErrorMessage.noPermission(player);
    }
    if(args.length != 1){
      ErrorMessage.usage("/whois [player]", player);
    }

    Player targetPlayer = Bukkit.getPlayerExact(args[0]);
    if (targetPlayer == null) {
      ErrorMessage.standard("Dieser Spieler existiert nicht", player);
      return false;
    }

    Message.mainPrefix("<#FF9BDF>" + targetPlayer + "<grey>:", player);
    Message.standard("<#FF9BDF>IP<grey>: " + targetPlayer.getAddress().getAddress().getHostAddress(), player);
    Message.standard("<#FF9BDF>Region<grey>: " + targetPlayer.getAddress().getAddress().getHostAddress(), player);
    Message.standard("<#FF9BDF>Coordinates<grey>: " + targetPlayer.getAddress().getAddress().getHostAddress(), player);
    Message.standard("<#FF9BDF>Playtime<grey>: " + targetPlayer.getAddress().getAddress().getHostAddress(), player);
    Message.standard("<#FF9BDF>Punishment<grey>: " + targetPlayer.getAddress().getAddress().getHostAddress(), player);

    return true;
  }
}
