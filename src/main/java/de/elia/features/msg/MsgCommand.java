package de.elia.features.msg;

import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import io.netty.util.AttributeKey;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MsgCommand extends Command {

  private static final MiniMessage miniMessage = MiniMessage.miniMessage();
  // Speichert die letzte Nachricht für den Reply-Befehl
  private static final Map<Player, Player> lastMessenger = new HashMap<>();

  public MsgCommand() {
    super("msg");
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
    if (!(sender instanceof Player player)) {
      ErrorMessage.noPlayer(sender);
      return false;
    }

    if (args.length < 2) {
      ErrorMessage.standard("Verwendung: /msg <Spieler> <Nachricht>", player);
      return false;
    }

    Player targetPlayer = Bukkit.getPlayerExact(args[0]);
    if (targetPlayer == null) {
      ErrorMessage.standard("Dieser Spieler existiert nicht oder ist offline.", player);
      return false;
    }

    // Nachricht zusammenbauen
    String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
    Component senderMessage = miniMessage.deserialize("<grey>Du -> <#FF9BDF>" + targetPlayer.getName() + "</#FF9BDF>: <white>" + message);
    Component targetMessage = miniMessage.deserialize("<grey><#FF9BDF>" + player.getName() + "</#FF9BDF> -> dir: <white>" + message);

    // Nachrichten senden
    Message.standard(senderMessage, player);
    targetPlayer.playSound(targetPlayer, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
    Message.standard(targetMessage, targetPlayer);

    // Für Reply speichern
    lastMessenger.put(targetPlayer, player);
    return true;
  }

  public static Map<Player, Player> getLastMessenger() {
    return lastMessenger;
  }
}
