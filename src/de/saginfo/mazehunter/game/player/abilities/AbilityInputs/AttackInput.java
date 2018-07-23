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
import de.saginfo.mazehunter.client.networkData.abilities.requests.AttackRequest;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Status;

/**
 *
 * @author Karl Huber
 */
public class AttackInput extends InputAdapter{
   
    private Vector2 getClickPosition(){
        final Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        tmp.set(GameScreen.GAMESCREEN_SINGLETON.camera.unproject(tmp));
        return new Vector2(tmp.x, tmp.y);
    }
    
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.SPACE && Status.canUseAbilities && GameScreen.GAMESCREEN_SINGLETON.config != null) {
            AttackRequest aR = new AttackRequest();
            aR.cursorPosition.set(getClickPosition());
            GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(aR);
        }
        return false;
    }
    
    public AttackInput() {
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(this);
    }
}

