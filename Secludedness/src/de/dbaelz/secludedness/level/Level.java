package de.dbaelz.secludedness.level;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Level {
	private TiledMap mMap;
	private TiledMapTileLayer mForegroundLayer = null;
	private int[] mPlayerCell; 
	
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
			} else if (mapLayers.get(i).getName().equals("player")) {
				setPlayerStartPosition(mapLayers.get(i).getObjects());
			}
		}
	}
	
	private void setPlayerStartPosition(MapObjects mapObjects) {
		if (mapObjects == null || mapObjects.getCount() != 1) {
			throw new GdxRuntimeException("Invalid map: Only one player start position allowed");
		}

		Rectangle playerBox = ((RectangleMapObject) mapObjects.get("startpoint")).getRectangle();		
		mPlayerCell = new int[2];
		mPlayerCell[0] = (int) Math.floor(playerBox.getX());
		mPlayerCell[1] = (int) Math.floor(playerBox.getY());
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
}
