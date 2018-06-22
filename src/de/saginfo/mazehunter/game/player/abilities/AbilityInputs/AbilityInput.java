/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityInputs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author Karl Huber
 * 
 * parentclass for all inputs 
 */
public class AbilityInput extends InputAdapter {
        public float getMouseAngle() {
        final Vector2 playerPos = new Vector2(GameScreen.GAMESCREEN_SINGLETON.game.getLocalPlayer().position);
        final Vector3 unpPlayerPos3 = new Vector3(GameScreen.GAMESCREEN_SINGLETON.camera.unproject(new Vector3(playerPos.x, playerPos.y, 0)));
        final Vector2 unpPlayerPos2 = new Vector2(unpPlayerPos3.x, unpPlayerPos3.y);
        final Vector2 tmp = new Vector2(Gdx.input.getX(), Gdx.input.getY()).sub(unpPlayerPos2);
        tmp.y = -tmp.y;
        return tmp.angle();
    }
}
