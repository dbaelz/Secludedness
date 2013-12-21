package de.dbaelz.secludedness.level;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Level {
	private final int TILE_SIZE = 64;
	
	private TiledMap mMap;
	private TiledMapTileLayer mForegroundLayer = null;
	private TiledMapTileLayer mPortalLayer = null;
	private TiledMapTileLayer mTrapLayer = null;
	private TiledMapTileLayer mExitLayer = null;
	private TiledMapTileLayer mFogLayer = null;
	private int[] mPlayerCell; 
	private int mPlayerStartHealth;
	private boolean mLevelFinished;
	private LevelStatistic mLevelStatistic;
	
	public Level(String mapName) {
		loadMap(mapName);
		readMap();
		mLevelStatistic = new LevelStatistic(getPlayerStartHealth(), mapName);
	}
	
	private void loadMap(String mapName) {
		AssetManager assetManager = new AssetManager();
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		assetManager.load(mapName, TiledMap.class);
		assetManager.finishLoading();
		mMap = assetManager.get(mapName);				
	}
	
	private void readMap() {
		MapLayers mapLayers = mMap.getLayers();
		for (int i = 0; i < mapLayers.getCount(); i++) {
			if (mapLayers.get(i).getName().equals("foreground")) {
				mForegroundLayer = (TiledMapTileLayer) mapLayers.get(i);
			} else if (mapLayers.get(i).getName().equals("portal")) {
				mPortalLayer = (TiledMapTileLayer) mapLayers.get(i);
			} else if (mapLayers.get(i).getName().equals("trap")) {
				mTrapLayer = (TiledMapTileLayer) mapLayers.get(i);
			} else if (mapLayers.get(i).getName().equals("exit")) {
				mExitLayer = (TiledMapTileLayer) mapLayers.get(i);
			} else if (mapLayers.get(i).getName().equals("fog")) {
				mFogLayer = (TiledMapTileLayer) mapLayers.get(i);
			} else if (mapLayers.get(i).getName().equals("player")) {
				setPlayerStartInformation(mapLayers.get(i).getObjects());
			}
		}
		
		if (mForegroundLayer == null) {
			throw new GdxRuntimeException("Invalid map: No foreground layer found!");
		}
		
		if (mForegroundLayer.getTileHeight() != TILE_SIZE || mForegroundLayer.getTileWidth() != TILE_SIZE) {
			throw new GdxRuntimeException("Invalid map: Tile size must be 64x64!");
		}
		
		if (mExitLayer == null) {
			throw new GdxRuntimeException("Invalid map: No exit found!");
		}

		// Create empty layers, if no layers defined 
		if (mPortalLayer == null) {
			mPortalLayer = new TiledMapTileLayer(mForegroundLayer.getWidth(), mForegroundLayer.getHeight(), TILE_SIZE, TILE_SIZE);
		}
		if (mTrapLayer == null) {
			mTrapLayer = new TiledMapTileLayer(mForegroundLayer.getWidth(), mForegroundLayer.getHeight(), TILE_SIZE, TILE_SIZE);
		}
		if (mFogLayer == null) {
			mFogLayer = new TiledMapTileLayer(mForegroundLayer.getWidth(), mForegroundLayer.getHeight(), TILE_SIZE, TILE_SIZE);
		}
	}
	
	private void setPlayerStartInformation(MapObjects mapObjects) {
		if (mapObjects == null || mapObjects.getCount() != 1) {
			throw new GdxRuntimeException("Invalid map: Only one player start position allowed!");
		}

		Rectangle playerBox = ((RectangleMapObject) mapObjects.get("startpoint")).getRectangle();		
		mPlayerCell = new int[2];
		mPlayerCell[0] = (int) Math.floor(playerBox.getX());
		mPlayerCell[1] = (int) Math.floor(playerBox.getY());
		
		String healthProperty = (String) mapObjects.get("startpoint").getProperties().get("health");
		if (healthProperty != null) {
			mPlayerStartHealth = Integer.parseInt(healthProperty);
		} else {
			mPlayerStartHealth = 10;
		}
		
		// Remove fog on start position
		if (mFogLayer != null) {
			removeFog(mPlayerCell[0], mPlayerCell[1]);
		}
	}

	public TiledMap getMap() {
		return mMap;
	}
	
	public int getPlayerCellX() {
		return mPlayerCell[0];
	}
	
	public int getPlayerCellY() {
		return mPlayerCell[1];
	}
	
	public int getPlayerStartHealth() {
		return mPlayerStartHealth;
	}
	
	public boolean isCollidingWithForeground(float xPosition, float yPosition) {	
		if ((xPosition / TILE_SIZE) >= mForegroundLayer.getWidth() || (xPosition / TILE_SIZE) < 0
				|| (yPosition / TILE_SIZE) >= mForegroundLayer.getHeight() || (yPosition / TILE_SIZE) < 0) {
			return true;
		}
		return checkCollidingWithLayer(mForegroundLayer, xPosition, yPosition, false);
	}
	
	public boolean isCollidingWithPortal(float xPosition, float yPosition) {	
		return checkCollidingWithLayer(mPortalLayer, xPosition, yPosition, false);
	}
	
	public boolean isCollidingWithTrap(float xPosition, float yPosition) {
		return checkCollidingWithLayer(mTrapLayer, xPosition, yPosition, false);
	}

	public boolean isCollidingWithExit(float xPosition, float yPosition) {
		return checkCollidingWithLayer(mExitLayer, xPosition, yPosition, false);
	}
	
	public boolean removeFog(float xPosition, float yPosition) {
		return checkCollidingWithLayer(mFogLayer, xPosition, yPosition, true);
	}
	
	private boolean checkCollidingWithLayer(TiledMapTileLayer layer, float xPosition, float yPosition, boolean remove) {
		int xCell = (int) (xPosition / TILE_SIZE);
		int yCell = (int) (yPosition / TILE_SIZE);
		Cell cell = layer.getCell(xCell, yCell);
		if (remove){
			layer.setCell(xCell, yCell, null);
		}
		return cell != null && cell.getTile() != null;
	}

	public boolean isFinished() {
		return mLevelFinished;
	}

	public void setFinished(boolean levelFinished) {
		this.mLevelFinished = levelFinished;
	}
	
	public LevelStatistic getLevelStatistic() {
		return this.mLevelStatistic;
	}
	
}
