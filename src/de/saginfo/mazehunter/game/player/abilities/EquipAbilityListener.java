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
public class EquipAbilityListener extends Listener{

    public EquipAbilityListener() {
        GameScreen.GAMESCREEN_SINGLETON.client.addListener(this);
    }

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof EquipAbility) {
            switch (((EquipAbility)object).type) {
                case "attack":
                    GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((EquipAbility)object).id).attackAbility = ((EquipAbility)object).abilityName;
                    System.out.println(GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((EquipAbility)object).id).attackAbility + "collected.");
                    break;
                case "utility":
                    GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((EquipAbility)object).id).utilityAbility = ((EquipAbility)object).abilityName;
                    System.out.println(GameScreen.GAMESCREEN_SINGLETON.game.getPlayer(((EquipAbility)object).id).utilityAbility + "collected.");
                    break;
                default:
                    try {
                        throw new InvalidAbilityTypeException("String " + ((EquipAbility) object).type + "is not a valid type.");
                    } catch (InvalidAbilityTypeException ex) {
                        Logger.getLogger(EquipAbilityListener.class.getName()).log(Level.SEVERE, null, ex);
                    }   break;
            }
        }
    }
}

