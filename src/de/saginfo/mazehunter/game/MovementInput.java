/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game;

import com.badlogic.gdx.InputAdapter;

/**
 *
 * @author karl.huber
 */
public class MovementInput extends InputAdapter {

    @Override
    public boolean keyDown(int keycode) {
        
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(this);
        
        return false;
    }
    
    
    
    //TODO send

    public MovementInput() {
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(this);
    }
}
