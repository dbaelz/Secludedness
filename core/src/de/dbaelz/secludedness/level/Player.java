package de.dbaelz.secludedness.level;

public class Player {
	private final int mStartPositionX;
	private final int mStartPositionY;
	private int mPositionX;
	private int mPositionY;
	private int mHealth;
	
	public Player(int positionX, int positionY, int health) {
		mStartPositionX = positionX;
		mStartPositionY = positionY;
		
		mPositionX = positionX;
		mPositionY = positionY;
		mHealth = health;
	}
	
	public int getPositionX() {
		return mPositionX;
	}

	public void setPositionX(int positionX) {
		this.mPositionX = positionX;
	}
	
	public void changeXPositionBy(int value) {
		this.mPositionX += value;
	}

	public int getPositionY() {
		return mPositionY;
	}

	public void setPositionY(int positionY) {
		this.mPositionY = positionY;
	}
	
	public void changeYPositionBy(int value) {
		this.mPositionY += value;
	}
	
	public void resetPositionToStart() {
		mPositionX = mStartPositionX;
		mPositionY = mStartPositionY;
	}

	public int getHealth() {
		return mHealth;
	}

	public void setHealth(int health) {
		this.mHealth = health;
	}
	
	public void changeHealthBy(int value) {
		this.mHealth += value;
	}
}
