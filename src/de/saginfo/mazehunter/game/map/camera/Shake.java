/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saginfo.mazehunter.game.map.camera;

import java.util.Random;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

public class Shake {
   float[] samples;
   Random rand = new Random();
   float internalTimer = 0;
   float shakeDuration = 0;
   
   int duration = 5; // In seconds, make longer if you want more variation
   int frequency = 20; // hertz
   float amplitude = 30; // how much you want to shake
   boolean falloff = true; // if the shake should decay as it expires
   
   int sampleCount;
   
   public Shake(){
      sampleCount = duration * frequency;
      samples = new float[sampleCount];
      for (int i =0; i < sampleCount; i++){
         samples[i] = rand.nextFloat() * 2f - 1f;
      }
   }
   
   /**
    * Called every frame will shake the camera if it has a shake duration
    * @param dt Gdx.graphics.getDeltaTime() or your dt in seconds
    * @param camera your camera
    * @param center Where the camera should stay centered on
    */
   public void update(float dt, Camera camera, Vector2 center){
      internalTimer += dt;
      if (internalTimer > duration) internalTimer -= duration;
      if (shakeDuration > 0){
         shakeDuration -= dt;
         float shakeTime = (internalTimer * frequency);
         int first = (int)shakeTime;
         int second = (first + 1)%sampleCount;
         int third = (first + 2)%sampleCount;
         float deltaT = shakeTime - (int)shakeTime;
         float deltaX = samples[first] * deltaT + samples[second] * ( 1f - deltaT);
         float deltaY = samples[second] * deltaT + samples[third] * ( 1f - deltaT);
         
         camera.position.x = center.x + deltaX * amplitude * (falloff ? Math.min(shakeDuration, 1f) : 1f);
         camera.position.y = center.y + deltaY * amplitude * (falloff ? Math.min(shakeDuration, 1f) : 1f);
         camera.update();
      }
   }
   
   /**
    * Will make the camera shake for the duration passed in in seconds
    * @param d duration of the shake in seconds
    */
   public void shake(float d){
      shakeDuration = d;
   }
}