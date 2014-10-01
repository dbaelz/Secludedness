package de.dbaelz.secludedness;

import de.dbaelz.secludedness.MainGame;
import de.dbaelz.secludedness.manager.GWTGPGSManager;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class GwtLauncher extends GwtApplication {
	@Override
	public GwtApplicationConfiguration getConfig () {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(800, 600);
		return cfg;
	}

	@Override
	public ApplicationListener getApplicationListener () {
		return new MainGame(new GWTGPGSManager());
	}
}