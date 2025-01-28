package de.elia.utils;

import de.elia.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message {

  static MiniMessage miniMessage = MiniMessage.miniMessage();

  public static void mainPrefix(String messageIn, Player playerIn) {
    Component messageOut = miniMessage.deserialize(messageIn);
    playerIn.sendMessage(miniMessage.deserialize("<dark_grey>[</dark_grey><gradient:#783D9F:#FF88FD><bold>SoulSMP</bold><dark_grey>]</dark_grey> ").append(messageOut));
  }
  public static void mainPrefix(String messageIn, CommandSender sender) {
    Component messageOut = miniMessage.deserialize(messageIn);
    sender.sendMessage(miniMessage.deserialize("<dark_grey>[</dark_grey><gradient:#783D9F:#FF88FD><bold>SoulSMP</bold><dark_grey>]</dark_grey> ").append(messageOut));
  }
  public static void mainPrefix(Component componentMessage, Player playerIn) {
    playerIn.sendMessage(miniMessage.deserialize("<dark_grey>[</dark_grey><gradient:#783D9F:#FF88FD><bold>SoulSMP</bold><dark_grey>]</dark_grey> ").append(componentMessage));
  }
  public static void mainPrefix(Component componentMessage, CommandSender sender) {
    sender.sendMessage(miniMessage.deserialize("<dark_grey>[</dark_grey><gradient:#783D9F:#FF88FD><bold>SoulSMP</bold><dark_grey>]</dark_grey> ").append(componentMessage));
  }
  public static void cwPrefix(String messageIn, Player playerIn) {
    Component messageOut = miniMessage.deserialize(messageIn);
    playerIn.sendMessage(miniMessage.deserialize("<dark_grey>[</dark_grey><gradient:#307DBB:#69BEFF><bold>CW</bold><dark_grey>]</dark_grey> ").append(messageOut));
  }
  public static void errorPrefix(String messageIn, Player playerIn) {
    Component messageOut = miniMessage.deserialize(messageIn);
    playerIn.sendMessage(miniMessage.deserialize("<dark_grey>[</dark_grey><gradient:#A01B1B:#E75656><bold>Error</bold><dark_grey>]</dark_grey> ").append(messageOut));
  }
  public static void errorPrefix(String messageIn, CommandSender sender) {
    Component messageOut = miniMessage.deserialize(messageIn);
    sender.sendMessage(miniMessage.deserialize("<dark_grey>[</dark_grey><gradient:#A01B1B:#E75656><bold>Error</bold><dark_grey>]</dark_grey> ").append(messageOut));
  }
  public static void standard(String messageIn, Player playerIn) {
    Component messageOut = miniMessage.deserialize(messageIn);
    playerIn.sendMessage(messageOut);
  }
}
