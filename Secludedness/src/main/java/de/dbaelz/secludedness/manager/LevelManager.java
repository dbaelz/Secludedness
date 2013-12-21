package de.dbaelz.secludedness.manager;

public class LevelManager {
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
		
		private String getFilename() {
			return mLevelFilename;
		}
	}
	
	private LevelFile[] mCampaignLevel = { LevelFile.LEVEL1, LevelFile.LEVEL2, LevelFile.LEVEL3 };
	private int mNextCampaignLevel = 0;
	
	public String getLevelFilename(boolean campaign) {
		if (campaign) {
			int levelIndex = mNextCampaignLevel;
			if (mNextCampaignLevel + 1 < mCampaignLevel.length) {
				mNextCampaignLevel++;	
			} else {
				mNextCampaignLevel = 0;
			}
			return mCampaignLevel[levelIndex].getFilename();
		}
		int random = (int) (Math.random() * (LevelFile.values().length - 0) + 0);
		return LevelFile.values()[random].getFilename();
	}
	
}
