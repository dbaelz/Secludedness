package de.dbaelz.secludedness.level;

public class Player {
	private final int mStartPositionX;
	private final int mStartPositionY;
	private int mPositionX;
	private int mPositionY;
	
	public Player(int positionX, int positionY) {
		mStartPositionX = positionX;
		mStartPositionY = positionY;
		
		mPositionX = positionX;
		mPositionY = positionY;
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
	
	public void resetToStart() {
		mPositionX = mStartPositionX;
		mPositionY = mStartPositionY;
	}
}
