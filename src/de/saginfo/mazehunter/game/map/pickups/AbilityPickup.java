package de.saginfo.mazehunter.game.map.pickups;

import com.badlogic.gdx.math.Vector2;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.map.Map;
import de.saginfo.mazehunter.grafik.SpriteVisual;

/**
 *
 * @author sreis
 */
public class AbilityPickup {
    public SpriteVisual visual;
    public String abilityName;

    public AbilityPickup(String name) {
        this.abilityName = name;
        
        visual = new SpriteVisual("assets\\abilities\\"+name+"\\pickup.png");
        visual.setZIndex(70);
        
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(visual);
    }
    
    public void dispose(){
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.removeSprite(visual);
    }
    
    public float getOffset(){
        return Map.center/2f-visual.getWidth()/2f;
    }
}
