package de.elia.elia.chat;

import io.papermc.paper.event.player.AsyncChatEvent;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.jetbrains.annotations.NotNull;

//This event overrides the message L to W
public class ChatListener implements Listener {

  @EventHandler
  public void onChat(@NotNull AsyncChatEvent event){
    Player player = event.getPlayer();
    MiniMessage miniMessage = MiniMessage.miniMessage();
    Component message = event.message();
    if(message.equals(miniMessage.deserialize("L"))){
      event.message(miniMessage.deserialize("<L>", Placeholder.parsed("L", "W")));
    }
  }
}
