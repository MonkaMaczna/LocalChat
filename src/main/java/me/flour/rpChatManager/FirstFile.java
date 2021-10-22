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


		Common.log("&b[Local Chat] Loaded Local Chat Manager. ");

	}

	@Override
	public List<Class<? extends YamlStaticConfig>> getSettings() {
		return Arrays.asList(Settings.class);
	}


}
