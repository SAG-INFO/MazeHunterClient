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
    
    public FireballEntity(Vector2 velocity, Vector2 position, SpriteVisual visual, int id) {
        super(velocity, position, visual, id);
    }
}
