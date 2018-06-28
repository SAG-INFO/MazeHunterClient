/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityInputs;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import de.saginfo.mazehunter.client.networkData.abilities.requests.SlideRequest;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author karl.huber
 */
public class SlideInput extends InputAdapter {

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.UP) {
            GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(new SlideRequest('N'));
        }
        if (keycode == Input.Keys.RIGHT) {
            GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(new SlideRequest('O'));
        }
        if (keycode == Input.Keys.DOWN) {
            GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(new SlideRequest('S'));
        }
        if (keycode == Input.Keys.LEFT) {
            GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(new SlideRequest('W'));
        }
        return false;
    }

    public SlideInput() {
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(this);
    }
}
