package de.saginfo.mazehunter.grafik;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author sreis
 */
public class SpriteVisual extends Sprite implements Visual{
    private int zIndex;

    public SpriteVisual(String name) {
        this(new Texture(name));
    }
    
    public SpriteVisual() {
    }

    public SpriteVisual(Texture texture) {
        super(texture);
    }

    public SpriteVisual(TextureRegion region) {
        super(region);
    }

    public SpriteVisual(Sprite sprite) {
        super(sprite);
    }
    
    
    
    @Override
    public void draw(SpriteBatch batch, float delta) {
        super.draw(batch);
    }

    public void setZIndex(int zIndex){
        this.zIndex = zIndex;
    }
    
    @Override
    public int getZIndex() {
        return zIndex;
    }

    @Override
    public void dispose() {
        getTexture().dispose();
    }
}
