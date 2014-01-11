package de.dbaelz.secludedness.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.dbaelz.secludedness.MainGame;
import de.dbaelz.secludedness.manager.GPGSAchievement;
import de.dbaelz.secludedness.manager.GPGSLeaderboard;
import de.dbaelz.secludedness.manager.GPGSManager;
import de.dbaelz.secludedness.manager.LevelManager;


public class MenuScreen extends AbstractScreen {
	private TextureAtlas mAtlas;
	private Skin mSkin;
	private Table mTable;
	
	public MenuScreen(MainGame game) {
		super(game);
	}

	@Override
	public void show() {
		super.show();

		final GPGSManager playManager = mGame.getGPGSManager();
		playManager.signIn();
		playManager.loadCampaignFromCloud();
		
		mAtlas = new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"));
		FileHandle skinFile = Gdx.files.internal( "ui/uiskin.json" );
		mSkin = new Skin(skinFile);

		final int buttonWidth = 250;
		final int buttonHeight = 30;
		
		final TextButton playRandomLevel = new TextButton("PLAY RANDOM LEVEL", mSkin);
		playRandomLevel.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);				
				mGame.setScreen(new LevelScreen(mGame, false, mGame.getLevelManager().getRandomLevel()));
			}
		});

		final TextButton playCampaign = new TextButton("PLAY CAMPAIGN", mSkin);
		playCampaign.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				mGame.getGPGSManager().unlockAchievement(GPGSAchievement.BEGIN_CAMPAIGN.getAchievementID());
				if (mGame.getLevelManager().isCampaignFinished()) {
					mGame.getLevelManager().restartCampaign();
				}
				mGame.setScreen(new LevelScreen(mGame, true, mGame.getLevelManager().getCurrentCampaignLevelFilename()));
			}
		});
				
		final TextButton showAchievements = new TextButton("SHOW ACHIEVEMENTS", mSkin);
		showAchievements.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				playManager.getAchievements();
			}
		});
		
		final TextButton showLeaderboard = new TextButton("SHOW LEADERBOARD", mSkin);
		showLeaderboard.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				mGame.getGPGSManager().getLeaderboard(GPGSLeaderboard.CAMPAIGN_SCORE.getLeaderboardID());
			}
		});

		
		final TextButton settingsMenu = new TextButton("SETTINGS", mSkin);
		settingsMenu.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				mGame.setScreen(new SettingsScreen(mGame));
			}
		});
		
		final TextButton endGame = new TextButton("END GAME", mSkin);
		endGame.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				LevelManager levelManager = mGame.getLevelManager();
				if (levelManager.isCampaignFinished()) {
					levelManager.restartCampaign();
				}
				playManager.saveCampaignToCloud(levelManager.getCurrentCampaignLevel(), levelManager.getCampaignScore());
				Gdx.app.exit();
			}
		});
		
		mTable = new Table(mSkin);
		mTable.setFillParent(true);
		mStage.addActor(mTable);
		mTable.add(playRandomLevel).size(buttonWidth, buttonHeight).uniform();
		mTable.row();
		mTable.add(playCampaign).size(buttonWidth, buttonHeight).uniform().spaceTop(10);
		mTable.row();
		
		mTable.add(showAchievements).size(buttonWidth, buttonHeight).uniform().spaceTop(30);
		mTable.row();
		mTable.add(showLeaderboard).size(buttonWidth, buttonHeight).uniform().spaceTop(10);
				
		mTable.row();
		mTable.add(settingsMenu).size(buttonWidth, buttonHeight).uniform().spaceTop(30);
		mTable.row();
		mTable.add(endGame).size(buttonWidth, buttonHeight).uniform().spaceTop(10);	
	}
	
	@Override
	public void hide() {
		super.hide();
		mAtlas.dispose();
		mSkin.dispose();
	}
}
