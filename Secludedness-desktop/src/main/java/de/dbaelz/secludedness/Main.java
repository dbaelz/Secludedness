package de.dbaelz.secludedness;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.dbaelz.secludedness.MainGame;
import de.dbaelz.secludedness.manager.DesktopGPGSManager;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Secludedness";
		cfg.useGL20 = true;
		cfg.width = 800;
		cfg.height = 600;
		
		new LwjglApplication(new MainGame(new DesktopGPGSManager()), cfg);
	}
}
