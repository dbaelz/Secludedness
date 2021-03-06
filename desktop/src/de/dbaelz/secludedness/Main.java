package de.dbaelz.secludedness;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.dbaelz.secludedness.MainGame;
import de.dbaelz.secludedness.manager.DesktopGPGSManager;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Secludedness";
		cfg.width = 1196;
		cfg.height = 720;
		
		new LwjglApplication(new MainGame(new DesktopGPGSManager()), cfg);
	}
}
