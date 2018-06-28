package de.saginfo.mazehunter.grafik;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author sreis
 */
public interface Visual {
    
    public void draw(SpriteBatch batch, float delta);
    public int getZIndex();
    public void setZIndex(int zIndex);
    public void dispose();

    public void setX(float f);
    public void setY(float f);

    public Color getColor();
    public float getRotation();
    public float getScaleX();
    public float getScaleY();
    public float getX();
    public float getY();
    public void setRotation(float rotation);
    public void setScale(float scaleX, float newValue);
    public void setPosition(float x, float y);
    public void setColor(float r, float g, float b, float a);
    
}
