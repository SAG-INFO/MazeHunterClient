package de.saginfo.mazehunter.ui.LobbyScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.saginfo.mazehunter.ui.UI;

/**
 *
 * @author sreis
 */
public class PlayerLabel extends Label{
    
    public PlayerLabel(CharSequence text) {
        super(text, new PLStyle());
    }
    
    private static class PLStyle extends LabelStyle{
        public PLStyle() {
            super(UI.fancyFont, Color.BLACK);
        }
    }
    
}
