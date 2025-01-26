package de.elia.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.elia.api.messages.builder.MessageBuilder.message;
import static de.elia.api.messages.builder.MessageBuilder.red;

//This class sends error messages.
//Coded by Gemmerr -> Edited by Elia (Edit MessageBuilder)
public class ErrorMessage {

    public static void noPermission(Player player) {
        Message.errorPrefix("<red>hierzu hast du keine Rechte", player);
    }
    public static void noPlayer(CommandSender commandSender) {
      Message.errorPrefix("<red>hierzu musst du ein Spieler sein", commandSender);
    }
    public static void usage(String message, Player player) {
      message(player, red("Usage: " + message));
      Message.errorPrefix("<red>Usage:" + message + "</red>", player);
    }
    public static void standard(String message, Player player) {
      Message.errorPrefix("<red>" + message, player);
    }
}
