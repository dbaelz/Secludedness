package de.dbaelz.secludedness.manager;

public enum GPGSAchievement {
	PLAY_RANDOM_LEVEL("CgkIlaqmzdgQEAIQAg"),
	FIFTY_RANDOM_LEVELS("CgkIlaqmzdgQEAIQAw"),

	BEGIN_CAMPAIGN("CgkIlaqmzdgQEAIQBA"),
	FINISH_CAMPAIGN("CgkIlaqmzdgQEAIQBQ"),
	
	ONEHUNDRED_MOVES("CgkIlaqmzdgQEAIQBg"),
	FIVEHUNDRED_MOVES("CgkIlaqmzdgQEAIQBw"),
	THOUSAND_MOVES("CgkIlaqmzdgQEAIQCA"),
	
	ONEHUNDRED_TELEPORTS("CgkIlaqmzdgQEAIQCQ"),
	TWOHUNDRED_TELEPORTS("CgkIlaqmzdgQEAIQCg"),
	THREEHUNDRED_TELEPORTS("CgkIlaqmzdgQEAIQCw"),
	
	ONEHUNDRED_DAMAGE("CgkIlaqmzdgQEAIQDA"),
	TWOHUNDRED_DAMAGE("CgkIlaqmzdgQEAIQDQ"),
	THREEHUNDRED_DAMAGE("CgkIlaqmzdgQEAIQDg"),
	
	FAIL_LEVEL("CgkIlaqmzdgQEAIQDw"),
	MASTER_LEVEL("CgkIlaqmzdgQEAIQEA"),
	
	WIN_TWOHUNDRED_LEVELS("CgkIlaqmzdgQEAIQEQ"),
	LOSE_TWOHUNDRED_LEVELS("CgkIlaqmzdgQEAIQEg"),
	
	CONFIGURATOR("CgkIlaqmzdgQEAIQFA");
	
	private String mAchievementID;
	
	private GPGSAchievement(String id) {
		mAchievementID = id; 
	}
	
	public String getAchievementID() {
		return mAchievementID;
	}
}
