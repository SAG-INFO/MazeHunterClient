/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.client.networkData.abilities;

import de.saginfo.mazehunter.game.player.abilities.AbilityConfigs.DashConfig;

/**
 *
 * @author Karl Huber
 */
public class AbilityConfigResponse {
    
    public DashConfig abilityConfig;

    public AbilityConfigResponse(DashConfig abilityConfig) {
        this.abilityConfig = abilityConfig;
    }

    public AbilityConfigResponse() {
    }
}
