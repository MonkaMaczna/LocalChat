package me.flour.rpChatManager.settings;

import org.mineacademy.fo.settings.SimpleSettings;

public class Settings extends SimpleSettings {
	@Override
	protected int getConfigVersion() {
		return 3;

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
	public static String GLOBAL_LABEL;
	public static String LOCAL_LABEL;
	public static String GLOBAL_NICK;
	public static String RP_PERM;
	public static String RP_NICK;
	public static Boolean DEPEND;


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
		GLOBAL_LABEL = getString("Global_chat.Label");
		LOCAL_LABEL = getString("RP_chat.Label");
		GLOBAL_NICK = getString("Global_chat.Nick_Color");
		RP_NICK = getString("RP_chat.Nick_Color");
		DEPEND = getBoolean("RolePlay_Character_Information_Dependency");


	}
}
