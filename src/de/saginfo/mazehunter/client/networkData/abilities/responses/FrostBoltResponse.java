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
public class FrostBoltResponse {
    public int entityID;
    public int connectionID;
    public Vector2 velocity;
    public float rotation;

    public FrostBoltResponse(int projectileID, int connectionID, Vector2 velocity, float rotation) {
        this.entityID = projectileID;
        this.connectionID = connectionID;
        this.velocity = velocity;
        this.rotation = rotation;
    }

    public FrostBoltResponse() {
    }
}