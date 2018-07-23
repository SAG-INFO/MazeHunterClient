/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.Entity.projectiles;

import com.badlogic.gdx.math.Vector2;
import de.saginfo.mazehunter.grafik.SpriteVisual;

/**
 *
 * @author Karl Huber
 */
public class FireballEntity extends Projectile {
    
    public FireballEntity(Vector2 position, int entityId) {
        super(position, new SpriteVisual("assets\\abilities\\Fireball\\fireball.png"), entityId);
        System.out.println(entityId);
        super.visual.setZIndex(100);
    }

    @Override
    public void shoot(Vector2 velocity) {
        super.shoot(velocity);
        super.visual.setRotation(velocity.angle());
    }
}
