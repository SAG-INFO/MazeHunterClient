/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityListener;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.responses.SlideResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.map.Map;

/**
 *
 * @author karl.huber
 */
public class SlideListener extends Listener{
    
    @Override
    public void received(Connection connection, Object object)  {
        System.out.println(object);
        if(object instanceof SlideResponse){
            
            System.out.println("SlideResponse received.");
            
            final Vector2 velocity = new Vector2(Map.blockbreite, 0);
            int dir = 0;
            
            switch (((SlideResponse) object).direction) {
                case 1:  velocity.setAngle(90); dir = 1; break; 
                case 2:  velocity.setAngle(0); dir = 2; break;
                case 3:  velocity.setAngle(180); dir = 3; break;
                case 4:  velocity.setAngle(270); dir = 4; break;
            }
            
            GameScreen.GAMESCREEN_SINGLETON.game.world.map.moveRow(((SlideResponse) object).row, dir);
        }
    }

    public SlideListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
