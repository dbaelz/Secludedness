package de.dbaelz.secludedness.level;

public class Player {
	private int mPositionX;
	private int mPositionY;
	
	public Player(int positionX, int positionY) {		
		mPositionX = positionX;
		mPositionY = positionY;
	}
	
	public int getPositionX() {
		return mPositionX;
	}

	public void setPositionX(int positionX) {
		this.mPositionX = positionX;
	}

	public int getPositionY() {
		return mPositionY;
	}

	public void setPositionY(int positionY) {
		this.mPositionY = positionY;
	}
	
}
