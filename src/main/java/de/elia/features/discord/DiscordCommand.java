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
    //set components for the message
    Component discordComponent = miniMessage.deserialize("<blue>Discord</blue>").clickEvent(ClickEvent.openUrl("https://discord.gg/soul-smp-minecraft-850364001195261993")).hoverEvent(HoverEvent.showText(miniMessage.deserialize("<grey><italic>https://discord.gg/soul-smp-minecraft-850364001195261993")));
    Component messageComponent = miniMessage.deserialize("<gray>unser Discord » ");
    //send message
    Message.mainPrefix(messageComponent.append(discordComponent), player);

    return true;
  }
}
