package de.saginfo.mazehunter.ui.gameoverscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.saginfo.mazehunter.MazeHunterMain;
import de.saginfo.mazehunter.game.GameScreen;
import de.saginfo.mazehunter.ui.LobbyScreen.LobbyScreen;
import de.saginfo.mazehunter.ui.MenuButton;
import de.saginfo.mazehunter.ui.UI;
import de.saginfo.mazehunter.ui.enterIpScreen.EnterIpScreen;

/**
 *
 * @author sreis
 */
public class GameoverScreen extends ScreenAdapter{
    private Stage stage;
    private Viewport viewport;

    private Label label;
    
    public GameoverScreen() {
    }

    @Override
    public void show() {
        super.show();
        this.createUI();

    }
    
    private void createUI() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        viewport = new FillViewport(1920, 1080);
        stage.setViewport(viewport);

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        label = new Label("EOL-You suck", new Label.LabelStyle(UI.fancyFont, Color.BLACK));
        root.add(label).padBottom(50).row();
        
        MenuButton lobby = new MenuButton("GOTO Loby");
        MenuButton menu = new MenuButton("leave Game");
        root.add(lobby);
        root.add(menu);
        
        lobby.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                LobbyScreen lobbyScreen = new LobbyScreen(GameScreen.GAMESCREEN_SINGLETON.client);
                MazeHunterMain.MAIN_SINGLETON.setScreen(lobbyScreen);
            }
        });
        menu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameScreen.GAMESCREEN_SINGLETON.client.close();
                EnterIpScreen eip = new EnterIpScreen();
                MazeHunterMain.MAIN_SINGLETON.setScreen(eip);
            }
        });
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
