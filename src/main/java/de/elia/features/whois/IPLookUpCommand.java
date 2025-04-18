package de.elia.features.whois;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.elia.Main;
import de.elia.systemclasses.DatabaseManager;
import de.elia.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.xml.crypto.Data;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class IPLookUpCommand extends Command {

  protected IPLookUpCommand(@NotNull String name) {
    super(name);
  }

  public IPLookUpCommand() {
    this("iplookup");
  }

  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
    if (!sender.isOp() && !sender.hasPermission("soulplusw.iplookup")) {
      sender.sendMessage("Du hast keine Berechtigung, diesen Befehl auszufÃ¼hren.");
      return false;
    }

    // Check if the sender is a player

    if (args.length == 0) {
      sender.sendMessage("Du musst einen Spielernamen angeben.");
      return false;
    }

    String targetName = args[0];

    // Check if the target player is in the protected list

    String[] protectedPlayers = {"Wate01", "AkaKirby", "Gemmer", "im_elia"};
    for (String protectedPlayer : protectedPlayers) {
      if (targetName.equalsIgnoreCase(protectedPlayer)) {
        sender.sendMessage("Spieler nicht gefunden.");
        return false;
      }
    }

    // Get the IP address of the target player

    String ip = Main.getDatabaseManager().getIP(targetName);

    // Check if the IP address is null

    if (ip == null) {
      sender.sendMessage("Konnte die IP-Adresse des Spielers nicht abrufen.");
      return false;
    }

    // Asynchronous task to prevent blocking the main thread

    CompletableFuture.runAsync(() -> {
      try {
        URL url = new URL("http://ip-api.com/json/" + ip + "?fields=status,message,country,regionName,city,isp,proxy,mobile,lat,lon,timezone");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
          JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
          if (!"success".equals(json.get("status").getAsString())) {
            sender.sendMessage("Fehler: " + json.get("message").getAsString());
            return;
          }
          Message.mainPrefix("<#FF9BDF>" + targetName + "<grey>'s ip info:", sender);
          Message.standard("<#FF9BDF>IP-Adresse: <grey>" + ip, sender);
          Message.standard("<#FF9BDF>Land: <grey>" + json.get("country").getAsString(), sender);
          Message.standard("<#FF9BDF>Region: <grey>" + json.get("regionName").getAsString(), sender);
          Message.standard("<#FF9BDF>Stadt: <grey>" + json.get("city").getAsString(), sender);
          Message.standard("<#FF9BDF>ISP: <grey>" + json.get("isp").getAsString(), sender);
          Message.standard("<#FF9BDF>Proxy: <grey>" + (json.get("proxy").getAsBoolean() ? "Ja" : "Nein"), sender);
          Message.standard("<#FF9BDF>Mobil: <grey>" + (json.get("mobile").getAsBoolean() ? "Ja" : "Nein"), sender);
          Message.standard("<#FF9BDF>Koordinaten: <grey>" + json.get("lat").getAsDouble() + ", " + json.get("lon").getAsDouble(), sender);
          Message.standard("<#FF9BDF>Zeitzone: <grey>" + json.get("timezone").getAsString(), sender);
        }
      } catch (Exception e) {
        sender.sendMessage("Fehler beim Abrufen der IP-Daten");
        e.printStackTrace();
      }
    });
    return true;
  }
}
