package de.saginfo.mazehunter;

import com.badlogic.gdx.Game;
import de.saginfo.mazehunter.ui.enterIpScreen.EnterIpScreen;

public class LabyRindMain extends Game {

    public static LabyRindMain GAME_SINGLETON;
    
    @Override
    public void create() {
        GAME_SINGLETON = this;
       
        EnterIpScreen screen = new EnterIpScreen();
        setScreen(screen);
    }
    
}
