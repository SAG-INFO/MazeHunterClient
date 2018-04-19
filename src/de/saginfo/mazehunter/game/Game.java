package de.saginfo.mazehunter.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import static de.saginfo.mazehunter.game.GameScreen.GAMESCREEN_SINGLETON;
import de.saginfo.mazehunter.grafik.SpriteVisual;

/**
 * 
 */
public class Game {
    
    public Game() {
        //Test Graphics
        int max = 7*15;
        float scale = 6f/max;

        for (int x = 0; x < max; x++) {
            for (int y = 0; y < max; y++) {
                
                SpriteVisual test2 = new SpriteVisual(Math.random()<0.5f?"assets\\img\\map\\edge.png":"assets\\img\\map\\side.png");
                test2.setZIndex(x*y);
                test2.setScale(scale);
                test2.setPosition(x*test2.getWidth()*scale, y*test2.getHeight()*scale);
                GAMESCREEN_SINGLETON.renderSystem.addSprite(test2);
            }
        }
    }
    
    public void update(float delta){
        System.out.println(1/delta);
        
        float speed = 3;
        
        if(Gdx.input.isKeyPressed(Keys.W))
            GAMESCREEN_SINGLETON.camera.position.add(0, speed, 0);
        if(Gdx.input.isKeyPressed(Keys.A))
            GAMESCREEN_SINGLETON.camera.position.add(-speed, 0, 0);
        if(Gdx.input.isKeyPressed(Keys.S))
            GAMESCREEN_SINGLETON.camera.position.add(0, -speed, 0);
        if(Gdx.input.isKeyPressed(Keys.D))
            GAMESCREEN_SINGLETON.camera.position.add(speed, 0, 0);
    }
    
    /**called when the user closes the window*/
    public void close(){
        
    }
}
