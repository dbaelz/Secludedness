package de.dbaelz.secludedness;


import com.badlogic.gdx.Game;

import de.dbaelz.secludedness.manager.AudioManager;
import de.dbaelz.secludedness.manager.GPGSManager;
import de.dbaelz.secludedness.manager.LevelManager;
import de.dbaelz.secludedness.screen.StartScreen;

public class MainGame extends Game {
	private GPGSManager mGPGSManager;
	private AudioManager mAudioManager;
	private LevelManager mLevelManager;
	
	public MainGame(GPGSManager gpgsManager) {
		this.mGPGSManager = gpgsManager;
	}
	
	@Override
	public void create() {
		setScreen(new StartScreen(this));
		mAudioManager = new AudioManager();
		mLevelManager = new LevelManager();
		
		// TODO: Start intro music
	}
	
	@Override
	public void dispose() {
		super.dispose();
		mAudioManager.dispose();
	}
	
	public GPGSManager getGPGSManager() {
		return mGPGSManager;
	}
	
	public AudioManager getAudioManager() {
		return mAudioManager;
	}
	
	public LevelManager getLevelManager() {
		return mLevelManager;
	}

}
