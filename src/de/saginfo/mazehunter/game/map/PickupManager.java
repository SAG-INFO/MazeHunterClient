package de.saginfo.mazehunter.game.map;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.PickupUpdate;
import de.saginfo.mazehunter.game.GameScreen;
import java.util.ArrayList;

/**
 *
 * @author sreis
 */
public class PickupManager extends Listener{
    
    public final ArrayList<AbilityPickup> pickups = new ArrayList<>();

    public PickupManager() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }
    
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof PickupUpdate){
            PickupUpdate update = (PickupUpdate)object;
            for (AbilityPickup pickup : pickups) {
                pickup.dispose();
            }
            pickups.clear();
            
            for (PickupUpdate.PickupData data : update.datas) {
                AbilityPickup pickup = new AbilityPickup(data.position, data.name);
                pickups.add(pickup);
            }
            
        }
    }
}
