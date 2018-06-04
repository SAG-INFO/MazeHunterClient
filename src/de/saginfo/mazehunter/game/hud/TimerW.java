package de.saginfo.mazehunter.game.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Timer;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.ui.UI;

/**
 *
 * @author sreis
 */
public class TimerW extends Label {
    private final Updater updater;
    
    public TimerW(int mins, int secs) {
        this(mins * 60 + secs);
    }

    public TimerW(int roundInSecs) {
        super("", new LabelStyle(UI.normalFont, Color.WHITE));
        super.setFontScale(0.5f);
        updater = new Updater(roundInSecs);
    }

    private void updateText(int remainingSeconds) {
        int minutes = (int)     (remainingSeconds % 3600) / 60;
        int seconds =            remainingSeconds % 60;
        
        setText((minutes<10?"0":"")+minutes + ":" +(seconds<10?"0":"")+ seconds);
    }

    private class Updater extends Listener {
        private int remainingSeconds;

        public Updater(int timeSeconds) {
            this.remainingSeconds = timeSeconds;
            
            Timer timer = new Timer();
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    remainingSeconds--;
                    TimerW.this.updateText(remainingSeconds);
                }
            }, 0, 1);
        }

        public void setTime(int time) {
            remainingSeconds = time;
        }

        @Override
        public void received(Connection connection, Object object) {
//            if (object instanceof PlayTimeUpdate) {
//                setTime(((PlayTimeUpdate) object).remainingSeconds);
//            }
        }
    }
}
