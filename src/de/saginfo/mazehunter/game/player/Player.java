/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player;

import com.badlogic.gdx.math.Vector2;

public class Player {
    public int id;
    public String name;
    
    public final PlayerVisual visual;
    
    public final Vector2 position;
    public final Vector2 velocity;

    public Player() {
        this.position = new Vector2();
        this.velocity = new Vector2();
        visual = new PlayerVisual();
    }
    
    private final Vector2 tmp = new Vector2();
    public void update(float delta){
        this.position.add(tmp.set(velocity).scl(delta));
        visual.lerpPosition(position.x, position.y);
    }
}
