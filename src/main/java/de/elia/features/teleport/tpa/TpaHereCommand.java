package de.elia.features.teleport.tpa;

import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TpaHereCommand extends Command {
  protected TpaHereCommand(@NotNull String name) {
    super(name);
  }

  public TpaHereCommand() {
    this("tpahere");
  }

  MiniMessage miniMessage = MiniMessage.miniMessage();

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
    if (!(sender instanceof Player player)) {
      ErrorMessage.noPlayer(sender);
      return false;
    }

    if (args.length != 1) {
      ErrorMessage.usage("/tpahere <player>", player);
      return false;
    }

    if (player.getName().equals(args[0])) {
      ErrorMessage.standard("du kannst dich nicht selber anfragen", player);
      return false;
    }

    Player targetPlayer = Bukkit.getPlayerExact(args[0]);
    if (targetPlayer == null) {
      ErrorMessage.standard("Dieser Spieler existiert nicht", player);
      return false;
    }

    // Check if player already has a pending request to targetPlayer
    if (TpaCommand.getPendingHere().containsKey(player) && TpaCommand.getPendingHere().get(player).equals(targetPlayer)) {
      ErrorMessage.standard("Du hast bereits eine ausstehende Anfrage an diesen Spieler", player);
      return false;
    }

    // Add player and targetPlayer to pendingHere map
    TpaCommand.getPendingHere().put(player, targetPlayer);
    Message.standard("<grey>tpahere gesendet...", player);

    // Send message to targetPlayer
    Component message = miniMessage.deserialize("du hast eine tpahere von <#FF9BDF>" + player.getName() + "</#FF9BDF> erhalten. ");
    Component slash = miniMessage.deserialize("/");
    Component accept = miniMessage.deserialize("<#FF9BDF>annehmen</#FF9BDF>")
      .clickEvent(ClickEvent.runCommand("/tpaaccept " + player.getName()))
      .hoverEvent(HoverEvent.showText(miniMessage.deserialize("<grey>klick hier")));
    Component deny = miniMessage.deserialize("<#FF9BDF>ablehnen</#FF9BDF>")
      .clickEvent(ClickEvent.runCommand("/tpadeny " + player.getName()))
      .hoverEvent(HoverEvent.showText(miniMessage.deserialize("<grey>klick hier")));
    Message.mainPrefix(message.append(accept).append(slash).append(deny), targetPlayer);

    return true;
  }
}
