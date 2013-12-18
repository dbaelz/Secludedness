package de.dbaelz.secludedness;


import com.badlogic.gdx.Game;

import de.dbaelz.secludedness.screen.StartScreen;

public class MainGame extends Game {
	@Override
	public void create() {
		setScreen(new StartScreen(this));
	}

}
