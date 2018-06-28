package de.saginfo.mazehunter.game.map.pickups;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.pickups.DisposePickup;
import de.saginfo.mazehunter.client.networkData.abilities.pickups.SpawnPickup;
import de.saginfo.mazehunter.game.GameScreen;
import java.util.HashMap;

/**
 *
 * @author sreis
 */
public class PickupManager extends Listener{
    
    public final HashMap<Integer, AbilityPickup> pickups = new HashMap<>();

    public PickupManager() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
    
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof SpawnPickup){
            SpawnPickup sp = (SpawnPickup)object;
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    pickups.put(sp.id, new AbilityPickup(sp.position, sp.abilityName));
                    System.out.println("gntrgtrgiohjtzhjzjuik");
                }
            });
        }else if(object instanceof DisposePickup){
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    pickups.remove(((DisposePickup) object).id).dispose();
                }
            });
        }
    }
}
