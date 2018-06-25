/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.client.networkData.abilities.responses;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Karl Huber
 */
public class TrapResponse {
    public Vector2 position;
    public int connectionID;
    public int entityID;

    public TrapResponse(Vector2 position, int connectionID, int entityID) {
        this.position = position;
        this.connectionID = connectionID;
        this.entityID = entityID;
    }

    public TrapResponse() {
    }
}
