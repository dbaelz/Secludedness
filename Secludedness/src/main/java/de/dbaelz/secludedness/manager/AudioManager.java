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
	private boolean mSoundActivated = true;
	private boolean mMusicActivated = true;
	
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
		if (mSoundActivated) {
			mSound = Gdx.audio.newSound(Gdx.files.internal(file.getFilename()));
			mSound.play(1.0f);
		}
	}

	public void playCollisionSound() {
		if (mSoundActivated) {
			mCollisionSound.play();
		}
	}
	
	public void playPortalSound() {
		if (mSoundActivated) {
			mPortalSound.play();
		}
	}
	
	public void playTrapSound() {
		if (mSoundActivated) {
			mTrapSound.play();
		}
	}
	public void playMusic(MusicFile file) {
		if (mMusicActivated) {
			mMusic = Gdx.audio.newMusic(Gdx.files.internal(file.getFilename()));
			mMusic.play();
		}
	}
	
	public void pauseMusic() {
		if (mMusic != null && mMusic.isPlaying()) {
			mMusic.pause();
		}
	}
	
	public void stopMusic() {
		if (mMusic != null && mMusic.isPlaying()) {
			mMusic.stop();
			mMusic.dispose();
		}
	}
	
	public boolean isSoundActivated() {
		return mSoundActivated;
	}
	
	public void setSoundActivated(boolean activated) {
		mSoundActivated = activated;
	}
	
	public boolean isMusicActivated() {
		return mMusicActivated;
	}
	
	public void setMusicActivated(boolean activated) {
		mMusicActivated = activated;
		if (!activated) {
			stopMusic();
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
