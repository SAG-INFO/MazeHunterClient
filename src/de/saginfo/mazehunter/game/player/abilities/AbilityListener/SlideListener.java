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
            
            final Vector2 velocity = new Vector2(Map.blockbreite, 0);
            char xory; 
            final Vector2 position = new Vector2();
            
            switch (((SlideResponse) object).direction) {
                case 'N':  velocity.setAngle(90); xory = 'y'; position.set(((SlideResponse) object).row*Map.blockbreite, 0);
                case 'O':  velocity.setAngle(0); xory = 'x';position.set(0, ((SlideResponse) object).row*Map.blockbreite);
                case 'S':  velocity.setAngle(180); xory = 'y'; position.set(((SlideResponse) object).row*Map.blockbreite, 0);
                case 'W':  velocity.setAngle(270); xory = 'x';position.set(0, ((SlideResponse) object).row*Map.blockbreite);
                default: xory = 'L'; 
            }
//        GameScreen.GAMESCREEN_SINGLETON.game.world.movePlayers(position, velocity, xory);
        }
    }

    public SlideListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
