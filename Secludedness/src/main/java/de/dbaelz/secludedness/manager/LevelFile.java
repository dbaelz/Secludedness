package de.dbaelz.secludedness.manager;

public enum LevelFile {
	// Campaign
	LEVEL1("maps/level1.tmx"),
	LEVEL2("maps/level2.tmx"),
	LEVEL3("maps/level3.tmx"),
			
	SMALL("maps/small.tmx");
	
	private String mLevelFilename;
	
	private LevelFile(String filename) {
		mLevelFilename = filename; 
	}
	
	public String getFilename() {
		return mLevelFilename;
	}
}
