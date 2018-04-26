/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.client;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryonet.Client;
import de.saginfo.mazehunter.client.networkData.ConnectResponse;
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
        getKryo().register(Vector2.class);
        getKryo().register(ArrayList.class);
        getKryo().register(ConnectResponse.class);
    }
}
