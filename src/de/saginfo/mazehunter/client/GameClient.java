/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.client;

import de.saginfo.mazehunter.client.networkData.abilities.dash.DashResponse;
import de.saginfo.mazehunter.client.networkData.abilities.dash.DashRequest;
import de.saginfo.mazehunter.client.networkData.abilities.AbilityConfigResponse;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Client;
import de.saginfo.mazehunter.client.networkData.ConnectResponse;
import de.saginfo.mazehunter.client.networkData.*;
import de.saginfo.mazehunter.client.networkData.abilities.blizzard.BlizzardConfigRequest;
import de.saginfo.mazehunter.client.networkData.abilities.blizzard.BlizzardConfigResponse;
import de.saginfo.mazehunter.client.networkData.abilities.dash.DashConfigRequest;
import de.saginfo.mazehunter.client.networkData.abilities.dash.DashConfigResponse;
import de.saginfo.mazehunter.client.networkData.abilities.standardHeal.StandardHealConfigRequest;
import de.saginfo.mazehunter.client.networkData.abilities.standardHeal.StandardHealConfigResponse;
import de.saginfo.mazehunter.client.networkData.abilities.standardHeal.StandardHealRequest;
import de.saginfo.mazehunter.client.networkData.abilities.standardHeal.StandardHealResponse;
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
        
        //Ability Stuff
        getKryo().register(AbilityConfigResponse.class);
        
        //Dash
        getKryo().register(DashRequest.class);
        getKryo().register(DashResponse.class);
        getKryo().register(DashConfigRequest.class);
        getKryo().register(DashConfigResponse.class);

        //StandardHeal
        getKryo().register(StandardHealRequest.class);
        getKryo().register(StandardHealResponse.class);
        getKryo().register(StandardHealConfigRequest.class);
        getKryo().register(StandardHealConfigResponse.class);
        
        //Blizzard
        getKryo().register(BlizzardConfigRequest.class);
        getKryo().register(BlizzardConfigResponse.class);
    }
}
