package de.saginfo.mazehunter.grafik;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author sreis
 */
public interface Visual {
    public void draw(SpriteBatch batch, float delta);
    public int getZIndex();
    public void dispose();
}
