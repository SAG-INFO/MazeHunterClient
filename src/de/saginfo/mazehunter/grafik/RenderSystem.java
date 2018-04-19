package de.saginfo.mazehunter.grafik;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.saginfo.mazehunter.game.GameScreen;
import static de.saginfo.mazehunter.game.GameScreen.GAMESCREEN_SINGLETON;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * <pre>
 * Hält eine Liste von {@link Visual}s und rendered diese automatisch.
 * 
 * Am einfachsten kann auf die Instanz dieser Klasse über
 * {@link GameScreen#GAMESCREEN_SINGLETON} zugegriffen werden.
 * 
 * Visuals werden nach zIndex sortiert
 * 
 * Beim schließen des Spieles werden die Ressourcen aller 
 * hinzugefügten Texturen freigegeben
 * </pre>
 * @author sreis
 */
public class RenderSystem {

    private final ArrayList<Visual> visuals;
    public final SpriteBatch batch;

    public RenderSystem() {
        visuals = new ArrayList<Visual>();
        batch = new SpriteBatch();
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(GAMESCREEN_SINGLETON.camera.combined);
        batch.begin();
        for (Visual visual : visuals) {
            visual.draw(batch, delta);
        }
        batch.end();
    }

    public void addSprite(Visual s) {
        visuals.add(s);
        visuals.sort(zComparator);
    }

    public void removeSprite(Sprite s) {
        visuals.remove(s);
    }

    public void dispose() {
        for (Visual visual : visuals) {
            visual.dispose();
        }
    }

    /**
     * Wird verwendet um {@link Visual}s in abhängigkeit von ihrem zIndex zu soriteren
     */
    private static final Comparator<Visual> zComparator = new Comparator<Visual>() {
        @Override
        public int compare(Visual visual1, Visual visual2) {
            if (visual1 == null) {
                return 1;
            } else if (visual2 == null) {
                return -1;
            } else if (visual1.getZIndex() > visual2.getZIndex()) {
                return 1;
            } else if (visual1.getZIndex() < visual2.getZIndex()) {
                return -1;
            } else {
                return 0;
            }
        }
    };
}
