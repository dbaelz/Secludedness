package de.dbaelz.secludedness.manager;

public interface GPGSManager {
	public boolean isSignedIn();
	public void signIn();
	public void signOut();
	public void unlockAchievement(String achievementID);
	public void incrementAchievement(String achievementID, int steps);
	public void getAchievements();
	public void submitScore(int score);
	public void getLeaderboard();
}
