/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.client.networkData.abilities;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Karl Huber
 */
public class FireballResponse {
    int id;
    public Vector2 position;
    public Vector2 velocity;

    public FireballResponse(int id, Vector2 position, Vector2 velocity) {
        this.id = id;
        this.position = position;
        this.velocity = velocity;
    }

    public FireballResponse() {
    }
}
