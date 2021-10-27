package me.flour.rpChatManager.commands;


import me.flour.character.API.CharacterAPI;
import me.flour.preferences.API.PreferencesAPI;
import me.flour.rpChatManager.data.EphemeralData;
import me.flour.rpChatManager.data.LogChat;
import me.flour.rpChatManager.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleCommand;

import static org.bukkit.Bukkit.getServer;

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

			final Location center = player.getLocation();
			final String playername = player.getDisplayName();
			final Boolean logging = Settings.LOGGING;



			final StringBuilder builder = new StringBuilder();
			for (final String arg : args) {
				builder.append(arg).append(" ");
			}
			String message = builder.toString().trim();


			for (final Player playerTarget : Bukkit.getOnlinePlayers()) {


				if (getServer().getPluginManager().getPlugin("RoleplayPreferencesPlugin") != null && Settings.DEPEND) {
					if (playerTarget == player || !PreferencesAPI.checkIgnoreRP(playerTarget)) {


						final Location location = playerTarget.getLocation();

						Integer	distance = PreferencesAPI.getHearingDistance(playerTarget);

						// if player is in distance of player executes block
						if (location.distanceSquared(center) <= distance * distance) {


							// colorizes message if contains color coded symbols
							if (message.contains("&")) {
								message = Common.colorize(message);
							}


							if (Settings.DEPEND && getServer().getPluginManager().getPlugin("RoleplayCharacterInformation") != null) {
								Common.tell(playerTarget, Settings.RP_PREFIX + "&a[&b " + CharacterAPI.getRpName(player) + "&a] " + Settings.RP_NICK + playername + "&7: " + Settings.RP_COLOR + message);

								Common.log(Settings.RP_PREFIX + "&a[&b " + CharacterAPI.getRpName(player) + "&a] " + Settings.RP_NICK + playername + "&7: " + Settings.RP_COLOR + message);

								if (logging) {
									LogChat.getInstance().logToFile(Settings.RP_PREFIX + "&a[&b " + CharacterAPI.getRpName(player) + "&a] " + Settings.RP_NICK + playername + "&7: " + Settings.RP_COLOR + message);
								}

							} else if (Settings.DEPEND && getServer().getPluginManager().getPlugin("RoleplayCharacterInformation") == null) {
								Common.log("&cNot found Roleplay Character Information Dependency. Download plugin or turn off dependency in settings.",
										"&cIf you think this is a mistake contact author of the plugin.",
										"&cDownload it from: https://www.spigotmc.org/resources/roleplay-character-information.97031/");
							} else {
								Common.tell(playerTarget, Settings.RP_PREFIX + " " + Settings.RP_NICK + playername + "&7: " + Settings.RP_COLOR + message);

								Common.log(Settings.RP_PREFIX + " " + Settings.RP_NICK + playername + "&7: " + Settings.RP_COLOR + message);

								if (logging) {
									LogChat.getInstance().logToFile(Settings.RP_PREFIX + " " + Settings.RP_NICK + playername + "&7: " + Settings.RP_COLOR + message);
								}


							}

						}
				}


				} else {
					final Location location = playerTarget.getLocation();




						Integer distance = Settings.DISTANCE;

					// if player is in distance of player executes block
					if (location.distanceSquared(center) <= distance * distance) {


						// colorizes message if contains color coded symbols
						if (message.contains("&")) {
							message = Common.colorize(message);
						}


						if (Settings.DEPEND && getServer().getPluginManager().getPlugin("RoleplayCharacterInformation") != null) {
							Common.tell(playerTarget, Settings.RP_PREFIX + "&a[&b " + CharacterAPI.getRpName(player) + "&a] " + Settings.RP_NICK + playername + "&7: " + Settings.RP_COLOR + message);

							Common.log(Settings.RP_PREFIX + "&a[&b " + CharacterAPI.getRpName(player) + "&a] " + Settings.RP_NICK + playername + "&7: " + Settings.RP_COLOR + message);

							if (logging) {
								LogChat.getInstance().logToFile(Settings.RP_PREFIX + "&a[&b " + CharacterAPI.getRpName(player) + "&a] " + Settings.RP_NICK + playername + "&7: " + Settings.RP_COLOR + message);
							}

						} else if (Settings.DEPEND && getServer().getPluginManager().getPlugin("RoleplayCharacterInformation") == null) {
							Common.log("&cNot found Roleplay Character Information Dependency. Download plugin or turn off dependency in settings.",
									"&cIf you think this is a mistake contact author of the plugin.",
									"&cDownload it from: https://www.spigotmc.org/resources/roleplay-character-information.97031/");
						} else {
							Common.tell(playerTarget, Settings.RP_PREFIX + " " + Settings.RP_NICK + playername + "&7: " + Settings.RP_COLOR + message);

							Common.log(Settings.RP_PREFIX + " " + Settings.RP_NICK + playername + "&7: " + Settings.RP_COLOR + message);

							if (logging) {
								LogChat.getInstance().logToFile(Settings.RP_PREFIX + " " + Settings.RP_NICK + playername + "&7: " + Settings.RP_COLOR + message);
							}


						}
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

