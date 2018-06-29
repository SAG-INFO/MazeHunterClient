/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.Entity.projectiles;

import com.badlogic.gdx.math.Vector2;
import de.saginfo.mazehunter.game.player.abilities.Entity.Entity;
import de.saginfo.mazehunter.grafik.SpriteVisual;

/**
 *
 * @author Karl Huber
 */
public abstract class Projectile extends Entity{
    
    Vector2 velocity;
    
    private final Vector2 tmp = new Vector2();
    
    @Override
    public void update(float delta){
        this.position.add(tmp.set(velocity).scl(delta));
        visual.setX(position.x);
        visual.setY(position.y);
    }

    public Projectile(Vector2 velocity, Vector2 position, SpriteVisual visual, int entityID) {
        super(position, entityID, visual);
        this.velocity = velocity;
    }
}

