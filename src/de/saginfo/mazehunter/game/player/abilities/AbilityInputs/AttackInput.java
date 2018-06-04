/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityInputs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.saginfo.mazehunter.client.networkData.abilities.AttackRequest;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Status;

/**
 *
 * @author Karl Huber
 */
public class AttackInput extends InputAdapter {
   
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.SPACE && Status.canUseAbilities == 0 && GameScreen.GAMESCREEN_SINGLETON.config != null) {
            GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(new AttackRequest(getMouseAngle()));
        }
        return false;
    }
    
    public float getMouseAngle() {
        final Vector2 playerPos = new Vector2(GameScreen.GAMESCREEN_SINGLETON.game.getLocalPlayer().position);
        final Vector2 mousePos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        final Vector3 unpPlayerPos3 = new Vector3(GameScreen.GAMESCREEN_SINGLETON.camera.unproject(new Vector3(playerPos.x, playerPos.y, 0)));
        final Vector2 unpPlayerPos2 = new Vector2(unpPlayerPos3.x, unpPlayerPos3.y);
        final Vector2 playerToMouse = new Vector2(mousePos.sub(unpPlayerPos2));
        return playerToMouse.angle();
    }
    
    public AttackInput() {
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(this);
    }
}

