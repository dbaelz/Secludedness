package de.dbaelz.secludedness;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;

import de.dbaelz.secludedness.MainGame;
import de.dbaelz.secludedness.manager.AndroidGPGSManager;
import de.dbaelz.secludedness.manager.GPGSManager;

public class MainActivity extends AndroidApplication implements GameHelperListener {
	private GameHelper mGameHelper;
	private GPGSManager mGPGSManager;
	private MainGame mGame;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        mGameHelper = new GameHelper(this);

        mGPGSManager = new AndroidGPGSManager(mGameHelper, this);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGL20 = true;
		cfg.useWakelock = true;
		cfg.useAccelerometer = true;
        
		mGame = new MainGame(mGPGSManager);
		initialize(mGame, cfg);
        mGameHelper.setup(this, GameHelper.CLIENT_GAMES | GameHelper.CLIENT_APPSTATE);
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	mGameHelper.onStart(this);
    }

    @Override
    protected void onStop() {
    	super.onStop();
    	mGameHelper.onStop();
    }
    
	@Override
	public void onSignInFailed() {
	}

	@Override
	public void onSignInSucceeded() {
	}
	
	public MainGame getGame() {
		return mGame;
	}
}