/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.ui.enterIpScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import de.saginfo.mazehunter.ui.UI;

/**
 *
 * @author sreis
 */
public class EnterIpField extends TextField{

    public EnterIpField() {
        super("localhost", new MenuTextFieldStyle());
    }
    
    private static class MenuTextFieldStyle extends TextField.TextFieldStyle{
        public MenuTextFieldStyle() {
            
            Sprite bg = new Sprite(new Texture("assets\\ui\\textField.png"));
            background = new NinePatchDrawable(new NinePatch(bg, 10, 10, 10, 10));
//            selection = new NinePatchDrawable(new NinePatch(atlas.createSprite("selection"), 0, 0, 0, 0));
            font = UI.normalFont;
            fontColor = Color.BLACK;
        }
    }
}
