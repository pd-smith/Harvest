package com.harvest.environment.overworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;

/**
 * Created by Patty on 1/25/2016.
 */
public class Map {

    TiledMap tileMap;
    TiledMapRenderer renderer;

    int[] backgroundLayer;
    int[] collisionLayer;
    int[] foregroundLayer;

    public Map(String tilemapPath, int[] background, int[] collision, int[] foreground){
        backgroundLayer = background;
        collisionLayer = collision;
        foregroundLayer = foreground;

        tileMap = new TmxMapLoader().load(tilemapPath);
        renderer = new OrthogonalTiledMapRenderer(tileMap);
    }

    public TiledMapRenderer getRenderer(){
        return renderer;
    }

    public int[] getBackground(){
        return backgroundLayer;
    }

    public int[] getCollision(){
        return collisionLayer;
    }

    public int[] getForeground(){
        return foregroundLayer;
    }

    public TiledMapTileLayer getBackgroundLayer(){
        return (TiledMapTileLayer) tileMap.getLayers().get(backgroundLayer[0]);
    }

    public TiledMapTileLayer getCollisionLayer(){
        return (TiledMapTileLayer) tileMap.getLayers().get(collisionLayer[0]);
    }

    public TiledMapTileLayer getForegroundLayer(){
        return (TiledMapTileLayer) tileMap.getLayers().get(foregroundLayer[0]);
    }



}
