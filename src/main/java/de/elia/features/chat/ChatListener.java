package de.elia.features.chat;

import de.elia.utils.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.net.http.WebSocket;

public class ChatListener implements Listener {
  MiniMessage miniMessage = MiniMessage.miniMessage();
  LuckPerms luckPerms = LuckPermsProvider.get();
  @EventHandler
  public void onChat(AsyncPlayerChatEvent event){
    Player player = event.getPlayer();
    //event.setFormat(PlaceholderAPI.setPlaceholders(player, "&r%luckperms-prefix% &7%1$s » "));
    event.setCancelled(true);

    String rawMessage = event.getMessage();
    String luckpermsPrefix = getPrefix(player);

    for (Player onlinePlayer : Bukkit.getOnlinePlayers()){
      Message.standard(luckpermsPrefix + " <grey>»</grey> " + rawMessage, onlinePlayer);
    }
  }

  public String getPrefix(Player player) {
    User user = luckPerms.getUserManager().getUser(player.getUniqueId());
    if (user != null) {
      CachedMetaData metaData = user.getCachedData().getMetaData();
      return metaData.getPrefix();
    }
    return ""; // Leerer String, falls kein Prefix gefunden wurde
  }

}
