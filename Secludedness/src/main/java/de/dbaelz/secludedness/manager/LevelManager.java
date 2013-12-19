package de.dbaelz.secludedness.manager;

public class LevelManager {
	public enum LevelFile {
		TEST("maps/test.tmx");
		
		private String mLevelFilename;
		
		private LevelFile(String filename) {
			mLevelFilename = filename; 
		}
		
		private String getFilename() {
			return mLevelFilename;
		}
	}
	
	private LevelFile[] mCampaignLevel = { LevelFile.TEST };
	private int mNextCampaignLevel = 0;
	
	public String getLevelName(boolean campaign) {
		if (campaign) {
			int levelIndex = mNextCampaignLevel;
			if (mNextCampaignLevel + 1 < mCampaignLevel.length) {
				mNextCampaignLevel++;	
			} else {
				// TODO: End of campaign = restart campaign?
				mNextCampaignLevel = 0;
			}
			return mCampaignLevel[levelIndex].getFilename();
		}
		// TODO: Randomly select a level?!
		return LevelFile.TEST.getFilename();
	}
	
}
