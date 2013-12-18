package de.dbaelz.secludedness.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Orientation;
import com.badlogic.gdx.Input.Peripheral;

import de.dbaelz.secludedness.level.Level;
import de.dbaelz.secludedness.level.Player;

public class InputManager {

	
	public void handlePlayerInput(float delta, Level level, Player player) {
		// TODO: Set controller by controller settings
		// TODO: Add support for controller
		
		int xPosition = player.getPositionX();
		int yPosition = player.getPositionY();
		
		if ((Gdx.app.getType() == ApplicationType.Android) && (Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer))) {
			float accelX = Gdx.input.getAccelerometerX();
			float accelY = Gdx.input.getAccelerometerY();

			if (Gdx.input.getNativeOrientation() == Orientation.Portrait) {
				float rotation = Gdx.input.getRotation() - 180;
			}

			float sensibility = 4.0f;
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
		
		if (xPosition != player.getPositionX() || yPosition != player.getPositionY()) {
			if (level.isCollidingWithDamage(xPosition, yPosition)) {
				player.changeHealthBy(-1);
				player.setPositionX(xPosition);
				player.setPositionY(yPosition);
			} else if (level.isCollidingWithPortal(xPosition, yPosition)) {
				player.resetPositionToStart();
			} else if (!level.isCollidingWithForeground(xPosition, yPosition)) {
				player.setPositionX(xPosition);
				player.setPositionY(yPosition);
			}					
		}

	}	

}
