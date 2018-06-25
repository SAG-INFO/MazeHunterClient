/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.client;

import de.saginfo.mazehunter.client.networkData.abilities.responses.DashResponse;
import de.saginfo.mazehunter.client.networkData.abilities.requests.MobilityRequest;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Client;
import de.saginfo.mazehunter.client.networkData.ConnectResponse;
import de.saginfo.mazehunter.client.networkData.*;
import de.saginfo.mazehunter.client.networkData.abilities.pickups.DisposePickup;
import de.saginfo.mazehunter.client.networkData.abilities.pickups.EquipAbility;
import de.saginfo.mazehunter.client.networkData.abilities.pickups.SpawnPickup;
import de.saginfo.mazehunter.client.networkData.abilities.entity.DisposeEntity;
import de.saginfo.mazehunter.client.networkData.abilities.requests.AttackRequest;
import de.saginfo.mazehunter.client.networkData.abilities.requests.SlideRequest;
import de.saginfo.mazehunter.client.networkData.abilities.requests.UtilityRequest;
import de.saginfo.mazehunter.client.networkData.abilities.responses.FireballResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.FireballShootResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.FrostBoltResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.FrostBoltShootResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.SlideResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.StandardHealResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.StunArrowResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.StunArrowShootResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.TrapResponse;
import de.saginfo.mazehunter.client.networkData.abilities.responses.TrapShootResponse;
import de.saginfo.mazehunter.client.networkData.configs.PushConfig;
import java.io.IOException;
import java.util.ArrayList;


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
    
    public void connectToServer(String ip) throws IOException {
        super.start();
        super.connect(TIMEOUT, ip, TCP_PORT, UDP_PORT);
    }
    
    private void registerClasses(){
        //general Stuff
        getKryo().register(Vector2.class);
        getKryo().register(ArrayList.class);
        
        //Lobby Stuff
        getKryo().register(ConnectResponse.class);
        getKryo().register(PlayerLobby.class);
        getKryo().register(LobbyUpdate.class);
        getKryo().register(StartGameRequest.class);
        getKryo().register(StartGameResponse.class);
        
        //Movement Stuff
        getKryo().register(MovementRequest.class);
        getKryo().register(MovementResponse.class);
        getKryo().register(MovementSpeedRequest.class);
        
        //Config Stuff
        getKryo().register(PushConfig.class);

        //Ability Stuff
        getKryo().register(HealthUpdate.class);
        getKryo().register(CanUseAbilitiesUpdate.class);
        getKryo().register(CanMoveUpdate.class);
        
        //AbilityRequests
        getKryo().register(AttackRequest.class);
        getKryo().register(UtilityRequest.class);
        getKryo().register(MobilityRequest.class);
        getKryo().register(SlideRequest.class);
        
        //AbilityResponses
        getKryo().register(DashResponse.class);
        getKryo().register(StandardHealResponse.class);
        getKryo().register(FrostBoltResponse.class);
        getKryo().register(FrostBoltShootResponse.class);
        getKryo().register(StunArrowResponse.class);
        getKryo().register(StunArrowShootResponse.class);
        getKryo().register(TrapResponse.class);
        getKryo().register(TrapShootResponse.class);
        getKryo().register(SlideResponse.class);
        getKryo().register(FireballResponse.class);
        getKryo().register(FireballShootResponse.class);
        
        //Entity Stuff
        getKryo().register(DisposeEntity.class);

        //Pickups
        getKryo().register(SpawnPickup.class);
        getKryo().register(DisposePickup.class);
        getKryo().register(EquipAbility.class);
    }
}
