/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.grafik;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import java.util.Arrays;

/**
 *
 * @author sreis
 */
public class AnimationVisual extends SpriteVisual {
    private final Animation<TextureRegion> animation;

    private float animation_time;
    private boolean isPlaying = true;

    public AnimationVisual(float fps, String filepath, Animation.PlayMode pm) {
        this(fps, createRegions(filepath), pm);
    }
    
    public static Array<TextureRegion> createRegions(String name){
        Array<Texture> texs = findTextures(name);
        Array<TextureRegion> res = new Array<>();
        
        for (Texture tex : texs) {
            res.add(new TextureRegion(tex));
        }
        
        return res;
    }
    
    public static Array<Texture> findTextures(String name){
        FileHandle f = Gdx.files.local(name);
        Array<Texture> result = new Array<>();
        
        for (FileHandle fileHandle : f.parent().list()) {
            if(!fileHandle.isDirectory() && fileHandle.file().getName().startsWith(f.nameWithoutExtension())){
                result.add(new Texture(fileHandle));
            }
        }
        
        return result;
    }
    
    public AnimationVisual(float fps, Array<? extends TextureRegion> regions, Animation.PlayMode playMode) {
        animation = new Animation(1f/fps, regions, playMode);
        setRegion(regions.first());
    }

    @Override
    public void draw(SpriteBatch batch, float delta) {
        if (super.getTexture() == null) {
            throw new RuntimeException("textureRegion is null. your fault? idk");
        }

        if (isPlaying) {
            animation_time += delta;
            update();
        }
        super.draw(batch, delta);
    }

    private void update() {
        super.setRegion(animation.getKeyFrame(animation_time));
        super.setSize(getRegionWidth(), getRegionHeight());
    }

    public void restart() {
        animation_time = 0;
    }

    public int getKeyFrameIndex() {
        return animation.getKeyFrameIndex(animation_time);
    }

    public boolean isPlaying() {
        return !isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = !playing;
    }

    public void setFps(float fps) {
        animation.setFrameDuration(1 / fps);
    }

    public float getFps() {
        return 1 / animation.getFrameDuration();
    }

    public void setFrameDuration(float frameDuration) {
        animation.setFrameDuration(frameDuration);
    }

    public float getFrameDuration() {

        return animation.getFrameDuration();
    }

    public Animation.PlayMode getPlayMode() {
        return animation.getPlayMode();
    }

    public void setPlayMode(Animation.PlayMode playMode) {
        animation.setPlayMode(playMode);
    }
}
