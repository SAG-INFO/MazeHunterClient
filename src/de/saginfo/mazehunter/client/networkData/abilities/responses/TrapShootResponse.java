/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.client.networkData.abilities.responses;

/**
 *
 * @author Karl Huber
 */
public class TrapShootResponse {
    public int connectionID;
    public int entityID;

    public TrapShootResponse(int connectionID, int entityID) {
        this.connectionID = connectionID;
        this.entityID = entityID;
    }

    public TrapShootResponse() {
    }
}
