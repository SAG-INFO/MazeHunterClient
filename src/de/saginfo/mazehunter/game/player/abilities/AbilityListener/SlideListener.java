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
        if(object instanceof SlideResponse){
            
            System.out.println("SlideResponse received.");
            
            final Vector2 velocity = new Vector2(Map.blockbreite, 0);
            int tmp;
            
            switch (((SlideResponse) object).direction) {
                case 'N':  velocity.setAngle(90); tmp = 1; break; 
                case 'O':  velocity.setAngle(0); tmp = 2; break;
                case 'S':  velocity.setAngle(180); tmp = 3; break;
                case 'W':  velocity.setAngle(270); tmp = 4; break;
            }
            
            System.out.println(" row:" + ((SlideResponse) object).row + "direction:" + ((SlideResponse) object).direction);
            GameScreen.GAMESCREEN_SINGLETON.game.world.movePlayers(velocity, ((SlideResponse) object).row);
            //TODO moveRow()
        }
    }

    public SlideListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
