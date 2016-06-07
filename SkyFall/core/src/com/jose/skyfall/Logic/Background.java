package com.jose.skyfall.Logic;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Created by Jose on 11/05/2016.
 */
public class Background {

    /** Loader of game's map */
    private TmxMapLoader mapLoader;
    /** Game Map */
    private TiledMap map;
    /** Map's renderer */
    private OrthogonalTiledMapRenderer renderer;
    /** Map's height */
    private int tiledHeight;
    /** Map's width */
    private int tiledWidth;

    /**
     * Background constructor
     * @param worldName name of the tmx file that contains the map
     */
    public Background(String worldName){
        mapLoader = new TmxMapLoader();
        map=mapLoader.load(worldName);
        renderer= new OrthogonalTiledMapRenderer(map);

        MapProperties prop = map.getProperties();
        tiledHeight=prop.get("height", Integer.class) * prop.get("tileheight", Integer.class);
        tiledWidth=prop.get("width", Integer.class) * prop.get("tilewidth", Integer.class);
    }

    /**
     * Returns map's width
     * @return Map's width
     */
    public int getTiledWidth(){
        return tiledWidth;
    }

    /**
     * Returns map's height
     * @return Map's height
     */
    public int getTiledHeight(){
        return tiledHeight;
    }

    /**
     * Updates the map's pixels that are going to be drawn
     * @param camera
     */
    public void update(OrthographicCamera camera){
        renderer.setView(camera);
    }

    /**
     * Draws the visible part of the Map
     */
    public void render(){
        renderer.render();
    }

    /**
     * Disposes the Map
     */
    public void dispose(){
        map.dispose();
        renderer.dispose();
    }
}
