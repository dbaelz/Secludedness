package de.dbaelz.secludedness.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import de.dbaelz.secludedness.MainGame;
import de.dbaelz.secludedness.level.Level;
import de.dbaelz.secludedness.level.Player;
import de.dbaelz.secludedness.manager.InputManager;

public class LevelScreen extends AbstractScreen {
	private MainGame mGame; 
	private Level mLevel;
	private Player mPlayer;
	private InputManager mInputManager;
	private Texture mTexture;
	private TextureRegion mPlayerTexture;
	private BitmapFont mFont;
	
		
	private SpriteBatch mBatch = new SpriteBatch();
	private OrthographicCamera mCamera;
	private OrthogonalTiledMapRenderer mMapRenderer;
	
	private boolean usePolling;
	
	public LevelScreen(MainGame game, String mapName) {
		super(game);		
		mGame = game;
		mLevel = new Level(mapName);
	}

	@Override
	public boolean isLevelScreen() {
		return true;
	}
	
	@Override
	public void render(float delta) {
		doGameLogic(delta);
		
		super.render(delta);
		
		mMapRenderer.setView(mCamera);
		mMapRenderer.render();

		mBatch.begin();
		mBatch.draw(mPlayerTexture, mPlayer.getPositionX(), mPlayer.getPositionY());
		// TODO: Debug, remove with nice hud
		mFont.draw(mBatch, "HEALTH: " + mPlayer.getHealth(), 50, 50);		
		mBatch.end();		
	}
	
	@Override
	public void show() {
		super.show();
		
		mPlayer = new Player(mLevel.getPlayerCellX(), mLevel.getPlayerCellY(), mLevel.getPlayerStartHealth());
		mTexture = new Texture(Gdx.files.internal("textures/texture.png"));
		mPlayerTexture = new TextureRegion(mTexture, 64, 0, 64, 64);
		
		mFont = new BitmapFont();
		mFont.setColor(1.0f, 0.5f, 1.0f, 1.0f);
		
		// TODO: Change input based on settings
		if ((Gdx.app.getType() == ApplicationType.Android) && (Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer))) {
			usePolling = true;
		} else {
			usePolling = false;						
		}
		mInputManager = new InputManager(mGame, mLevel, mPlayer);
		Gdx.input.setInputProcessor(mInputManager);
	}
	
	@Override
	public void resize(int width, int height) {
		mMapRenderer = new OrthogonalTiledMapRenderer(mLevel.getMap(), 1.0f);
		mCamera = new OrthographicCamera();
		mCamera.setToOrtho(false, width, height);
		mCamera.update();
	}
	
	@Override
	public void dispose() {
		mMapRenderer.dispose();
		mTexture.dispose();
		mFont.dispose();
		super.dispose();
	}

	private void doGameLogic(float delta){
		if (usePolling) {
			mInputManager.pollPlayerInput(delta, mLevel, mPlayer);	
		}
					

		if (mPlayer.getHealth() == 0) {
			// TODO: GAME OVER!
		}
	}
}
