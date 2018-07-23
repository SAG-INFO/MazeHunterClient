package de.saginfo.mazehunter.game.player.abilities.Entity.nonMoving;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import com.badlogic.gdx.math.Vector2;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.abilities.Entity.AbilityEntity;
import de.saginfo.mazehunter.grafik.SpriteVisual;
import de.saginfo.mazehunter.grafik.VisualAccessor;

/**
 *
 * @author sreis
 */
public class SatanismEntity extends AbilityEntity{

    private SpriteVisual penta;
    private SpriteVisual bg;

    public SatanismEntity(Vector2 position, int entityID) {
        super(position, entityID);
        penta = new SpriteVisual("assets\\abilities\\satan\\penta.png");
        bg = new SpriteVisual("assets\\abilities\\satan\\bg.png");
        
        penta.setPosition(position.x-penta.getWidth()/2, position.y-penta.getHeight()/2);
        bg.setPosition(position.x-bg.getWidth()/2, position.y-bg.getHeight()/2);
        
        penta.setZIndex(110);
        bg.setZIndex(110);
        
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(penta);
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.addSprite(bg);
        
        Timeline timeline = Timeline.createSequence();
        
        Tween tween = Tween.to(bg, VisualAccessor.ALPHA, 0.5f).ease(TweenEquations.easeOutCirc);
        tween.target(0.2f, 0.7f, 0.2f);
        tween.repeat(3, 0.1f);
        
        timeline.push(tween);
        timeline.pushPause(0.1f);
        timeline.setCallback((int type, BaseTween<?> source) -> {
            GameScreen.GAMESCREEN_SINGLETON.game.world.entityManager.disposeEntity(this);
        });
        
        timeline.start(GameScreen.GAMESCREEN_SINGLETON.tweenManager);
    }

    @Override
    public void dispose() {
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.removeSprite(penta);
        GameScreen.GAMESCREEN_SINGLETON.renderSystem.removeSprite(bg);
    }
    
}
