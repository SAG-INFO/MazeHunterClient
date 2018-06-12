/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.projectiles;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.projectiles.DisposeProjectile;
import de.saginfo.mazehunter.game.Game;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author Karl Huber
 */
public class ProjectileListener extends Listener{
    
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof DisposeProjectile) {
            System.out.println("DisposeProjectile received.");
            GameScreen.GAMESCREEN_SINGLETON.game.projectileManager.disposeProjectile(((DisposeProjectile) object).id);
        }
    }

    public ProjectileListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
}
