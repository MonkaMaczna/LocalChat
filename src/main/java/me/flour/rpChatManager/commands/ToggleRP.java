package me.flour.rpChatManager.commands;


import me.flour.rpChatManager.data.EphemeralData;
import me.flour.rpChatManager.data.LogChat;
import me.flour.rpChatManager.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleCommand;

public class ToggleRP extends SimpleCommand {
	public ToggleRP(final String label) {
		super(Settings.LOCAL_LABEL);
	}


	@Override
	protected void onCommand() {


		checkConsole();
		checkPerm(Settings.RP_PERM);

		if (args.length > 0) {


			final Player player = getPlayer();
			final Integer distance = Settings.DISTANCE;
			final Location center = player.getLocation();
			final String playername = player.getDisplayName();
			final Boolean logging = Settings.LOGGING;



			final StringBuilder builder = new StringBuilder();
			for (final String arg : args) {
				builder.append(arg).append(" ");
			}
			String message = builder.toString().trim();


			for (final Player playerTarget : Bukkit.getOnlinePlayers()) {
				final Location location = playerTarget.getLocation();
				// if player is in distance of player executes block
				if (location.distanceSquared(center) <= distance * distance) {


					// colorizes message if contains color coded symbols
					if (message.contains("&")) {
						message = Common.colorize(message);
					}
					

					Common.tell(playerTarget, Settings.RP_PREFIX + " " + playername + "&7: " + Settings.RP_COLOR + message);

					// logs message to the console
					Common.log(Settings.RP_PREFIX + " " + playername + "&7: " + Settings.RP_COLOR + message);

					if (logging) {
						// checks if logging is on in settings; if true logs the file to the logs file in plugin folder
						LogChat.getInstance().logToFile(Settings.RP_PREFIX + " " + playername + "&7: " + Settings.RP_COLOR + message);
					}


				}


			}

		} else if (args.length == 0) {
			final Player playertwo = getPlayer();
			addToRp(playertwo);
		}


	}


	private void addToRp(final Player player) {
		final Player playe = player;
		if (!EphemeralData.getInstance().checkIfToggledRP().contains(playe.getUniqueId())) {
			EphemeralData.getInstance().checkIfToggledRP().add(playe.getUniqueId());
			Common.tell(playe, Settings.RP_PREFIX + ChatColor.GREEN + "You started talking in RP Chat.");
		} else if (EphemeralData.getInstance().checkIfToggledRP().contains(playe.getUniqueId())) {
			Common.tell(playe, Settings.RP_PREFIX + ChatColor.RED + "You are already talking in RP Chat!");
		}


	}
}

