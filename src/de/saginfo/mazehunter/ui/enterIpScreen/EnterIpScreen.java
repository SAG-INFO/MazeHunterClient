package de.saginfo.mazehunter.ui.enterIpScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import static com.badlogic.gdx.graphics.GL20.GL_NEAREST;
import static com.badlogic.gdx.graphics.GL20.GL_TEXTURE_2D;
import static com.badlogic.gdx.graphics.GL20.GL_TEXTURE_MAG_FILTER;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.saginfo.mazehunter.LabyRindMain;
import de.saginfo.mazehunter.ui.connectScreen.ConnectScreen;

/**
 *
 * @author sreis
 */
public class EnterIpScreen extends ScreenAdapter{

    private Stage stage;
    private Viewport viewport;
    
    private MenuButton connectButton;
    private EnterIpField field;
    
    @Override
    public void show() {
        super.show();
        
        createUI();
        
        connectButton.addListener(new ClickListener(){
        @Override public void clicked(final InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            connect();
        }});
    }
    
    
    private void createUI(){
        this.viewport = new FillViewport(1920, 1080);
        
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        
        field = new EnterIpField();
        root.add(field).center().width(700).height(100).padBottom(60);
        
        root.row();
        
        connectButton = new MenuButton("Connect");
        root.add(connectButton).width(700).height(100);
    }
    
    private void connect(){
        LabyRindMain.GAME_SINGLETON.setScreen(new ConnectScreen(field.getText()));
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.clear();
        stage.dispose();
    }

    @Override
    public void hide() {
        super.hide();
        dispose();
    }
    
    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        Gdx.gl.glClearColor(1, 1, 1, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT| GL20.GL_DEPTH_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height, true);
    }
}
