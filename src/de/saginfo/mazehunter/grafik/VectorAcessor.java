/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.grafik;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author sreis
 */
public class VectorAcessor implements TweenAccessor<Vector2> {

    public static final int POSITION = 2;
    public static final int POSITION_X = 0;
    public static final int POSITION_Y = 1;

    @Override
    public int getValues(Vector2 target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case POSITION:
                returnValues[0] = target.x;
                returnValues[1] = target.y;
                return 2;
            case POSITION_X:
                returnValues[0] = target.x;
                return 1;
            case POSITION_Y:
                returnValues[0] = target.y;
                return 1;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Vector2 target, int tweenType, float[] newValues) {
        switch(tweenType){
            case POSITION:
                target.set(newValues[0], newValues[1]);
                break;
            case POSITION_X:
                target.x = newValues[0];
                break;
            case POSITION_Y:
                target.y = newValues[0];
                break;
        }
    }

}
