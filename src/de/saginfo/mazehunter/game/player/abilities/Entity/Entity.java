/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.Entity;

import com.badlogic.gdx.math.Vector2;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.grafik.Visual;

/**
 *
 * @author karl.huber
 * 
 * parent class for all Entities.
 */
public class Entity {
    public final Vector2 position;
    public int entityID;
    public Visual visual;

    public Entity(Vector2 position, int entityID, Visual visual) {
        this.position = position;
        this.entityID = entityID;
        this.visual = visual;
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visual);
    }
    
    public void update(float delta) {}
}
