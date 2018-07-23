package de.saginfo.mazehunter.game.player.abilities.AbilityListener;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.client.networkData.abilities.responses.SatanResponse;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.abilities.Entity.nonMoving.SatanismEntity;

/**
 *
 * @author arein
 */
public class SatanListener extends Listener{
    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof SatanResponse){
            Gdx.app.postRunnable(() ->{
                SatanismEntity entity = new SatanismEntity(((SatanResponse) object).position, ((SatanResponse) object).playerId);
                GameScreen.GAMESCREEN_SINGLETON.game.world.entityManager.entities.add(entity);
            });
        }
    }
}
