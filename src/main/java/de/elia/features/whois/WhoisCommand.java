package de.elia.features.whois;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import de.elia.Main;
import de.elia.systemclasses.DatabaseManager;
import de.elia.utils.ErrorMessage;
import de.elia.utils.Message;
import de.elia.utils.MessageUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryMode;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WhoisCommand extends Command {
  public WhoisCommand(){
    this("whois");
  }

  protected WhoisCommand(@NotNull String name) {
    super(name);
  }

  LuckPerms luckPerms = LuckPermsProvider.get();

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String @NotNull [] args) {
    if(!(sender instanceof Player player)){
      ErrorMessage.noPlayer(sender);
      return false;
    }
    if(!player.hasPermission("soulsmp.admin") || !player.isOp()){
      ErrorMessage.noPermission(player);
      return false;
    }
    if(args.length != 1){
      ErrorMessage.usage("/whois [player]", player);
      return false;
    }
    Player targetPlayer = Bukkit.getPlayerExact(args[0]);
    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);

    if(targetPlayer == null){
      //only for offline players
      Message.mainPrefix("<#FF9BDF>" + offlinePlayer.getName() + "<grey>'s info:", player);
      if(offlinePlayer.hasPlayedBefore()){
        //only if player has played before

        //send Coords
        int x = offlinePlayer.getLocation().getBlockX();
        int y = offlinePlayer.getLocation().getBlockZ();
        int z = offlinePlayer.getLocation().getBlockZ();
        Message.standard("<#FF9BDF>Coordinates: <grey>" + x + ", " + y + ", " + z, player);


        //send playtime and punishment type
        Message.standard("<#FF9BDF>Playtime<grey>: " + MessageUtils.shortInteger(offlinePlayer.getStatistic(Statistic.PLAY_ONE_MINUTE)), player);
        Message.standard("<#FF9BDF>Punishment<grey>: " + Main.getDatabaseManager().getPunishmentType(offlinePlayer.getName()), player);
        Message.standard("<#FF9BDF>Last Seen<grey>: " + MessageUtils.shortDate(offlinePlayer.getLastSeen()), player);
      }
      else {
        Message.standard("<#FF9BDF>Last Seen<grey>: nicht zuvor gesehen", player);
      }
      return true;
    }
    Message.mainPrefix("<#FF9BDF>" + targetPlayer.getName() + "<grey>'s info:", player);
    //send ip
    //TODO add region (maybe with GeoLite db)
    Message.standard("<#FF9BDF>IP<grey>: " + targetPlayer.getAddress().getAddress().getHostAddress(), player);

    //send Coords
    int x = player.getLocation().getBlockX();
    int y = player.getLocation().getBlockZ();
    int z = player.getLocation().getBlockZ();
    Message.standard("<#FF9BDF>Coordinates: <grey>" + x + ", " + y + ", " + z, player);

    //send playtime and punishment type
    Message.standard("<#FF9BDF>Playtime<grey>: " + MessageUtils.shortInteger(targetPlayer.getStatistic(Statistic.PLAY_ONE_MINUTE)), player);
    Message.standard("<#FF9BDF>Punishment<grey>: " + Main.getDatabaseManager().getPunishmentType(targetPlayer.getName()), player);
    Message.standard("<#FF9BDF>Rank<grey>: <reset>" + getPrefix(targetPlayer), player);
    return true;
  }
  public String getPrefix(Player player) {
    //grab user from luckperms
    User user = luckPerms.getUserManager().getUser(player.getUniqueId());
    if (user != null) {
      //grab prefix from user
      CachedMetaData metaData = user.getCachedData().getMetaData();
      return metaData.getPrefix();
    }
    //empty string if no prefix
    return "";
  }
}
