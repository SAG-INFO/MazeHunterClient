/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.abilities.Entity.nonMoving;

import com.badlogic.gdx.math.Vector2;
import de.saginfo.mazehunter.game.player.abilities.Entity.AbilityEntity;
import de.saginfo.mazehunter.grafik.SpriteVisual;

/**
 *
 * @author karl.huber
 */
public class TrapEntity extends AbilityEntity {

    public TrapEntity(Vector2 position, int entityID) {
        super(position, entityID, new SpriteVisual("assets\\abilities\\trap\\trap.png"));
        visual.setZIndex(95);
    }
}
