package de.saginfo.mazehunter.ui.LobbyScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.saginfo.mazehunter.client.GameClient;
import de.saginfo.mazehunter.client.networkData.lobby.PlayerLobby;
import de.saginfo.mazehunter.client.networkData.StartGameRequest;
import de.saginfo.mazehunter.ui.MenuButton;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arein
 */
public class LobbyScreen extends ScreenAdapter{
    
    private Stage stage;
    
    private Viewport viewport;

    public GameClient client;

    
    public List<PlayerLobby> players = new ArrayList<>();
    public ArrayList<TextButton> buttons = new ArrayList<>();
    
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
        root.setFillParent(true);
        stage.addActor(root);
        
        Table up = new Table();
        Table mid = new Table();
        Table down = new Table();
        
        buttons.add(new PlayerButton("Boss", true, 0, client));
        buttons.add(new PlayerButton("Uno", false, 1, client));
        buttons.add(new PlayerButton("Dos", false, 2, client));
        buttons.add(new PlayerButton("Tres", false, 3, client));
        
        up.add(buttons.get(0)).width(350);
        mid.add(buttons.get(1)).padRight(60).width(350);
        mid.add(buttons.get(2)).padRight(60).width(350);
        mid.add(buttons.get(3)).padRight(60).width(350);
        MenuButton startButton = new MenuButton("Start Game");
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                client.sendUDP(new StartGameRequest());
            }
        });
        down.add(startButton).padBottom(100);
        
        root.add(up).padBottom(60);
        root.row();
        root.add(mid).padBottom(100);
        root.row();
        root.add(down);
        
    }
    
    public void updatePlayers(ArrayList<PlayerLobby> players){
        this.players.clear();
        this.players.addAll(players);
        
        for (TextButton button : buttons) {
            button.setText("free");
        }
        
        for (PlayerLobby player : players) {
            if(player.slot != -1)
                this.buttons.get(player.slot).setText(player.name);
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
