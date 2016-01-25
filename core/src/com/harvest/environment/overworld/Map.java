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

    public Map(String tilemapPath){
        tileMap = new TmxMapLoader().load(tilemapPath);
        renderer = new OrthogonalTiledMapRenderer(tileMap);
    }

    public TiledMapRenderer getRenderer(){
        return renderer;
    }


}
