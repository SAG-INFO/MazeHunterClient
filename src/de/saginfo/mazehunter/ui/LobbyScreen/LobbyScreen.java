package de.saginfo.mazehunter.ui.LobbyScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.saginfo.mazehunter.client.GameClient;
import de.saginfo.mazehunter.client.networkData.PlayerLobby;
import de.saginfo.mazehunter.client.networkData.StartGameRequest;
import de.saginfo.mazehunter.ui.MenuButton;
import de.saginfo.mazehunter.ui.connectScreen.ConnectSystem;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author sreis
 */
public class LobbyScreen extends ScreenAdapter{

    private Stage stage;
    private Table labeltable;
    
    private Viewport viewport;

    public GameClient client;

    public ArrayList<PlayerLobby> players = new ArrayList<>();
    
    public LobbyScreen(GameClient client) {
        this.client = client;
    }

    @Override
    public void show() {
        super.show();
        this.createUI();
        client.addListener(new LobbyListener(this));
    }
    
    private void createUI() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        viewport = new FillViewport(1920, 1080);
        stage.setViewport(viewport);
        
        Table root = new Table();
        
        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        
        labeltable = new Table();
        root.add(labeltable).expand();
        root.row();
        
        MenuButton startButton = new MenuButton("Start Game");
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                client.sendUDP(new StartGameRequest());
            }
        });
        root.add(startButton).padBottom(100);
    }
    
    public void updatePlayers(ArrayList<PlayerLobby> players){
        labeltable.clearChildren();
        this.players.clear();
        
        
        this.players  = players;
        for (PlayerLobby player : players) {
            PlayerLabel l = new PlayerLabel(player.name);
            labeltable.add(l);
            labeltable.row();
        }
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
