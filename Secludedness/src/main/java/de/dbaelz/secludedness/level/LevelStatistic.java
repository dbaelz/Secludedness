package de.dbaelz.secludedness.level;

public class LevelStatistic {
	private final int START_HEALTH;
	private final String LEVEL_FILENAME;
	
	private int mMoves;
	private int mTeleports;
	private int mTraps;
	
	private int mHealth;
	
	public LevelStatistic(int startHealth, String levelFilename) {
		START_HEALTH = startHealth;
		LEVEL_FILENAME = levelFilename;
	}

	public int getMoves() {
		return mMoves;
	}

	public void changeMovesBy(int value) {
		this.mMoves += value;
	}

	public int getTeleports() {
		return mTeleports;
	}

	public void changeTeleportsBy(int value) {
		this.mTeleports += value;
	}

	public int getTraps() {
		return mTraps;
	}

	public void changeTrapsBy(int value) {
		this.mTraps += value;
	}
	
	public int getHealth() {
		return mHealth;
	}

	public void setHealth(int health) {
		this.mHealth = health;
	}

	public int getStartHealth() {
		return START_HEALTH;
	}
	
	public String getLevelFilename() {
		return LEVEL_FILENAME;
	}
}
