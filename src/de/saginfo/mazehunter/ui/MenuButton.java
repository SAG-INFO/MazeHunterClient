/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import de.saginfo.mazehunter.ui.UI;

/**
 *
 * @author sreis
 */
public class MenuButton extends TextButton{

    public MenuButton(String text){
        super(text, new MenuButtonStyle());
        super.getLabelCell().padLeft(10).padRight(10);
    }

    private static class MenuButtonStyle extends TextButtonStyle{
        public MenuButtonStyle() {
            super();
            
            Sprite upSprite = new Sprite(new Texture("assets\\ui\\menuButtonUp.png"));
            Sprite downSprite = new Sprite(new Texture("assets\\ui\\menuButtonDown.png"));
            Sprite overSprite = new Sprite(new Texture("assets\\ui\\menuButtonOver.png"));
            
            up = new NinePatchDrawable(new NinePatch(upSprite, 10, 10, 10, 10));
            down = new NinePatchDrawable(new NinePatch(downSprite, 10, 10, 10, 10));
            over = new NinePatchDrawable(new NinePatch(overSprite, 10, 10, 10, 10));
            font = UI.fancyFont;
            fontColor = Color.BLACK;
        }
    }
}
