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
	private TiledMapTileLayer mDamageLayer = null;
	private int[] mPlayerCell; 
	private int mPlayerStartHealth;
	
	public Level(String mapName) {
		loadMap(mapName);
		readMap();
	}
	
	private void loadMap(String mapName) {
		AssetManager assetManager = new AssetManager();
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		assetManager.load("maps/" + mapName + ".tmx", TiledMap.class);
		assetManager.finishLoading();
		mMap = assetManager.get("maps/" + mapName + ".tmx");				
	}
	
	private void readMap() {
		MapLayers mapLayers = mMap.getLayers();
		for (int i = 0; i < mapLayers.getCount(); i++) {
			if (mapLayers.get(i).getName().equals("foreground")) {
				mForegroundLayer = (TiledMapTileLayer) mapLayers.get(i);
			} else if (mapLayers.get(i).getName().equals("portal")) {
				mPortalLayer = (TiledMapTileLayer) mapLayers.get(i);
			} else if (mapLayers.get(i).getName().equals("damage")) {
				mDamageLayer = (TiledMapTileLayer) mapLayers.get(i);
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

		// Create empty layers, if no layers defined 
		if (mPortalLayer == null) {
			mPortalLayer = new TiledMapTileLayer(mForegroundLayer.getWidth(), mForegroundLayer.getHeight(), TILE_SIZE, TILE_SIZE);
		}
		if (mDamageLayer == null) {
			mDamageLayer = new TiledMapTileLayer(mForegroundLayer.getWidth(), mForegroundLayer.getHeight(), TILE_SIZE, TILE_SIZE);
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
		return checkCollidingWithLayer(mForegroundLayer, xPosition, yPosition);
	}
	
	public boolean isCollidingWithPortal(float xPosition, float yPosition) {	
		return checkCollidingWithLayer(mPortalLayer, xPosition, yPosition);
	}
	
	public boolean isCollidingWithDamage(float xPosition, float yPosition) {
		return checkCollidingWithLayer(mDamageLayer, xPosition, yPosition);
	}

	private boolean checkCollidingWithLayer(TiledMapTileLayer layer, float xPosition, float yPosition) {
		Cell cell = layer.getCell((int) (xPosition / TILE_SIZE), (int) (yPosition / TILE_SIZE));
		return cell != null && cell.getTile() != null;
	}
	
}
