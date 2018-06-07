/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.projectiles;

import com.badlogic.gdx.math.Vector2;
import de.saginfo.mazehunter.grafik.SpriteVisual;
import de.saginfo.mazehunter.grafik.Visual;

/**
 *
 * @author Karl Huber
 */
public abstract class Projectile {
    
    Vector2 velocity;
    Vector2 position;
    float radius;
    int id;
    SpriteVisual visual;
    
    public void dispose() {}
    
    public void shoot() {}
    
    private final Vector2 tmp = new Vector2();
    public void update(float delta){
        this.position.add(tmp.set(velocity).scl(delta));
        visual.setX(position.x);
        visual.setY(position.y);
    }

    public Projectile(Vector2 velocity, Vector2 position, float radius, SpriteVisual visual, int id) {
        this.velocity = velocity;
        this.position = position;
        this.radius = radius;
        this.visual = visual;
        this.id = id;
    }
}

