package de.dbaelz.secludedness.manager;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Orientation;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.InputProcessor;

import de.dbaelz.secludedness.MainGame;
import de.dbaelz.secludedness.level.Level;
import de.dbaelz.secludedness.level.LevelStatistic;
import de.dbaelz.secludedness.level.Player;
import de.dbaelz.secludedness.manager.AudioManager.SoundFile;

public class InputManager implements InputProcessor {
	private MainGame mGame;
	private Level mLevel;
	private Player mPlayer;
	
	public InputManager(MainGame game, Level level, Player player) {
		mGame = game;
		mLevel = level;
		mPlayer = player;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (!mLevel.isFinished()) {
			int xPosition = mPlayer.getPositionX();
			int yPosition = mPlayer.getPositionY();
			
			if (keycode == Keys.RIGHT) {
				xPosition += 64;
			} else if (keycode == Keys.LEFT) {
				xPosition -= 64;
			} else if (keycode == Keys.UP) {
				yPosition += 64;
			} else if (keycode == Keys.DOWN) {
				yPosition -= 64;
			}
			
			handlePlayer(xPosition, yPosition);
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}	

	public void pollPlayerInput(float delta, Level level, Player player) {
		int xPosition = player.getPositionX();
		int yPosition = player.getPositionY();
		
		if ((Gdx.app.getType() == ApplicationType.Android) && (Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer))) {
			float accelX = Gdx.input.getAccelerometerX();
			float accelY = Gdx.input.getAccelerometerY();

			// Game is in landscape, so rotate axes when default orientation is portrait
			if (Gdx.input.getNativeOrientation() == Orientation.Portrait) {
				accelX = Gdx.input.getAccelerometerY();
				accelY = Gdx.input.getAccelerometerX();
			}

			float sensibility = 5.0f;
			if (accelX > sensibility) {
				xPosition += 64;
			} else if (accelX < -sensibility) {
				xPosition -= 64;
			} else if (accelY > sensibility) {
				yPosition -= 64;
			} else if (accelY < -sensibility) {
				yPosition += 64;
			}
				
		} else {
			if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				xPosition += 64;
			} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				xPosition -= 64;
			} else if (Gdx.input.isKeyPressed(Keys.UP)) {
				yPosition += 64;
			} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
				yPosition -= 64;
			}
		}
		
		handlePlayer(xPosition, yPosition);
	}

	
	private void handlePlayer(int xPosition, int yPosition) {
		if (xPosition != mPlayer.getPositionX() || yPosition != mPlayer.getPositionY()) {
			LevelStatistic statistic = mLevel.getLevelStatistic();			
			statistic.changeMovesBy(1);			
			mLevel.removeFog(xPosition, yPosition);
			
			if (mLevel.isCollidingWithExit(xPosition, yPosition)) {
				mLevel.setFinished(true);
			} else if (mLevel.isCollidingWithTrap(xPosition, yPosition)) {
				mGame.getAudioManager().playSound(SoundFile.TRAP);
				statistic.changeTrapsBy(1);
				mPlayer.changeHealthBy(-1);
				mPlayer.setPositionX(xPosition);
				mPlayer.setPositionY(yPosition);
			} else if (mLevel.isCollidingWithPortal(xPosition, yPosition)) {				
				mGame.getAudioManager().playSound(SoundFile.PORTAL);
				statistic.changeTeleportsBy(1);
				mPlayer.resetPositionToStart();
			} else if (mLevel.isCollidingWithForeground(xPosition, yPosition)) {
				mGame.getAudioManager().playSound(SoundFile.COLLISION);
			} else {
				mPlayer.setPositionX(xPosition);
				mPlayer.setPositionY(yPosition);
			}					
		}
	}
}
