/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityInputs;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Timer;
import de.saginfo.mazehunter.client.networkData.abilities.MobilityRequest;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Status;

/**
 *
 * @author Karl Huber
 * 
 * Checks if the user pressed space as well as if the player is currently effected by cc Abilities and sends a dashrequest if needed.
 */
public class MobilityInput extends InputAdapter {
    
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.SHIFT_LEFT && Status.canMove == 0 && Status.canUseAbilities == 0 && GameScreen.GAMESCREEN_SINGLETON.config != null) {
                GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(new MobilityRequest());
        }
        return false;
    }
    
    public MobilityInput() {
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(this);
    }
}
