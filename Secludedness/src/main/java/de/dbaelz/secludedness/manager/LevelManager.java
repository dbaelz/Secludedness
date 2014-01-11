package de.dbaelz.secludedness.manager;

public class LevelManager {
	private LevelFile[] mCampaignLevels = { LevelFile.LEVEL1, LevelFile.LEVEL2, LevelFile.LEVEL3 };
	private int mCurrentCampaignLevel = 0;
	private int mCampaignScore = 0;
	private boolean mCampaignFinished = false;
	
	public String getRandomLevel() {
		int random = (int) (Math.random() * (LevelFile.values().length - 0) + 0);
		return LevelFile.values()[random].getFilename();	
	}
	
	public void handleCampaignLevelFinished(int score) {
		mCampaignScore += score;		
		if (mCurrentCampaignLevel + 1 < mCampaignLevels.length) {
			mCurrentCampaignLevel++;
		} else {
			mCampaignFinished = true;
		}
	}
	
	public String getCurrentCampaignLevel() {
		// TODO: Restart campaign when finished?
		if (mCampaignFinished) {
			restartCampaign();
		}
		return mCampaignLevels[mCurrentCampaignLevel].getFilename();
	}
	
	public boolean isCampaignFinished() {
		return mCampaignFinished;
	}
	
	public int getCampaignScore() {
		return mCampaignScore;
	}
	
	public void restartCampaign() {
		mCampaignFinished = false;
		mCurrentCampaignLevel = 0;
		mCampaignScore = 0;
	}
}
