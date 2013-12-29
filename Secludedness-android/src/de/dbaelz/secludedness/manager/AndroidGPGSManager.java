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
	public void submitScore(int score) {
		//mGameHelper.getGamesClient().submitScore(LEADERBORD_ID, score);
	}

	@Override
	public void getLeaderboard() {
	}

}
