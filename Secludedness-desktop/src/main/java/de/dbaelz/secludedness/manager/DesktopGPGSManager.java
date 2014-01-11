package de.dbaelz.secludedness.manager;

import com.badlogic.gdx.Gdx;


public class DesktopGPGSManager implements GPGSManager {
	private final String GPGS_MANAGER = "GPGS MANAGER";
	
	@Override
	public boolean isSignedIn() {
		Gdx.app.log(GPGS_MANAGER, "isSignedIn TRUE");
		return true;
	}

	@Override
	public void signIn() {
		Gdx.app.log(GPGS_MANAGER, "signIn");
	}

	@Override
	public void signOut() {
		Gdx.app.log(GPGS_MANAGER, "signOut");
	}
	
	@Override
	public void unlockAchievement(String achievementID) {
		Gdx.app.log(GPGS_MANAGER, "UNLOCK ACHIEVEMENT: " + achievementID);
	}

	@Override
	public void incrementAchievement(String achievementID, int steps) {
		Gdx.app.log(GPGS_MANAGER, "INCREMENT ACHIEVEMENT: " + achievementID + " BY " + steps + " STEPS");
	}
	
	@Override
	public void getAchievements() {
		Gdx.app.log(GPGS_MANAGER, "getAchievements");
	}

	@Override
	public void submitScore(String leaderboardID, int score) {
		Gdx.app.log(GPGS_MANAGER, "Leaderboard " +  leaderboardID + " submit score " + score);
	}

	@Override
	public void getLeaderboard(String leaderboardID) {
		Gdx.app.log(GPGS_MANAGER, "getLeaderboard " + leaderboardID);
	}

	@Override
	public void saveCampaignToCloud(int campaignLevel, int campaignScore) {
		Gdx.app.log(GPGS_MANAGER, "saveCampaignToCloud: Campaign Level " + campaignLevel + " Campaign Score " + campaignScore);
	}

	@Override
	public void loadCampaignFromCloud() {
		Gdx.app.log(GPGS_MANAGER, "loadCampaignFromCloud");
	}
}
