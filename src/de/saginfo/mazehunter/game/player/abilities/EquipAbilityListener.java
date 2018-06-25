package de.saginfo.mazehunter.game.player.abilities;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.pickups.EquipAbility;
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
        if(object instanceof EquipAbility) {
            if (((EquipAbility)object).type.equals("attack")) {
                GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((EquipAbility)object).id).attackAbility = ((EquipAbility)object).abilityName;
                System.out.println(GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((EquipAbility)object).id).attackAbility + "collected.");
            }
            if (((EquipAbility)object).type.equals("utility")) {
                GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((EquipAbility)object).id).utilityAbility = ((EquipAbility)object).abilityName;
                System.out.println(GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((EquipAbility)object).id).utilityAbility + "collected.");
            }
        }
    }
}
