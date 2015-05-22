package com.johnathongoss.libgdxtests.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.johnathongoss.libgdxtests.AppData.Prefs;
import com.johnathongoss.libgdxtests.Assets;
import com.johnathongoss.libgdxtests.ImageCache;
import com.johnathongoss.libgdxtests.MyGame;
import com.johnathongoss.libgdxtests.ParticleCache;
import com.johnathongoss.libgdxtests.Sounds;

public class Loading
  implements Screen
{
  private SpriteBatch batch;
  private OrthographicCamera cam;
  private MyGame game;
  
  public Loading(MyGame paramMyGame)
  {
    this.game = paramMyGame;
    this.cam = new OrthographicCamera();
    this.batch = new SpriteBatch();
    this.batch.setProjectionMatrix(this.cam.combined);
    Assets.Load();
    ImageCache.load();
    ParticleCache.Load();
    AppData.Prefs.Load();
    Sounds.Load();
  }
  
  public void dispose()
  {
    this.batch.dispose();
  }
  
  public void hide()
  {
    dispose();
  }
  
  public void pause() {}
  
  public void render(float paramFloat)
  {
    Gdx.gl.glClearColor(AppData.Prefs.getBackgroundR(), AppData.Prefs.getBackgroundG(), AppData.Prefs.getBackgroundB(), 1.0F);
    Gdx.gl.glClear(16384);
    this.batch.begin();
    Assets.font32.draw(this.batch, "LOADING...", 64.0F, 64.0F);
    this.batch.end();
    this.game.setScreen(new MainMenu(this.game));
  }
  
  public void resize(int paramInt1, int paramInt2) {}
  
  public void resume() {}
  
  public void show() {}
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\screens\Loading.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */