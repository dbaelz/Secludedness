package de.dbaelz.secludedness;


import com.badlogic.gdx.Game;

import de.dbaelz.secludedness.manager.AudioManager;
import de.dbaelz.secludedness.manager.AudioManager.MusicFile;
import de.dbaelz.secludedness.manager.LevelManager;
import de.dbaelz.secludedness.screen.StartScreen;

public class MainGame extends Game {
	private AudioManager mAudioManager;
	private LevelManager mLevelManager;
	
	@Override
	public void create() {
		setScreen(new StartScreen(this));
		mAudioManager = new AudioManager();
		mLevelManager = new LevelManager();
		
		// TODO: Start intro music
	}
	
	public AudioManager getAudioManager() {
		return mAudioManager;
	}
	
	public LevelManager getLevelManager() {
		return mLevelManager;
	}

}
