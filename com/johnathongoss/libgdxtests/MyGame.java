package com.johnathongoss.libgdxtests;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.johnathongoss.libgdxtests.screens.Loading;

public class MyGame
  extends Game
{
  public CameraShaker CameraShaker = new CameraShaker();
  private IActivityRequestHandler myRequestHandler;
  
  public MyGame(IActivityRequestHandler paramIActivityRequestHandler)
  {
    this.myRequestHandler = paramIActivityRequestHandler;
  }
  
  public void create()
  {
    setScreen(new Loading(this));
  }
  
  public float getButtonHeight()
  {
    return getHeight() / 8.0F;
  }
  
  public float getButtonWidth()
  {
    return getWidth() / 7.0F;
  }
  
  public float getHeight()
  {
    return Gdx.app.getGraphics().getHeight();
  }
  
  public float getPixelNo()
  {
    return getWidth() * getHeight();
  }
  
  public float getWidth()
  {
    return Gdx.app.getGraphics().getWidth();
  }
  
  public void showAds(boolean paramBoolean)
  {
    if (AppData.DISABLE_ADS) {
      paramBoolean = false;
    }
    this.myRequestHandler.showAds(paramBoolean);
  }
  
  public static class CameraShaker
  {
    OrthographicCamera cam;
    float currentTime = 0.0F;
    float duration;
    float intensity;
    private boolean isRunning = false;
    private boolean isShaking = false;
    float start_x;
    float start_y;
    
    public void Shake(OrthographicCamera paramOrthographicCamera, float paramFloat1, float paramFloat2)
    {
      this.cam = paramOrthographicCamera;
      this.duration = paramFloat1;
      this.intensity = paramFloat2;
      this.currentTime = 0.0F;
      this.start_x = this.cam.position.x;
      this.start_y = this.cam.position.y;
      this.isShaking = true;
      this.isRunning = true;
    }
    
    public void update(float paramFloat)
    {
      if (this.isRunning)
      {
        this.currentTime += paramFloat;
        if (this.currentTime > this.duration) {
          this.isShaking = false;
        }
        if (this.isShaking)
        {
          Vector3 localVector3 = this.cam.position;
          localVector3.x += MathUtils.random(-this.intensity * this.cam.viewportWidth / 800.0F, this.intensity * this.cam.viewportWidth / 800.0F);
          localVector3 = this.cam.position;
          localVector3.y += MathUtils.random(-this.intensity * this.cam.viewportHeight / 800.0F, this.intensity * this.cam.viewportHeight / 800.0F);
        }
      }
      else
      {
        return;
      }
      this.cam.position.x = this.start_x;
      this.cam.position.y = this.start_y;
      this.isRunning = false;
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\MyGame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */