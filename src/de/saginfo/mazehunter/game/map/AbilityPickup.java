package de.saginfo.mazehunter.game.map;

import com.badlogic.gdx.math.Vector2;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.grafik.SpriteVisual;

/**
 *
 * @author sreis
 */
public class AbilityPickup {
    public final Vector2 position;
    public SpriteVisual visual;
    public String abilityName;

    public AbilityPickup(Vector2 position, String name) {
        System.out.println("cool");
        this.position = new Vector2(position);
        this.abilityName = name;
        
        visual = new SpriteVisual("assets\\abilities\\"+name+"Pickup.png");
        visual.setPosition(position.x, position.y);
        visual.setZIndex(10);
        
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visual);
    }
    
    public void dispose(){
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.removeSprite(visual);
    }
}
