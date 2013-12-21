package de.dbaelz.secludedness.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.dbaelz.secludedness.MainGame;

public abstract class AbstractScreen implements Screen {
	protected final MainGame mGame;
	protected final Stage mStage;

	public AbstractScreen(MainGame game) {
		mGame = game;
		mStage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
	}

	public boolean isLevelScreen() {
		return false;
	}
	
	@Override
	public void render(float delta) {
		if (isLevelScreen()) {
			Gdx.gl.glClearColor(0.98f, 0.98f, 0.98f, 1.0f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);									
		} else {
			mStage.act(delta);			
			Gdx.gl.glClearColor(0.98f, 0.98f, 0.98f, 1.0f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);			
			mStage.draw();	
		}
	}

	@Override
	public void resize(int width, int height) {
		mStage.setViewport(400, 300, true);
	}

	@Override
	public void show() {
		if (!isLevelScreen()) {
			Gdx.input.setInputProcessor(mStage);	
		}
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
