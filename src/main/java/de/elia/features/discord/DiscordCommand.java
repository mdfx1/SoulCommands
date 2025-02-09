package de.elia.features.discord;

import de.elia.utils.ErrorMessage;

import de.elia.utils.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import java.util.List;

//This command sends a message with the discord link
public class DiscordCommand extends Command {

  static MiniMessage miniMessage = MiniMessage.miniMessage();

  public DiscordCommand(){
    this("/discord", "Send a link to the discord.", "", List.of("dc"));
  }

  public DiscordCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
    super(name, description, usageMessage, aliases);
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
    if(!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }
    //send discord link
    Message.discord(player);
    return true;
  }
}
