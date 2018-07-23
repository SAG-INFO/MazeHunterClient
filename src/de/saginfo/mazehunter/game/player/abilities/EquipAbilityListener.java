package de.saginfo.mazehunter.game.player.abilities;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.pickups.EquipAbility;
import de.saginfo.mazehunter.game.GameScreen;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sreis
 */
public class EquipAbilityListener extends Listener {

    public EquipAbilityListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof EquipAbility) {
            GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((EquipAbility) object).id).attackAbility = ((EquipAbility) object).abilityName;
        }
    }
    
}
