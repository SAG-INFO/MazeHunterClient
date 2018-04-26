/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player;

import com.badlogic.gdx.math.Vector2;
/**
 *
 * @author s.reiser
 */

/**
 *
 * @author s.reiser
 */
    public class Player {

    //TODO Graphic Attribut
    private Vector2 position;
    private Vector2 velocity;

    public Player(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
}
