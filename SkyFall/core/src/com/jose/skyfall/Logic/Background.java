package com.jose.skyfall.Logic;

import com.badlogic.gdx.graphics.OrthographicCamera;
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

    public Background(){
        mapLoader = new TmxMapLoader();
        map=mapLoader.load("background1.tmx");
        renderer= new OrthogonalTiledMapRenderer(map);
    }

    public void update(OrthographicCamera camera){
        renderer.setView(camera);
    }

    public void render(){
        renderer.render();
    }
}
