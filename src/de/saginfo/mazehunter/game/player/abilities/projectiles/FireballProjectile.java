/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.projectiles;

import com.badlogic.gdx.math.Vector2;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.grafik.SpriteVisual;

/**
 *
 * @author Karl Huber
 */
public class FireballProjectile extends Projectile {
    
    public FireballProjectile(Vector2 velocity, Vector2 position, float radius, SpriteVisual visual) {
        super(velocity, position, radius, visual);
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visual);
    }
}
