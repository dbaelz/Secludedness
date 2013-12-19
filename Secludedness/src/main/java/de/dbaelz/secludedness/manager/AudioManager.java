package de.dbaelz.secludedness.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {
	private Sound mSound;
	private Music mMusic;
	
	public enum SoundFile {
		COLLISION("sounds/collision.ogg"),
		PORTAL("sounds/portal.ogg"),
		TRAP("sounds/trap.ogg");
		
		private String mFilename;
		
		private SoundFile(String filename) {
			mFilename = filename; 
		}
		
		private String getFilename() {
			return mFilename;
		}
	}
	
	public enum MusicFile {
		// TODO: Add music files
		TEMP("music/temp.ogg");
		
		private String mFilename;
		
		private MusicFile(String filename) {
			mFilename = filename; 
		}
		
		private String getFilename() {
			return mFilename;
		}
	}
	
	public void playSound(SoundFile file) {
		mSound = Gdx.audio.newSound(Gdx.files.internal(file.getFilename()));
		mSound.play(1.0f);
	}

	public void playMusic(MusicFile file) {
		mMusic = Gdx.audio.newMusic(Gdx.files.internal(file.getFilename()));
		mMusic.play();
	}
	
	public void pauseMusic() {
		if (mMusic != null || mMusic.isPlaying()) {
			mMusic.pause();
		}
	}
	
	public void stopMusic() {
		if (mMusic != null || mMusic.isPlaying()) {
			mMusic.stop();
			mMusic.dispose();
		}
	}
	
	// TODO: Call dispose or change AudioManager
	public void dispose() {
		if (mSound != null) {
			mSound.dispose();
		}

		if (mMusic != null) {
			mMusic.dispose();	
		}		
	}
}
