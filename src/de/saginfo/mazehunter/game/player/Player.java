/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player;

import com.badlogic.gdx.math.Vector2;
import de.saginfo.mazehunter.game.GameScreen;

/**
 * 
 * @author karl.huber
 * 
 */
public class Player {
    public int id;
    public String name;
    
    public final PlayerVisual visual;
    
    public final Vector2 position;
    public final Vector2 velocity;
    
    public String attackAbility;
    public String utilityAbility;
    public String mobilityAbility;

    public Player(int id, String name, Vector2 position) {
        this.position = new Vector2(position);
        this.velocity = new Vector2();
        this.id = id;
        this.name = name;
        visual = new PlayerVisual(id==GameScreen.GAMESCREEN_SINGLETON.client.getID());
    }
    
    private final Vector2 tmp = new Vector2();
    public void update(float delta){
        this.position.add(tmp.set(velocity).scl(delta));
        visual.lerpPosition(position.x, position.y);
    }
    
    public boolean isLocal(){
        return id == GameScreen.GAMESCREEN_SINGLETON.client.getID();
    }
}
