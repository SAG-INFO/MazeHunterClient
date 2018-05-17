/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.client.networkData;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author karl.huber
 */
public class DashResponse {
    public Vector2 position;
    public int id;

    public DashResponse(Vector2 position, int id) {
        this.position = position;
        this.id = id;
    }

    public DashResponse() {
    }
}
