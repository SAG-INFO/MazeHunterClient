/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.player.movement;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import de.saginfo.mazehunter.client.networkData.MovementRequest;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.game.player.Status;
import static de.saginfo.mazehunter.game.player.Status.canUseAbilities;

/**
 *
 * @author Karl Huber
 */
public class MovementInput extends InputAdapter {

    private boolean cancel;

    private Vector2 direction = new Vector2(0, 0);

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.W) {
            direction.y += 1;
            sendMovementRequest(direction);
        }
        if (keycode == Keys.S) {
            direction.y += -1;
            sendMovementRequest(direction);
        }
        if (keycode == Keys.D) {
            direction.x += 1;
            sendMovementRequest(direction);
        }
        if (keycode == Keys.A) {
            direction.x += -1;
            sendMovementRequest(direction);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.W) {
            direction.y -= 1;
            sendMovementRequest(direction);
        }
        if (keycode == Keys.S) {
            direction.y += 1;
            sendMovementRequest(direction);
        }
        if (keycode == Keys.D) {
            direction.x -= 1;
            sendMovementRequest(direction);
        }
        if (keycode == Keys.A) {
            direction.x += 1;
            sendMovementRequest(direction);
        }
        return false;
    }

    public void sendMovementRequest(Vector2 requestedVector) {

        boolean movement;

        if (direction.isZero()) {
            movement = false;
        } else {
            movement = true;
        }

        if (Status.canMove == 0) {

            MovementRequest movementRequest = new MovementRequest((int) requestedVector.angle(), movement);
            GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(movementRequest);

        } else {

            cancelOld();
            while (Status.canMove > 0) {
                if (cancel = true) {return;}
            }
            MovementRequest movementRequest = new MovementRequest((int) requestedVector.angle(), movement);
            GameScreen.GAMESCREEN_SINGLETON.client.sendUDP(movementRequest);
        }
    }

    public void cancelOld() {
        cancel = true;
        Timer t = new Timer();
        t.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                cancel = false;
            }
        }, 0, 05);
    }

    public MovementInput() {
        GameScreen.GAMESCREEN_SINGLETON.inputMultiplexer.addProcessor(this);
    }
}
