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
public class FireballShootResponse {
    public int playerID;
    public int projectileID;

    public FireballShootResponse(int playerID, int projectileID) {
        this.playerID = playerID;
        this.projectileID = projectileID;
    }

    public FireballShootResponse() {
    }
}
