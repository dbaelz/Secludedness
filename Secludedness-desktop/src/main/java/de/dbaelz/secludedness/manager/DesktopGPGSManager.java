package de.dbaelz.secludedness.manager;


public class DesktopGPGSManager implements GPGSManager {

	@Override
	public boolean isSignedIn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void signIn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void signOut() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void unlockAchievement(String achievementID) {
		System.out.println("PLACEHOLDER: UNLOCK ACHIEVEMENT " + achievementID);
	}

	@Override
	public void incrementAchievement(String achievementID, int steps) {
		System.out.println("PLACEHOLDER: INCREMENT ACHIEVEMENT " + achievementID + " BY " + steps + " STEPS");	}
	
	@Override
	public void getAchievements() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submitScore(int score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getLeaderboard() {
		// TODO Auto-generated method stub
		
	}

}