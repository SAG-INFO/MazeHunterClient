package de.saginfo.mazehunter.game.player.abilities;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.EquipAbility;
import de.saginfo.mazehunter.game.GameScreen;

/**
 *
 * @author sreis
 */
public class EquipAbilityListener extends Listener{

    public EquipAbilityListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof EquipAbility){
            String abilityName = ((EquipAbility)object).abilityName;
            System.out.println("Hi karl could I please get the "+abilityName+" ability?      plz????");
        }
    }
}
