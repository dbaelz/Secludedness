package de.dbaelz.secludedness.screen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import de.dbaelz.secludedness.MainGame;
import de.dbaelz.secludedness.manager.AudioManager.MusicFile;

public class StartScreen extends AbstractScreen {
	private MainGame mGame;
	
	public StartScreen(MainGame game) {
		super(game);
		mGame = game;
	}

	@Override
	public void show() {
		super.show();
		
		mGame.getAudioManager().playMusic(MusicFile.THE_FINAL_END, true);
		
		final Image logoImage = new Image(new Texture(Gdx.files.internal("images/startscreen.png")));
		logoImage.setFillParent(true);
		logoImage.getColor().a = 0f;
		
		Action changeScreen = new Action() {
			@Override
			public boolean act(float delta) {
				mGame.setScreen(new MenuScreen(mGame));
				return true;
			}
		};
		
		logoImage.addAction(sequence(fadeIn(1.5f), delay(1.5f), fadeOut(1.5f), changeScreen));		
		mStage.addActor(logoImage);		
	}
	
	@Override
	public void hide() {
		dispose();
		super.hide();
	}
}
