package me.flour.rpChatManager.data;

import me.flour.rpChatManager.FirstFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogChat {

	private static LogChat instance;

	public static LogChat getInstance() {
		if (instance == null) {
			instance = new LogChat();
		}
		return instance;
	}

	public void logToFile(final String message) {

		try {
			final File dataFolder = FirstFile.getInstance().getDataFolder();
			if (!dataFolder.exists()) {
				dataFolder.mkdir();
			}

			final File saveTo = new File(FirstFile.getInstance().getDataFolder(), "data.txt");
			if (!saveTo.exists()) {
				saveTo.createNewFile();
			}


			final FileWriter fw = new FileWriter(saveTo, true);

			final PrintWriter pw = new PrintWriter(fw);

			pw.println(message);

			pw.flush();

			pw.close();

		} catch (final IOException e) {

			e.printStackTrace();

		}

	}
}
