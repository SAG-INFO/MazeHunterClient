/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author karl.huber
 */
public class MovementInput extends InputAdapter {

    private Vector2 requestedVector = new Vector2(0, 0);

    public void update(float delta) {
        
        if (Gdx.input.isKeyJustPressed(Keys.W)) {
            requestedVector.y += 1;
        }
        if (Gdx.input.isKeyJustPressed(Keys.S)) {
            requestedVector.y -= 1;
        }
        if (Gdx.input.isKeyJustPressed(Keys.D)) {
            requestedVector.x += 1;
        }
        if (Gdx.input.isKeyJustPressed(Keys.A)) {
            requestedVector.x -= 1;
        }
        
        
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.W) {

        }
        if (keycode == Keys.A) {

        }
        if (keycode == Keys.S) {

        }
        if (keycode == Keys.D) {

        }
        return false;
    }

    //TODO send
    public MovementInput() {
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(this);
    }
}
