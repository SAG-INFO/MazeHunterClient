/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.AbilityListener;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.responses.TrapResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.TrapShootResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.abilities.Entity.AbilityEntity;
import de.saginfo.mazehunter.game.player.abilities.Entity.nonMoving.TrapEntity;
import de.saginfo.mazehunter.grafik.SpriteVisual;

/**
 *
 * @author Karl Huber
 */
public class TrapListener extends Listener {

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof TrapResponse) {
            Gdx.app.postRunnable(() -> {
                GameScreen.GAMESCREEN_SINGLETON.game.world.entityManager.entities.add(new TrapEntity(((TrapResponse) object).position, ((TrapResponse) object).entityID));
            });
            
        } else if (object instanceof TrapShootResponse) {
            Gdx.app.postRunnable(() -> {
                System.out.println("recccccc");
                GameScreen.GAMESCREEN_SINGLETON.game.world.entityManager.disposeEntity(((TrapShootResponse) object).entityID);
            });
        }
    }

    public TrapListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
