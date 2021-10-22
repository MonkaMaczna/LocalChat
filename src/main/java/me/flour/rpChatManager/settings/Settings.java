package me.flour.rpChatManager.settings;

import org.mineacademy.fo.settings.SimpleSettings;

public class Settings extends SimpleSettings {
	@Override
	protected int getConfigVersion() {
		return 1;

	}


	public static String RP_PREFIX;
	public static String RP_COLOR;
	public static Boolean GLOBAL_OVERRIDE;
	public static Boolean LUCKPERMS_BOOLEAN;
	public static Boolean LOGGING;
	public static Integer DISTANCE;
	public static String GLOBAL_COLOR;
	public static String GLOBAL_PREFIX;
	public static String GLOBAL_PERM;

	public static String RP_PERM;


	private static void init() {

		RP_PREFIX = getString("RP_chat.Prefix");
		RP_COLOR = getString("RP_chat.Message_Color");
		DISTANCE = getInteger("Distance");
		GLOBAL_OVERRIDE = getBoolean("Global_Chat_Override");
		LUCKPERMS_BOOLEAN = getBoolean("LuckPerms_Prefixes");
		GLOBAL_COLOR = getString("Global_chat.Message_Color");
		GLOBAL_PREFIX = getString("Global_chat.Prefix");
		GLOBAL_PERM = getString("Global_chat.Permission_To_Global_chat");
		LOGGING = getBoolean("Logging_Messages");
		RP_PERM = getString("RP_chat.Permission_To_RP_chat");

	}
}
