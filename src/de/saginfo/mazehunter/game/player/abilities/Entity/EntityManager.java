/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.Entity;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import de.saginfo.mazehunter.client.networkData.abilities.entity.DisposeEntity;
import de.saginfo.mazehunter.game.GameScreen;
import java.util.ArrayList;

/**
 *
 * @author karl.huber
 */
public class EntityManager {

    public ArrayList<AbilityEntity> entities;

    public void disposeEntity(int entityID) {
        entities.removeIf((e) ->{e.dispose();return e.entityID==entityID;});
    }
    
    public void disposeEntity(AbilityEntity e){
        e.dispose();
        entities.remove(e);
    }

    public void update(float delta) {
        entities.forEach((entity) -> {
            entity.update(delta);
        });
    }

    public EntityManager() {
        entities = new ArrayList<>();
        GameScreen.GAMESCREEN_SINGLETON.client.addListener((Connection c, Object o) -> {
            if(o instanceof  DisposeEntity){
                Gdx.app.postRunnable(() -> {
                    GameScreen.GAMESCREEN_SINGLETON.game.world.entityManager.disposeEntity(((DisposeEntity) o).entityID);
                });
            }
        });
    }
}
