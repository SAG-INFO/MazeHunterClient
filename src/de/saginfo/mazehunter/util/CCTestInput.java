/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.util;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Status;

/**
 *
 * @author Karl Huber
 */
public class CCTestInput extends InputAdapter {
    
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.E) {
            Status.stun(2);
        }
        if (keycode == Input.Keys.R) {
            Status.root(2);
        }
        if (keycode == Input.Keys.T) {
            Status.silence(2);
        }
        if (keycode == Input.Keys.Z) {
            Status.changeMovementSpeed(2, -0.5f);
        }
        if (keycode == Input.Keys.U) {
            Status.changeMovementSpeed(2, 0.5f);
        }
        return false;
    }
    
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }
    
    public CCTestInput() {
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(this);
    }
}
