/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.ui.connectScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.saginfo.mazehunter.MazeHunterMain;
import de.saginfo.mazehunter.client.GameClient;
import de.saginfo.mazehunter.ui.Menu;
import de.saginfo.mazehunter.ui.enterIpScreen.EnterIpScreen;
import java.io.IOException;

/**
 *
 * @author sreis
 */
public class ConnectScreen extends ScreenAdapter {

    private Stage stage;
    private Viewport viewport;

    public final GameClient client;
    private String ip;

    private Label label;
    
    public ConnectScreen(String ip) {
        this.ip = ip.trim().toLowerCase();
        this.client = new GameClient();
    }

    @Override
    public void show() {
        super.show();
        this.createUI();

        this.client.addListener(new ConnectSystem(this));
        connect();
    }
    
    private void createUI() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        viewport = new FillViewport(1920, 1080);
        stage.setViewport(viewport);

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        label = new Label("Connecting to " + ip + "...", new Label.LabelStyle(Menu.fancyFont, Color.BLACK));
        root.add(label);
    }

    public void displayError(String msg){
        label.setText(msg);
        
        Timer t = new Timer();
        t.scheduleTask(new Task() {
            @Override
            public void run() {
                client.close();
                MazeHunterMain.MAIN_SINGLETON.setScreen(new EnterIpScreen());
            }
        }, 4);
    }

    public void connect() {
        Timer timer = new Timer();
        timer.scheduleTask(new Task() {
            @Override
            public void run() {
                try {
                    client.connectToServer(ip);
                } catch (IOException ex) {
                    System.out.println("RIP");
                }
            }
        }, 0.5f);
    }
    
    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 1, 1, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        super.hide();
        dispose();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height, true);
    }
}
