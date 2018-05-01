package de.saginfo.mazehunter;

import com.badlogic.gdx.Game;
import de.saginfo.mazehunter.ui.enterIpScreen.EnterIpScreen;

public class MazeHunterMain extends Game {

    public static MazeHunterMain MAIN_SINGLETON;
    
    @Override
    public void create() {
        MAIN_SINGLETON = this;
       
        EnterIpScreen screen = new EnterIpScreen();
        setScreen(screen);
    }
    
}
