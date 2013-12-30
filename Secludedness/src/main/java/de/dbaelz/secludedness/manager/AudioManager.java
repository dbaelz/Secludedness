package de.dbaelz.secludedness.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {
	private Sound mSound;
	private Sound mCollisionSound;
	private Sound mPortalSound;
	private Sound mTrapSound;
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
	
	public AudioManager() {
		mCollisionSound = Gdx.audio.newSound(Gdx.files.internal(SoundFile.COLLISION.getFilename()));
		mPortalSound = Gdx.audio.newSound(Gdx.files.internal(SoundFile.PORTAL.getFilename()));
		mTrapSound = Gdx.audio.newSound(Gdx.files.internal(SoundFile.TRAP.getFilename()));
	}
	
	public void playSound(SoundFile file) {
		mSound = Gdx.audio.newSound(Gdx.files.internal(file.getFilename()));
		mSound.play(1.0f);
	}

	public void playCollisionSound() {
		mCollisionSound.play();
	}
	
	public void playPortalSound() {
		mPortalSound.play();
	}
	
	public void playTrapSound() {
		mTrapSound.play();
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
	
	public void dispose() {
		if (mSound != null) {
			mSound.dispose();	
		}
		
		mCollisionSound.dispose();
		mPortalSound.dispose();
		mTrapSound.dispose();
		
		if (mMusic != null) {
			mMusic.dispose();	
		}		
	}
}
