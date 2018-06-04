/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author sreis
 */
public class HUD {
    
    public Stage stage;
    public Viewport hudViewport;
    public Table root;
    
    public TimerW timer;
    
    public HUD() {
        hudViewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 1920, 1080);
        stage = new Stage(hudViewport);
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(stage);
        
        root = new Table();
        root.setFillParent(true);
        root.bottom().left();
        stage.addActor(root);
        
        timer = new TimerW(10, 10);
        root.add(timer).pad(20);
    }
    
    public void update(float delta){
        stage.act(delta);
        stage.draw();
    }
    
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }
    
}
