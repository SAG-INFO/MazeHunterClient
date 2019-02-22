package de.saginfo.mazehunter.game.map.pickups;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquation;
import aurelienribon.tweenengine.TweenEquations;
import de.saginfo.mazehunter.game.GameScreen;
import static de.saginfo.mazehunter.game.GameScreen.GAMESCREEN_SINGLETON;
import de.saginfo.mazehunter.game.map.Map;
import de.saginfo.mazehunter.grafik.SpriteVisual;
import de.saginfo.mazehunter.grafik.VisualAccessor;

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
        
        Timeline tl = Timeline.createSequence().push(
            Tween.to(visual, VisualAccessor.POSITION_Y, 0.5f).targetRelative(4f).ease(TweenEquations.easeOutSine)
        ).push(
            Tween.to(visual, VisualAccessor.POSITION_Y, 0.5f).targetRelative(-4).ease(TweenEquations.easeOutSine)
        ).repeat(-1, 0).start(GAMESCREEN_SINGLETON.tweenManager);
    }
    
    public void dispose(){
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.removeSprite(visual);
    }
    
    public float getOffset(){
        return Map.center/2f-visual.getWidth()/2f;
    }
}
