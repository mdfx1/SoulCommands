package de.elia.features.teleport.tpa;

import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
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

import java.util.HashMap;
import java.util.Map;

public class TpaCommand extends Command {
  protected TpaCommand(@NotNull String name) {
    super(name);
  }

  public TpaCommand(){
    this("tpa");
  }

  MiniMessage miniMessage = MiniMessage.miniMessage();
  //map for pending tpa requests
  private static Map<Player, Player> pending = new HashMap<>();
  private static Map<Player, Player> pendingHere = new HashMap<>();

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
    if(!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }

    if(player.getName().equals(args[0])){
      ErrorMessage.standard("du kannst dich nicht selber anfragen", player);
      return false;
    }

    //check if targetPlayer exists
    Player targetPlayer = Bukkit.getPlayerExact(args[0]);
    if (targetPlayer == null) {
      ErrorMessage.standard("Dieser Spieler existiert nicht", player);
      return false;
    }
    //check if player already has a pending request to targetPlayer
    if (pending.containsKey(player) && pending.containsValue(targetPlayer)) {
      ErrorMessage.standard("Du hast bereits eine ausstehende Anfrage an diesen Spieler", player);
      return false;
    }

    //add player and targetPlayer to pending
    pending.put(player, targetPlayer);
    Message.standard("<grey>tpa gesendet...", player);

    //send message to targetPlayer
    Component message = miniMessage.deserialize("du hast eine tpa von <#FF9BDF>" + player.getName() + "</#FF9BDF> erhalten. ");
    Component slash = miniMessage.deserialize("/");
    Component accept = miniMessage.deserialize("<#FF9BDF>annehmen</#FF9BDF>").clickEvent(ClickEvent.runCommand("/tpaaccept " + player.getName())).hoverEvent(HoverEvent.showText(miniMessage.deserialize("<grey>klick hier")));
    Component deny = miniMessage.deserialize("<#FF9BDF>ablehnen</#FF9BDF>").clickEvent(ClickEvent.runCommand("/tpadeny " + player.getName())).hoverEvent(HoverEvent.showText(miniMessage.deserialize("<grey>klick hier")));
    Message.mainPrefix(message.append(accept).append(slash).append(deny), targetPlayer);

    targetPlayer.playSound(targetPlayer, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);

    return false;
  }


  public static Map<Player, Player> getPending() {
    return pending;
  }

  public static Map<Player, Player> getPendingHere() {
    return pendingHere;
  }

}
