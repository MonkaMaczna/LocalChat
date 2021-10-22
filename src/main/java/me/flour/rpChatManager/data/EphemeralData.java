package me.flour.rpChatManager.data;

import java.util.ArrayList;
import java.util.UUID;

public class EphemeralData {

	private static EphemeralData instance;


	private final ArrayList<UUID> playerWithEnabledRP = new ArrayList<>();


	private EphemeralData() {

	}

	public static EphemeralData getInstance() {
		if (instance == null) {
			instance = new EphemeralData();
		}
		return instance;
	}


	public ArrayList<UUID> checkIfToggledRP() {
		return playerWithEnabledRP;
	}

	
}