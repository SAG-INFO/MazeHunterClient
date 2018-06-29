/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.grafik;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author sreis
 */
public class VisualAccessor implements TweenAccessor<Visual>{
        
    public static final int ALPHA = 0;
    public static final int TINT = 1;
    public static final int ROTATION = 2;
    public static final int SCALE = 3;
    public static final int SCALE_X = 4;
    public static final int SCALE_Y = 5;
    public static final int POSITION_X = 6;
    public static final int POSITION_Y = 7;
    public static final int POSITION = 8;
    
    @Override
    public int getValues(Visual target, int tweenType, float[] returnValues) {
        switch(tweenType){
            case ALPHA:
                returnValues[0] = target.getColor().a;
                return 1;
            case ROTATION:
                returnValues[0] = target.getRotation();
                return 1;
            case SCALE:
                returnValues[0] = target.getScaleX();
                returnValues[1] = target.getScaleY();
                return 2;
            case SCALE_Y:
                returnValues[0] = target.getScaleY();
                return 1;
            case SCALE_X:
                returnValues[0] = target.getScaleX();
                return 1;
            case POSITION_X:
                returnValues[0] = target.getX();
                return 1;
            case POSITION_Y:
                returnValues[0] = target.getY();
                return 1;
            case POSITION:
                returnValues[0] = target.getX();
                returnValues[1] = target.getY();
                return 2;
            case TINT:
                returnValues[0] = target.getColor().r;
                returnValues[1] = target.getColor().g;
                returnValues[2] = target.getColor().b;
                returnValues[3] = target.getColor().a;
                return 4;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Visual target, int tweenType, float[] newValues) {
        switch(tweenType){
            case ALPHA:
                target.setColor(target.getColor().r, target.getColor().g, target.getColor().b, newValues[0]);
                break;
            case ROTATION:
                target.setRotation(newValues[0]);
                break;
            case SCALE_Y:
                target.setScale(target.getScaleX(), newValues[0]);
                break;
            case SCALE_X:
                target.setScale(newValues[0], target.getScaleY());
                break;
            case POSITION_X:
                target.setPosition(newValues[0], target.getY());
                break;
            case POSITION_Y:
                target.setPosition(target.getX(), newValues[0]);
                break;
            case POSITION:
                target.setPosition(newValues[0], newValues[1]);
                break;
            case SCALE:
                target.setScale(newValues[0], newValues[1]);
                break;
            case TINT:
                target.setColor(newValues[0], newValues[1], newValues[2], newValues[3]);
                break;
            default:
                assert false;
        }
    }
}
