package de.saginfo.mazehunter.game;

import com.badlogic.gdx.Gdx;
import de.saginfo.mazehunter.grafik.RenderSystem;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Manages the main game (calles update, close, etc, ...)
 * keeps track of some graphics related stuff:
 * {@link RenderSystem}, {@link OrthographicCamera}, {@link Viewport}, ...
 * @author sreis
 */
public class GameScreen extends ScreenAdapter {

    public Game game;
    public RenderSystem renderSystem;
    
    public OrthographicCamera camera;
    public Viewport viewport;

    /**kann verwendet werden um von beliebiger Stelle auf z.B. das RenderSystem zuzugreifen*/
    public static GameScreen GAMESCREEN_SINGLETON;

    @Override
    public void show() {
        GAMESCREEN_SINGLETON = this;
        renderSystem = new RenderSystem();
        
        camera = new OrthographicCamera();
        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

        viewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        viewport.apply();
        
        game = new Game();
    }

    @Override
    public void render(float delta) {
        game.update(delta);
        camera.update();
        renderSystem.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        renderSystem.dispose();
        game.close();
    }
    
    @Override
    public void hide(){
        dispose();
    }
}
