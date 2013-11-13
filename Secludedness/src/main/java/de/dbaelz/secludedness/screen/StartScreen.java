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

public class StartScreen extends AbstractScreen {
	private Image mStartImage;
	private Texture mTexture;
	public StartScreen(MainGame game) {
		super(game);		
	}

	@Override
	public void show() {
		super.show();
		
		mTexture = new Texture(Gdx.files.internal("data/logo.png"));
		mStartImage = new Image(mTexture);
		mStartImage.setFillParent(true);
		mStartImage.getColor().a = 0f;
		
		Action changeScreen = new Action() {
			@Override
			public boolean act(float delta) {
				mGame.setScreen(new MenuScreen(mGame));
				return true;
			}
		};
		mStartImage.addAction(sequence(fadeIn(1.5f), delay(1f), fadeOut(1.5f), changeScreen));		
		mStage.addActor(mStartImage);
	}
	
	@Override
	public void hide() {
		super.hide();
		mTexture.dispose();
	}
}
