package me.flour.rpChatManager.commands;

import me.flour.rpChatManager.data.EphemeralData;
import me.flour.rpChatManager.settings.Settings;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleCommand;

import static org.bukkit.Bukkit.getServer;

public class ToggleGlobal extends SimpleCommand {

	public ToggleGlobal(final String label) {
		super("global");
	}




	@Override
	protected void onCommand() {
		checkConsole();
		checkPerm(Settings.GLOBAL_PERM);


		if (Settings.GLOBAL_OVERRIDE && args.length > 0) {


			final StringBuilder builder = new StringBuilder();
			for (final String arg : args) {
				builder.append(arg).append(" ");
			}
			String message = builder.toString().trim();


			if (message.contains("&")) {
				message = Common.colorize(message);
			}


			final String playername = getPlayer().getDisplayName();
			final Player player = getPlayer();
			if (Settings.LUCKPERMS_BOOLEAN && getServer().getPluginManager().getPlugin("LuckPerms") != null) {
				final LuckPerms luckPerms = LuckPermsProvider.get();
				final User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
				final String prefix = user.getCachedData().getMetaData().getPrefix();
				message = prefix + " " + playername + Settings.GLOBAL_COLOR + ": " + message;
			} else if (getServer().getPluginManager().getPlugin("LuckPerms") == null && Settings.LUCKPERMS_BOOLEAN) {
				Common.log("&cERROR: LuckPerms not found. Setting for luckperms implementation is enabled.");

			} else if (!Settings.LUCKPERMS_BOOLEAN) {
				message = playername + Settings.GLOBAL_COLOR + ": " + message;
				if (Settings.GLOBAL_PREFIX != null) {
					message = Settings.GLOBAL_PREFIX + " " + message;
				}
			}
			Common.broadcast(message);


		} else if (args.length == 0) {

			final Player playerthree = getPlayer();
			removeFromRP(playerthree);
		}


	}

	private void removeFromRP(final Player player) {
		final Player play = player;
		if (EphemeralData.getInstance().checkIfToggledRP().contains(play.getUniqueId())) {
			EphemeralData.getInstance().checkIfToggledRP().remove(play.getUniqueId());

			Common.tell(play, "&aYou started talking in Global Chat.");

		} else if (!EphemeralData.getInstance().checkIfToggledRP().contains(player.getUniqueId())) {
			play.sendMessage(ChatColor.RED + "You are already talking in Global Chat!");
		}
	}
}

