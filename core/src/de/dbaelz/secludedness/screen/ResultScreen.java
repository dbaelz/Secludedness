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
import de.dbaelz.secludedness.manager.GPGSAchievement;
import de.dbaelz.secludedness.manager.GPGSManager;

public class ResultScreen extends AbstractScreen {
	private LevelStatistic mLevelStatistic;
	private TextureAtlas mAtlas;
	private Skin mSkin;
	private Table mTable;

	public ResultScreen(MainGame game, LevelStatistic statistic) {
		super(game);
		mLevelStatistic = statistic;
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
		
		Label finishedLabel = new Label("== LEVEL FINISHED ==", mSkin);
		String result = "You've actually survived!";
		if (mLevelStatistic.getHealth() == 0) {
			result = "OOOhhh, you lost!";
		}
		Label resultLabel = new Label(result, mSkin);
		
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
		
		mTable.add(finishedLabel);
		mTable.row();
		mTable.add(resultLabel);
		mTable.row();
		mTable.add(movesLabel);
		mTable.row();
		mTable.add(teleportsLabel);
		mTable.row();
		mTable.add(trapsLabel);
		mTable.row();
		mTable.add(healthLabel);
		mTable.row();		
		mTable.add(repeatLevel).size(buttonWidth, buttonHeight).uniform().spaceBottom(10).spaceTop(30);
		mTable.row();
		mTable.add(returnMenuScreen).size(buttonWidth, buttonHeight).uniform().spaceBottom(10);
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
			manager.unlockAchievement(GPGSAchievement.PLAY_RANDOM_LEVEL.getAchievementID());
			manager.incrementAchievement(GPGSAchievement.FIFTY_RANDOM_LEVELS.getAchievementID(), 1);
			
			// Moves
			if (mLevelStatistic.getMoves() > 0) {
				manager.incrementAchievement(GPGSAchievement.ONEHUNDRED_MOVES.getAchievementID(), mLevelStatistic.getMoves());
				manager.incrementAchievement(GPGSAchievement.FIVEHUNDRED_MOVES.getAchievementID(), mLevelStatistic.getMoves());
				manager.incrementAchievement(GPGSAchievement.THOUSAND_MOVES.getAchievementID(), mLevelStatistic.getMoves());
			}
			
			// Teleports
			if (mLevelStatistic.getTeleports() > 0) {
				manager.incrementAchievement(GPGSAchievement.ONEHUNDRED_TELEPORTS.getAchievementID(), mLevelStatistic.getTeleports());
				manager.incrementAchievement(GPGSAchievement.TWOHUNDRED_TELEPORTS.getAchievementID(), mLevelStatistic.getTeleports());
				manager.incrementAchievement(GPGSAchievement.THREEHUNDRED_TELEPORTS.getAchievementID(), mLevelStatistic.getTeleports());	
			}
	
			// Damage
			int damage = Math.abs(mLevelStatistic.getHealth() - mLevelStatistic.getStartHealth());
			if (damage > 0) {
				manager.incrementAchievement(GPGSAchievement.ONEHUNDRED_DAMAGE.getAchievementID(), damage);
				manager.incrementAchievement(GPGSAchievement.TWOHUNDRED_DAMAGE.getAchievementID(), damage);
				manager.incrementAchievement(GPGSAchievement.THREEHUNDRED_DAMAGE.getAchievementID(), damage);
			}
			
			// Lose or win
			if (mLevelStatistic.getHealth() == 0) {
				manager.unlockAchievement(GPGSAchievement.FAIL_LEVEL.getAchievementID());
				manager.incrementAchievement(GPGSAchievement.LOSE_TWOHUNDRED_LEVELS.getAchievementID(), 1);			
			} else {
				manager.unlockAchievement(GPGSAchievement.MASTER_LEVEL.getAchievementID());
				manager.incrementAchievement(GPGSAchievement.WIN_TWOHUNDRED_LEVELS.getAchievementID(), 1);
			}
		}
	}
}
