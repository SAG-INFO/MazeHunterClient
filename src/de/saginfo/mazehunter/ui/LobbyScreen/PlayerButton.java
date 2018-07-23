/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.ui.LobbyScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import de.saginfo.mazehunter.client.GameClient;
import de.saginfo.mazehunter.client.networkData.lobby.OccupySlotRequest;
import de.saginfo.mazehunter.ui.UI;

/**
 *
 * @author arein
 */
public class PlayerButton extends TextButton{
    
    public final boolean boss;
    public String name;
    public int playerID = -1;
    
    public int index;
    
    private GameClient client;
    
    public PlayerButton(String name, boolean boss, int index, GameClient client){
        super(name, new PlayerButtonStyle());
        this.boss = boss;
        this.index = index;
        this.client = client;
        super.getLabelCell().padLeft(10).padRight(10);
        
        super.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                OccupySlotRequest r = new OccupySlotRequest();
                r.index = index;
                client.sendTCP(r);
            }
        });
    }
    
    public void removePlayer(){
        playerID = -1;
        setText("nutte");
    }
    
    public void setPlayer(String name, int id){
        playerID = id;
        setText(name);
    }
    
    private static class PlayerButtonStyle extends TextButton.TextButtonStyle{
        public PlayerButtonStyle() {
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
