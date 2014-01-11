package de.dbaelz.secludedness.manager;

import android.app.Activity;

import com.google.example.games.basegameutils.GameHelper;

public class AndroidGPGSManager implements GPGSManager {
	private GameHelper mGameHelper;
	private Activity mActivity;
	
	public AndroidGPGSManager(GameHelper gamehelper, Activity activity) {
		mGameHelper = gamehelper;
		mActivity = activity;
	}
	
	@Override
	public boolean isSignedIn() {
		return mGameHelper.isSignedIn();
	}

	@Override
	public void signIn() {
		mGameHelper.beginUserInitiatedSignIn();
	}

	@Override
	public void signOut() {
		mGameHelper.signOut();
	}
	
	@Override
	public void unlockAchievement(String achievementID) {
		mGameHelper.getGamesClient().unlockAchievement(achievementID);
	}
	
	@Override
	public void incrementAchievement(String achievementID, int steps) {
		mGameHelper.getGamesClient().incrementAchievement(achievementID, steps);
	}

	@Override
	public void getAchievements() {
		mActivity.startActivityForResult(mGameHelper.getGamesClient().getAchievementsIntent(), 10);
	}

	@Override
	public void submitScore(String leaderboardID, int score) {
		mGameHelper.getGamesClient().submitScore(leaderboardID, score);
	}

	@Override
	public void getLeaderboard(String leaderboardID) {
		mActivity.startActivityForResult(mGameHelper.getGamesClient().getLeaderboardIntent(leaderboardID), 11);
	}

	@Override
	public void saveCampaignToCloud() {
	}

	@Override
	public void loadCampaignFromCloud() {
	}
}
