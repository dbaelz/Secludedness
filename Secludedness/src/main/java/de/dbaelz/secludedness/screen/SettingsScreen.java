package de.dbaelz.secludedness.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.dbaelz.secludedness.MainGame;
import de.dbaelz.secludedness.manager.AudioManager;
import de.dbaelz.secludedness.manager.GPGSAchievement;
import de.dbaelz.secludedness.manager.GPGSManager;

public class SettingsScreen extends AbstractScreen {
	private MainGame mGame;
	private TextureAtlas mAtlas;
	private Skin mSkin;
	private Table mTable;

	public SettingsScreen(MainGame game) {
		super(game);
		mGame = game;
		manageAchievements();
	}

	@Override
	public void show() {
		super.show();
		
		mAtlas = new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"));
		FileHandle skinFile = Gdx.files.internal( "ui/uiskin.json" );
		mSkin = new Skin(skinFile);

		final int buttonWidth = 250;
		final int buttonHeight = 30;
		
		final AudioManager audioManager = mGame.getAudioManager();
		
		final CheckBox soundBox = new CheckBox(" SOUND EFFECTS", mSkin);
		soundBox.align(Align.left);
		soundBox.setChecked(audioManager.isSoundActivated());
		soundBox.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);				
				audioManager.setSoundActivated(soundBox.isChecked());
			}
		});
		
		final CheckBox musicBox = new CheckBox(" MUSIC", mSkin);
		musicBox.align(Align.left);
		musicBox.setChecked(audioManager.isMusicActivated());
		musicBox.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);				
				audioManager.setMusicActivated(musicBox.isChecked());
			}
		});
		
		CheckBox controllerBox = new CheckBox(" CONTROLLER", mSkin);
		controllerBox.align(Align.left);
		controllerBox.setDisabled(true);

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
		
		mTable.add(soundBox).size(buttonWidth, buttonHeight).uniform();
		mTable.row();
		mTable.add(musicBox).size(buttonWidth, buttonHeight).uniform().spaceTop(10);
		mTable.row();		
		mTable.add(controllerBox).size(buttonWidth, buttonHeight).uniform().spaceTop(10);
		mTable.row();
		mTable.add(returnMenuScreen).size(buttonWidth, buttonHeight).uniform().spaceTop(30);
	}
	
	@Override
	public void hide() {
		super.hide();
		mAtlas.dispose();
		mSkin.dispose();
	}
	
	private void manageAchievements() {
		GPGSManager manager = mGame.getGPGSManager();
		
		if (manager.isSignedIn()) {	
			manager.unlockAchievement(GPGSAchievement.CONFIGURATOR.getAchievementID());
		}
	}
}
