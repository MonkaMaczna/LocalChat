package me.flour.rpChatManager.Events;

import me.flour.rpChatManager.data.EphemeralData;
import me.flour.rpChatManager.data.LogChat;
import me.flour.rpChatManager.settings.Settings;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.mineacademy.fo.Common;

import static org.bukkit.Bukkit.getServer;




public class Messenger implements Listener {
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerChat(final AsyncPlayerChatEvent event) {
		final Player player = event.getPlayer();
		String message = event.getMessage();
		final String playername = player.getDisplayName();
		final Integer distance = Settings.DISTANCE;
		final Location center = player.getLocation();
		final Boolean logging = Settings.LOGGING;
		Common.setLogPrefix("[Local Chat Manager]");


		if (EphemeralData.getInstance().checkIfToggledRP().contains(player.getUniqueId())) {


			for (final Player playerTarget : Bukkit.getOnlinePlayers()) {
				final Location location = playerTarget.getLocation();

				if (location.distanceSquared(center) <= distance * distance) {


					if (message.contains("&")) {
						message = Common.colorize(message);
					}


					Common.tell(playerTarget, Settings.RP_PREFIX + " " + Settings.RP_NICK + playername + "&7: " + Settings.RP_COLOR + message);

					Common.log(Settings.RP_PREFIX + " " + Settings.RP_NICK + playername + "&7: " + Settings.RP_COLOR + message);

					if (logging) {
						LogChat.getInstance().logToFile(Settings.RP_PREFIX + " " + Settings.RP_NICK + playername + "&7: " + Settings.RP_COLOR + message);
					}
					event.setCancelled(true);


				}


			}


		} else if (Settings.GLOBAL_OVERRIDE && !EphemeralData.getInstance().checkIfToggledRP().contains(player.getUniqueId())) {

			if (Settings.LUCKPERMS_BOOLEAN && getServer().getPluginManager().getPlugin("LuckPerms") != null) {
				final LuckPerms luckPerms = LuckPermsProvider.get();
				final User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
				final String prefix = user.getCachedData().getMetaData().getPrefix();


				if (message.contains("&")) {
					message = Common.colorize(message);
				}

				message = prefix + " "+ Settings.GLOBAL_NICK + playername + "&l&b: " + Settings.GLOBAL_COLOR + event.getMessage();


			} else if (!Settings.LUCKPERMS_BOOLEAN || getServer().getPluginManager().getPlugin("LuckPerms") == null) {
				message = Settings.GLOBAL_NICK + playername + Settings.GLOBAL_COLOR + ": " + event.getMessage();
				if (Settings.GLOBAL_PREFIX != null) {
					message = Settings.GLOBAL_PREFIX + " " + message;
				}
			}


			if (logging) {
				LogChat.getInstance().logToFile("message");
			}


			Common.broadcast(message);
			Common.log(message);


			event.setCancelled(true);

		}


	}


	}




