package de.dbaelz.secludedness;


import com.badlogic.gdx.Game;

import de.dbaelz.secludedness.manager.InputManager;
import de.dbaelz.secludedness.screen.StartScreen;

public class MainGame extends Game {
	private InputManager mInputManager;
	
	@Override
	public void create() {
		mInputManager = new InputManager();
		
		setScreen(new StartScreen(this));
	}
	
	public InputManager getInputManager() {
		return mInputManager;
	}
}
