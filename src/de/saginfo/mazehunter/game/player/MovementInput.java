/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author karl.huber
 */
public class MovementInput extends InputAdapter {
    
    

    @Override
    public boolean keyDown(int keycode) {
        float angle;
        if(keycode == Keys.W) {
            angle = 0;
        }
        if(keycode == Keys.A) {
            angle = 270;
        }
        if(keycode == Keys.S) {
            angle = 180;            
        }
        if(keycode == Keys.D) {
            angle = 90;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Keys.W) {
            
        }
        if(keycode == Keys.A) {
            
        }
        if(keycode == Keys.S) {
            
        }
        if(keycode == Keys.D) {
            
        }
        return false;
    }
    
    //TODO send
    public MovementInput() {
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(this);
    }
}
