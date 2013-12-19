package de.dbaelz.secludedness;


import com.badlogic.gdx.Game;

import de.dbaelz.secludedness.manager.AudioManager;
import de.dbaelz.secludedness.manager.AudioManager.MusicFile;
import de.dbaelz.secludedness.screen.StartScreen;

public class MainGame extends Game {
	private AudioManager mAudioManager;
	
	@Override
	public void create() {
		setScreen(new StartScreen(this));
		mAudioManager = new AudioManager();
		
		// TODO: For testing/demo purpose only
		mAudioManager.playMusic(MusicFile.TEMP);
	}
	
	public AudioManager getAudioManager() {
		return mAudioManager;
	}

}
