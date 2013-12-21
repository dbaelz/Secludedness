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
		
		mAtlas = new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"));
		FileHandle skinFile = Gdx.files.internal( "ui/uiskin.json" );
		mSkin = new Skin(skinFile);

		TextButton playRandomLevel = new TextButton("PLAY RANDOM LEVEL", mSkin);
		playRandomLevel.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);				
				mGame.setScreen(new LevelScreen(mGame, false));
			}
		});

		TextButton playCampaign = new TextButton("PLAY CAMPAIGN", mSkin);
		playCampaign.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				mGame.setScreen(new LevelScreen(mGame, true));
			}
		});
		
		TextButton endGame = new TextButton("END GAME", mSkin);
		endGame.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);				
				Gdx.app.exit();				 
			}
		});
		
		mTable = new Table(mSkin);
		mTable.setFillParent(true);
		mStage.addActor(mTable);
		mTable.add(playRandomLevel).size(180, 30).uniform().spaceBottom(10);
		mTable.row();
		mTable.add(playCampaign).size(180, 30).uniform().spaceBottom(10);
		mTable.row();
		mTable.add(endGame).size(180, 30).uniform().spaceBottom(10).spaceTop(30);	
	}
	
	@Override
	public void hide() {
		super.hide();
		mAtlas.dispose();
		mSkin.dispose();
	}
}
