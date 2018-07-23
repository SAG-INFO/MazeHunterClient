/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.client;

import de.saginfo.mazehunter.client.networkData.lobby.LobbyUpdate;
import de.saginfo.mazehunter.client.networkData.lobby.PlayerLobby;
import de.saginfo.mazehunter.client.networkData.abilities.responses.DashResponse;
import de.saginfo.mazehunter.client.networkData.abilities.requests.MobilityRequest;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.saginfo.mazehunter.MazeHunterMain;
import de.saginfo.mazehunter.client.networkData.ConnectResponse;
import de.saginfo.mazehunter.client.networkData.*;
import de.saginfo.mazehunter.client.networkData.abilities.pickups.DisposePickup;
import de.saginfo.mazehunter.client.networkData.abilities.pickups.EquipAbility;
import de.saginfo.mazehunter.client.networkData.abilities.pickups.SpawnPickup;
import de.saginfo.mazehunter.client.networkData.abilities.entity.DisposeEntity;
import de.saginfo.mazehunter.client.networkData.abilities.requests.AttackRequest;
import de.saginfo.mazehunter.client.networkData.abilities.requests.SlideRequest;
import de.saginfo.mazehunter.client.networkData.abilities.responses.FireballDispose;
import de.saginfo.mazehunter.client.networkData.abilities.responses.FireballResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.SatanResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.SlideResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.SpeedBoostResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.StunArrowDispose;
import de.saginfo.mazehunter.client.networkData.abilities.responses.StunArrowResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.TrapResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.TrapShootResponse;
import de.saginfo.mazehunter.client.networkData.configs.PushConfig;
import de.saginfo.mazehunter.client.networkData.lobby.OccupySlotRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sreis
 */
public class GameClient extends Client {

    private final int TIMEOUT = 5000;
    private final int TCP_PORT = 54777;
    private final int UDP_PORT = 54779;

    public GameClient() {
        registerClasses();
    }

    public void addListener(TestInt r){
        super.addListener(new Listener(){
            @Override
            public void received(Connection connection, Object object) {
                r.run(connection, object);
            }
        });
    }
    
    public void connectToServer(String ip) throws IOException {
        super.start();
        super.connect(TIMEOUT, ip, TCP_PORT, UDP_PORT);
        
        super.addListener(new Listener(){
            @Override
            public void connected(Connection connection) {
                MazeHunterMain.MAIN_SINGLETON.NETWORK_ID = getID();
            }
        });
    }

    private void registerClasses() {
        //general Stuff
        getKryo().register(Vector2.class);
        getKryo().register(ArrayList.class);
        getKryo().register(List.class);
        
        //Lobby Stuff
        getKryo().register(ConnectResponse.class);
        getKryo().register(PlayerLobby.class);
        getKryo().register(LobbyUpdate.class);
        getKryo().register(OccupySlotRequest.class);
        
        getKryo().register(StartGameRequest.class);
        getKryo().register(StartGameResponse.class);
        getKryo().register(SpawnPlayer.class);
        getKryo().register(Gameover.class);
        
        //Movement Stuff
        getKryo().register(MovementRequest.class);
        getKryo().register(MovementResponse.class);
        
        //Config Stuff
        getKryo().register(PushConfig.class);

        //Ability Stuff
        getKryo().register(HealthUpdate.class);
        getKryo().register(CanUseAbilitiesUpdate.class);
        getKryo().register(CanMoveUpdate.class);
        
        //AbilityRequests
        getKryo().register(AttackRequest.class);
        getKryo().register(MobilityRequest.class);
        getKryo().register(SlideRequest.class);
        
        //AbilityResponses
        getKryo().register(DashResponse.class);
        getKryo().register(StunArrowResponse.class);
        getKryo().register(StunArrowDispose.class);
        getKryo().register(TrapResponse.class);
        getKryo().register(TrapShootResponse.class);
        getKryo().register(SlideResponse.class);
        getKryo().register(FireballResponse.class);
        getKryo().register(FireballDispose.class);
        getKryo().register(SpeedBoostResponse.class);
        getKryo().register(SatanResponse.class);
        
        //Entity Stuff
        getKryo().register(DisposeEntity.class);

        //Pickups
        getKryo().register(SpawnPickup.class);
        getKryo().register(DisposePickup.class);
        getKryo().register(EquipAbility.class);
        
        getKryo().register(PlayerVisualUpdate.class);
    }
    
    @FunctionalInterface
    public static interface TestInt{
        public void run(Connection c, Object o);
    }
}
