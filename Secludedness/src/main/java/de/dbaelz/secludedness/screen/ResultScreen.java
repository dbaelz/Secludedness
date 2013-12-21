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
import de.dbaelz.secludedness.level.LevelStatistic;

public class ResultScreen extends AbstractScreen {
	private LevelStatistic mLevelStatistic;
	private TextureAtlas mAtlas;
	private Skin mSkin;
	private Table mTable;

	public ResultScreen(MainGame game, LevelStatistic statistic) {
		super(game);
		mLevelStatistic = statistic;
	}

	@Override
	public void show() {
		super.show();
		
		mAtlas = new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"));
		FileHandle skinFile = Gdx.files.internal( "ui/uiskin.json" );
		mSkin = new Skin(skinFile);

		Label movesLabel = new Label("Moves: " + mLevelStatistic.getMoves(), mSkin);
		Label teleportsLabel = new Label("Teleports: " + mLevelStatistic.getTeleports(), mSkin);
		Label trapsLabel = new Label("Traps: " + mLevelStatistic.getTraps(), mSkin);
		Label healthLabel = new Label("Health: " + mLevelStatistic.getHealth() + "/" + mLevelStatistic.getStartHealth(), mSkin);
		
		TextButton repeatLevel = new TextButton("REPEAT LEVEL", mSkin);
		repeatLevel.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);				
				mGame.setScreen(new LevelScreen(mGame, false, mLevelStatistic.getLevelFilename()));
			}
		});

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
		
		mTable.add(movesLabel);
		mTable.row();
		mTable.add(teleportsLabel);
		mTable.row();
		mTable.add(trapsLabel);
		mTable.row();
		mTable.add(healthLabel);
		mTable.row();		
		mTable.add(repeatLevel).size(180, 30).uniform().spaceBottom(10).spaceTop(30);
		mTable.row();
		mTable.add(returnMenuScreen).size(180, 30).uniform().spaceBottom(10);
	}
	
	@Override
	public void hide() {
		super.hide();
		mAtlas.dispose();
		mSkin.dispose();
	}
}
