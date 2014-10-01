package de.dbaelz.secludedness.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.dbaelz.secludedness.MainGame;
import de.dbaelz.secludedness.manager.GPGSAchievement;
import de.dbaelz.secludedness.manager.GPGSLeaderboard;
import de.dbaelz.secludedness.manager.GPGSManager;

public class CampaignFinishScreen extends AbstractScreen {
	private MainGame mGame;
	private TextureAtlas mAtlas;
	private Skin mSkin;
	private Table mTable;

	public CampaignFinishScreen(MainGame game) {
		super(game);
		mGame = game;
		manageGPGS();
	}

	@Override
	public void show() {
		super.show();
		
		mAtlas = new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"));
		FileHandle skinFile = Gdx.files.internal( "ui/uiskin.json" );
		mSkin = new Skin(skinFile);
		
		final int buttonWidth = 250;
		final int buttonHeight = 30;

		Label finishedLabel = new Label("== CAMPAIGN FINISHED ==", mSkin);
				
		Label textLabel = new Label("You have successfully completed the campaign!", mSkin);
		Label tempLabel = new Label("Campaign score: " + mGame.getLevelManager().getCampaignScore(), mSkin);

		TextButton returnMenuScreen = new TextButton("RETURN TO MENU", mSkin);
		returnMenuScreen.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);				
				mGame.setScreen(new MenuScreen(mGame));
			}
		});
		
		mTable = new Table(mSkin);
		mTable.setFillParent(true);
		mStage.addActor(mTable);

		mTable.add(finishedLabel);
		mTable.row();
		mTable.add(textLabel);
		mTable.row();
		mTable.add(tempLabel);
		mTable.row();
		mTable.add(returnMenuScreen).size(buttonWidth, buttonHeight).uniform().spaceBottom(30);
	}
	
	@Override
	public void hide() {
		super.hide();
		mAtlas.dispose();
		mSkin.dispose();
	}
	
	private void manageGPGS() {
		GPGSManager manager = mGame.getGPGSManager();
		
		manager.unlockAchievement(GPGSAchievement.FINISH_CAMPAIGN.getAchievementID());
		
		manager.submitScore(GPGSLeaderboard.CAMPAIGN_SCORE.getLeaderboardID(), mGame.getLevelManager().getCampaignScore());
	}
}
