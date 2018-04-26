/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player;

import com.badlogic.gdx.math.Vector2;
public class Player {

        
    public PlayerVisual visual;
    
    public Vector2 position;
    public Vector2 velocity;

    public Player(Vector2 position) {
        this.position = position;
        velocity = new Vector2();
        visual = new PlayerVisual();
    }
}
