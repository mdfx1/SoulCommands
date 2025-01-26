package de.elia.gemmerr.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static de.elia.api.messages.builder.MessageBuilder.message;
import static de.elia.api.messages.builder.MessageBuilder.red;

//This class sends error messages.
//Coded by Gemmerr -> Edited by Elia (Edit MessageBuilder)
public class ErrorMessages {

    public static void noPermission(Player player) {
        message(player, red("Dazu hast du keine Rechte!"));
    }

    public static void noPlayer(CommandSender commandSender) {
      message(commandSender, red("<red>Hierzu musst du ein Spieler sein."));
    }
    public static void sendUsage(String usage, Player player) {
      message(player, red("Usage: " + usage));
    }

    public static void sendNull(String usage, Player player) {
      message(player, red("Null: " + usage));
    }
}
