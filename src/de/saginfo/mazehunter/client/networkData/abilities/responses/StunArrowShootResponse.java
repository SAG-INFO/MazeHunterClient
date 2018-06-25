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
public class StunArrowShootResponse {
    public int playerID;
    public int entityID;
    public float stunDuration;

    public StunArrowShootResponse(int playerID, int projectileID, float stunDuration) {
        this.playerID = playerID;
        this.entityID = projectileID;
        this.stunDuration = stunDuration;
    }

    public StunArrowShootResponse() {
    }
}
