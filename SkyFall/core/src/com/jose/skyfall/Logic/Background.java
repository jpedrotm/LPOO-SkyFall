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

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private int tiledHeight;
    private int tiledWidth;

    public Background(String worldName){
        mapLoader = new TmxMapLoader();
        map=mapLoader.load(worldName);
        renderer= new OrthogonalTiledMapRenderer(map);

        MapProperties prop = map.getProperties();
        tiledHeight=prop.get("height", Integer.class) * prop.get("tileheight", Integer.class);
        tiledWidth=prop.get("width", Integer.class) * prop.get("tilewidth", Integer.class);
    }

    public int getTiledWidth(){
        return tiledWidth;
    }

    public int getTiledHeight(){
        return tiledHeight;
    }

    public void update(OrthographicCamera camera){
        renderer.setView(camera);
    }

    public void render(){
        renderer.render();
    }
}
