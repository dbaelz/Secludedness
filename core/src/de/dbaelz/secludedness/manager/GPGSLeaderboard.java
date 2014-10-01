package de.dbaelz.secludedness.manager;

public enum GPGSLeaderboard {
	CAMPAIGN_SCORE("CgkIlaqmzdgQEAIQEw");
	
	private String mLeaderboardID;
	
	private GPGSLeaderboard(String id) {
		mLeaderboardID = id; 
	}
	
	public String getLeaderboardID() {
		return mLeaderboardID;
	}
}
