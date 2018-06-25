/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.Entity;

import de.saginfo.mazehunter.game.GameScreen;
import java.util.ArrayList;

/**
 *
 * @author karl.huber
 */
public class EntityManager {
    
    public ArrayList<Entity> entities;

    public void disposeEntity(int entityID) throws EntityNotFoundException {
        for (Entity e : entities) {
            if (e.entityID == entityID) {
                GameScreen.GAMESCREEN_SINGLETON.renderSystem.removeSprite(e.visual);
                entities.remove(e);
                return;
            }
        }
        String s = "Entity with ID " + entityID + " doesn't exist.";
        throw new EntityNotFoundException(s);
    }

    public void update(float delta){
        entities.forEach((entity) -> {entity.update(delta);});
    }
    
    public EntityManager() {
        entities = new ArrayList<>();
    }
}
