package de.dbaelz.secludedness;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.example.games.basegameutils.GameHelper;

import de.dbaelz.secludedness.MainGame;
import de.dbaelz.secludedness.manager.AndroidGPGSManager;

public class MainActivity extends AndroidApplication {
	private GameHelper mGameHelper;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        mGameHelper = new GameHelper(this);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGL20 = true;
		cfg.useWakelock = true;
		cfg.useAccelerometer = true;
        
        initialize(new MainGame(new AndroidGPGSManager()), cfg);
    }
}