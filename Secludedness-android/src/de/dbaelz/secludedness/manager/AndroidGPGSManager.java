package de.dbaelz.secludedness.manager;

import java.nio.ByteBuffer;

import com.google.android.gms.appstate.AppStateClient;
import com.google.android.gms.appstate.OnStateLoadedListener;
import com.google.example.games.basegameutils.GameHelper;

import de.dbaelz.secludedness.MainActivity;

public class AndroidGPGSManager implements GPGSManager, OnStateLoadedListener {
	private final int STATE_KEY = 0;

	private GameHelper mGameHelper;
	private MainActivity mActivity;

	public AndroidGPGSManager(GameHelper gamehelper, MainActivity activity) {
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
		mActivity.startActivityForResult(mGameHelper.getGamesClient()
				.getAchievementsIntent(), 10);
	}

	@Override
	public void submitScore(String leaderboardID, int score) {
		mGameHelper.getGamesClient().submitScore(leaderboardID, score);
	}

	@Override
	public void getLeaderboard(String leaderboardID) {
		mActivity.startActivityForResult(mGameHelper.getGamesClient()
				.getLeaderboardIntent(leaderboardID), 11);
	}

	@Override
	public void saveCampaignToCloud(int campaignLevel, int campaignScore) {
		ByteBuffer buffer = ByteBuffer.allocate(9);
		buffer.putInt(campaignLevel);
		buffer.putInt(campaignScore);
		mGameHelper.getAppStateClient().updateState(STATE_KEY, buffer.array());
	}

	@Override
	public void loadCampaignFromCloud() {
		mGameHelper.getAppStateClient().loadState(this, STATE_KEY);
	}

	@Override
	public void onStateConflict(int stateKey, String version, byte[] localData, byte[] serverData) {
		ByteBuffer localBuffer = ByteBuffer.wrap(localData);
		ByteBuffer serverBuffer = ByteBuffer.wrap(serverData);
		int localCampaignLevel = localBuffer.getInt();
		int serverCampaignLevel = serverBuffer.getInt();

		LevelManager levelManager = mActivity.getGame().getLevelManager();
		if (localCampaignLevel >= serverCampaignLevel) {
			levelManager.setCurrentCampaignLevel(localCampaignLevel);
			levelManager.setCampaignScore(localBuffer.getInt());
		} else {
			levelManager.setCurrentCampaignLevel(serverCampaignLevel);
			levelManager.setCampaignScore(serverBuffer.getInt());
		}
	}

	@Override
	public void onStateLoaded(int statusCode, int stateKey, byte[] data) {
		LevelManager levelManager = mActivity.getGame().getLevelManager();
		
		switch (statusCode) {
		case AppStateClient.STATUS_OK:
			ByteBuffer buffer = ByteBuffer.wrap(data);
			levelManager.setCurrentCampaignLevel(buffer.getInt());
			levelManager.setCampaignScore(buffer.getInt());
			break;		
		default:
			levelManager.setCampaignScore(0);
			levelManager.setCampaignScore(0);
		}
	}
}
