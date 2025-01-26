package de.elia.gemmerr.commands.discord;

import de.elia.gemmerr.utils.ErrorMessages;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static de.elia.api.messages.builder.MessageBuilder.*;

//This command sends a message with the discord link
public class DiscordCommand extends Command {

  public DiscordCommand(){
    this("/discord", "Send a link to the discord.", "/discord", List.of("dc"));
  }

  public DiscordCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
    if (sender instanceof Player player) {
      if(args.length == 0) {
        messageWithPrefix(player, gray("unser Discord » ").append(blue("<blue>Discord</blue>")).append(italic("<gray>(click)discord.gg/soul-smp-minecraft-850364001195261993</gray>")));
        return true;
      }else {
        ErrorMessages.sendUsage("/discord", player);
        return false;
      }
    }else {
      ErrorMessages.noPlayer(sender);
      return false;
    }
  }
}
