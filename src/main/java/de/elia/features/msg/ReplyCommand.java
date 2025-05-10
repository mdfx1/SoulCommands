package de.elia.features.msg;

import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReplyCommand extends Command {

  private static final MiniMessage miniMessage = MiniMessage.miniMessage();

  public ReplyCommand() {
    super("r");
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
    if (!(sender instanceof Player player)) {
      ErrorMessage.noPlayer(sender);
      return false;
    }

    if (args.length < 1) {
      ErrorMessage.standard("Verwendung: /r <Nachricht>", player);
      return false;
    }

    Player targetPlayer = MsgCommand.getLastMessenger().get(player);
    if (targetPlayer == null) {
      ErrorMessage.standard("Du hast niemandem, dem du antworten könntest.", player);
      return false;
    }

    // Nachricht zusammenbauen
    String message = String.join(" ", args);
    Component senderMessage = miniMessage.deserialize("<grey>Du -> <#FF9BDF>" + targetPlayer.getName() + "</#FF9BDF>: <white>" + message);
    Component targetMessage = miniMessage.deserialize("<grey><#FF9BDF>" + player.getName() + "</#FF9BDF> -> dir: <white>" + message);

    // Nachrichten senden
    Message.standard(senderMessage, player);
    targetPlayer.playSound(targetPlayer, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
    Message.standard(targetMessage, targetPlayer);

    // Für Reply speichern
    MsgCommand.getLastMessenger().put(targetPlayer, player);
    return true;
  }
}
