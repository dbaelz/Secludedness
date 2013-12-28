package de.dbaelz.secludedness.manager;

public interface GPGSManager {
	public boolean isUserSignedIn();
	public void loginUser();
	public void unlockAchievement(String achievementID);
	public void getAchievements();
	public void submitScore();
	public void getLeaderboard();
}
