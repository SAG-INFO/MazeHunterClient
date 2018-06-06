/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.client;

import de.saginfo.mazehunter.client.networkData.abilities.DashResponse;
import de.saginfo.mazehunter.client.networkData.abilities.MobilityRequest;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Client;
import de.saginfo.mazehunter.client.networkData.ConnectResponse;
import de.saginfo.mazehunter.client.networkData.*;
import de.saginfo.mazehunter.client.networkData.abilities.AttackRequest;
import de.saginfo.mazehunter.client.networkData.abilities.FireballResponse;
import de.saginfo.mazehunter.client.networkData.abilities.UtilityRequest;
import de.saginfo.mazehunter.client.networkData.abilities.StandardHealResponse;
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
        
        //Dash
        getKryo().register(MobilityRequest.class);
        getKryo().register(DashResponse.class);

        //StandardHeal
        getKryo().register(UtilityRequest.class);
        getKryo().register(StandardHealResponse.class);
        
        //SO NE GROßE FEUERBALL JUNGE
        getKryo().register(AttackRequest.class);
        getKryo().register(FireballResponse.class);
        
        //Blizzard
    }
}
