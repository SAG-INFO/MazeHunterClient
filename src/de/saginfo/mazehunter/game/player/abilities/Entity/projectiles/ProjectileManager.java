/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.Entity.projectiles;

import de.saginfo.mazehunter.game.Game;
import de.saginfo.mazehunter.game.GameScreen;
import java.util.ArrayList;

/**
 *
 * @author Karl Huber
 */
public class ProjectileManager {
    public ArrayList<Projectile> projectiles;
    
    public void disposeProjectile(int id) {
        for (Projectile projectile : projectiles) {
            if (projectile.entityID == id) {
                GameScreen.GAMESCREEN_SINGLETON.renderSystem.removeSprite(projectile.visual);
                projectiles.remove(projectile);
                return;
            }
        }
    }
    
    public ProjectileManager() {
        projectiles = new ArrayList<>();
    }
}
