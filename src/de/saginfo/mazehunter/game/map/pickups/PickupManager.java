package de.saginfo.mazehunter.game.map.pickups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.pickups.DisposePickup;
import de.saginfo.mazehunter.client.networkData.abilities.pickups.SpawnPickup;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.map.Centeropen;
import de.saginfo.mazehunter.game.map.Map;
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
                    spawnPickup(sp.id, sp.blockX, sp.blockY, sp.abilityName);
                }
            });
        }else if(object instanceof DisposePickup){
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    disposePickup(((DisposePickup) object).id);
                }
            });
        }
    }
    
    private void spawnPickup(int pickupID, int tileX, int tileY, String abilityName){
        Map m = GameScreen.GAMESCREEN_SINGLETON.game.world.map;
        
        Centeropen center = (Centeropen) m.blocklist[tileX][tileY].tilelist[1][1];
        
        AbilityPickup pickup = new AbilityPickup(abilityName);
        center.pickup = pickup;
        pickups.put(pickupID, pickup);
        center.setPosition();
    }
    private void disposePickup(int pickupID){
        pickups.remove(pickupID).dispose();
    }
}
