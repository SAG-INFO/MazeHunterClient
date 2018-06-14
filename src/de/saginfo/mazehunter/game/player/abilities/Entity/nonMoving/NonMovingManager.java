/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.Entity.nonMoving;

import de.saginfo.mazehunter.game.GameScreen;
import java.util.ArrayList;

/**
 *
 * @author karl.huber
 */
public class NonMovingManager {
    
    public ArrayList<NonMoving> nonMoving;
    
    public void disposeNonMoving(int entityID) {
        for (NonMoving n : nonMoving) {
            if (n.entityID == entityID) {
                GameScreen.GAMESCREEN_SINGLETON.renderSystem.removeSprite(n.visual);
                nonMoving.remove(n);
                return;
            }
        }
    }

    public NonMovingManager() {
        nonMoving = new ArrayList<>();
    }
}