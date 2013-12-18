package de.dbaelz.secludedness.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {
	private Sound mSound;
	
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
	
	public void playSound(SoundFile file) {
		Sound mSound = Gdx.audio.newSound(Gdx.files.internal(file.getFilename()));
		mSound.play(1.0f);
	}
	
	// TODO: Call dispose or change AudioManager
	public void dispose() {
		mSound.dispose();
	}
}
