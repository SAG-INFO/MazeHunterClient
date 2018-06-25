/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityInputs;

import com.badlogic.gdx.Input.Keys;
import de.saginfo.mazehunter.client.networkData.abilities.requests.AttackRequest;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Status;

/**
 *
 * @author Karl Huber
 */
public class AttackInput extends AbilityInput {
   
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.SPACE && Status.canUseAbilities && GameScreen.GAMESCREEN_SINGLETON.config != null) {
            AttackRequest aR = new AttackRequest(getMouseAngle());
            GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(aR);
            System.out.println(getMouseAngle());
        }
        return false;
    }
    
    public AttackInput() {
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(this);
    }
}

