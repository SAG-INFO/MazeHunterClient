package de.saginfo.mazehunter.client.networkData.abilities;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

/**
 *
 * @author sreis
 */
public class PickupUpdate {

    public final ArrayList<PickupData> datas = new ArrayList<>();

    public static class PickupData {

        public Vector2 position;
        public String name;
    }
}
