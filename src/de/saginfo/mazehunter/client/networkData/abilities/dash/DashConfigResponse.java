/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.client.networkData.abilities.dash;

import de.saginfo.mazehunter.game.player.abilities.AbilityConfigs.DashConfig;

/**
 *
 * @author Karl Huber
 */
public class DashConfigResponse {
    public DashConfig dashConfig;

    public DashConfigResponse(DashConfig dashConfig) {
        this.dashConfig = dashConfig;
    }

    public DashConfigResponse() {
    }
}
