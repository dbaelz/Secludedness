package de.dbaelz.secludedness.screen;

import com.badlogic.gdx.Gdx;

import de.dbaelz.secludedness.MainGame;
import de.dbaelz.secludedness.level.Level;

public class LevelScreen extends AbstractScreen {
	private MainGame mGame; 
	private Level mLevel;
	
	public LevelScreen(MainGame game, String mapName) {
		super(game);		
		mGame = game;
		mLevel = new Level(mapName);
		Gdx.app.log("Test", "levelscreen");
	}
}
