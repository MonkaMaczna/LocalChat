package me.flour.rpChatManager;


import me.flour.rpChatManager.commands.ToggleGlobal;
import me.flour.rpChatManager.commands.ToggleRP;
import me.flour.rpChatManager.settings.Settings;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.settings.YamlStaticConfig;

import java.util.Arrays;
import java.util.List;

public class FirstFile extends SimplePlugin {



	@Override
	protected void onPluginStart() {
		final int pluginId = 13096; // <-- Replace with the id of your plugin!
		final org.mineacademy.fo.metrics.Metrics metrics = new org.mineacademy.fo.metrics.Metrics(this, pluginId);


		registerEvents(new me.flour.rpChatManager.Events.Messenger());

		registerCommand(new ToggleRP("rp"));
		registerCommand(new ToggleGlobal("global"));

		if (Settings.DEPEND) {
			if (getServer().getPluginManager().getPlugin("RoleplayPreferencesPlugin") == null ||
					getServer().getPluginManager().getPlugin("RoleplayCharacterInformation") == null) {
				Common.log("&cDependecies not found!!!",
						"&cNeeded plugins: RoleplayPreferencesPlugin, RoleplayCharacterInformation",
						"&cYou can download these from my spigotmc profile: https://www.spigotmc.org/members/monkakokosowa.1262056/",
						"&cRoleplayCharacterInformation: https://www.spigotmc.org/resources/roleplay-character-information.97031/",
						"&cRoleplayPreferencesPlugin: https://www.spigotmc.org/resources/local-chat-plugin-for-short-distance-chatting.97027/",
						"&cDisabling Plugin...");
				getServer().getPluginManager().disablePlugin(this);
			}
		}


		Common.log("&b[Local Chat] Loaded Local Chat Manager. ", "&bPlugin was made by Wheat Flour");

		new UpdateChecker(this, 97027).getVersion(version -> {
			if (this.getDescription().getVersion().equals(version)) {
				Common.log("&b[Local Chat] &aThere aren't any new updates for Local Chat");
			} else {
				Common.log("&aThere's a new update avalaible for Local Chat",
						"&aLink to update: https://www.spigotmc.org/resources/local-chat-plugin-for-short-distance-chatting.97027/",
						"&aPlease download regularly updates because they are fixing many bugs and add many new features.");

			}
		});

	}

	@Override
	public List<Class<? extends YamlStaticConfig>> getSettings() {
		return Arrays.asList(Settings.class);
	}


}
