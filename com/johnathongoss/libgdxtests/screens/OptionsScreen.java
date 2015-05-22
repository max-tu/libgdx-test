package com.johnathongoss.libgdxtests.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.johnathongoss.libgdxtests.AppData.Prefs;
import com.johnathongoss.libgdxtests.Assets;
import com.johnathongoss.libgdxtests.MyGame;
import com.johnathongoss.libgdxtests.MyInputProcessor;

public class OptionsScreen
  implements Screen
{
  float b;
  float g;
  private MyGame game;
  MyInputProcessor input = new MyInputProcessor()
  {
    public boolean keyUp(int paramAnonymousInt)
    {
      if ((paramAnonymousInt == 4) || (paramAnonymousInt == 67) || (paramAnonymousInt == 131)) {
        OptionsScreen.this.game.setScreen(new MainMenu(OptionsScreen.this.game));
      }
      return false;
    }
  };
  float r;
  private Stage stageui;
  
  public OptionsScreen(MyGame paramMyGame)
  {
    this.game = paramMyGame;
  }
  
  public void dispose() {}
  
  public void hide()
  {
    dispose();
  }
  
  public void pause() {}
  
  public void render(float paramFloat)
  {
    Gdx.gl.glClear(16384);
    this.stageui.act(paramFloat);
    this.stageui.draw();
  }
  
  public void resize(int paramInt1, int paramInt2) {}
  
  public void resume() {}
  
  public void show()
  {
    this.game.showAds(true);
    this.stageui = new Stage();
    Object localObject = new InputMultiplexer(new InputProcessor[] { this.stageui, this.input });
    Gdx.input.setInputProcessor((InputProcessor)localObject);
    Gdx.input.setCatchBackKey(true);
    localObject = new TextButton("Save", Assets.skin);
    ((TextButton)localObject).setWidth(Gdx.app.getGraphics().getWidth() / 7);
    ((TextButton)localObject).setHeight(Gdx.app.getGraphics().getHeight() / 8);
    ((TextButton)localObject).addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        AppData.Prefs.Save();
        OptionsScreen.this.game.setScreen(new MainMenu(OptionsScreen.this.game));
      }
    });
    ((TextButton)localObject).setPosition(this.game.getWidth() - this.game.getButtonWidth(), 0.0F);
    this.stageui.addActor((Actor)localObject);
    final CheckBox localCheckBox = new CheckBox(" Limit Particle Effects", Assets.skin);
    localCheckBox.setChecked(AppData.Prefs.isLimitParticles());
    localCheckBox.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        AppData.Prefs.setLimitParticles(localCheckBox.isChecked());
      }
    });
    localCheckBox.setPosition(20.0F, Gdx.app.getGraphics().getHeight() - localCheckBox.getHeight() - ((TextButton)localObject).getHeight() * 2.0F);
    this.stageui.addActor(localCheckBox);
    localCheckBox = new CheckBox(" Enable sound", Assets.skin);
    localCheckBox.setChecked(AppData.Prefs.isEnableSound());
    localCheckBox.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        AppData.Prefs.setEnableSound(localCheckBox.isChecked());
      }
    });
    localCheckBox.setPosition(20.0F, Gdx.app.getGraphics().getHeight() - localCheckBox.getHeight() * 3.0F - ((TextButton)localObject).getHeight() * 2.0F);
    this.stageui.addActor(localCheckBox);
    this.r = AppData.Prefs.getBackgroundR();
    this.g = AppData.Prefs.getBackgroundG();
    this.b = AppData.Prefs.getBackgroundB();
    localObject = new TextButton("Background", Assets.skin);
    ((TextButton)localObject).setWidth(Gdx.app.getGraphics().getWidth() / 7);
    ((TextButton)localObject).setHeight(Gdx.app.getGraphics().getHeight() / 8);
    ((TextButton)localObject).addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        paramAnonymousInputEvent = OptionsScreen.this;
        paramAnonymousInputEvent.r += 0.1F;
        if (OptionsScreen.this.r > 0.3F) {
          OptionsScreen.this.r = 0.0F;
        }
        paramAnonymousInputEvent = OptionsScreen.this;
        paramAnonymousInputEvent.g += 0.1F;
        if (OptionsScreen.this.g > 0.3F) {
          OptionsScreen.this.g = 0.0F;
        }
        paramAnonymousInputEvent = OptionsScreen.this;
        paramAnonymousInputEvent.b += 0.1F;
        if (OptionsScreen.this.b > 0.3F) {
          OptionsScreen.this.b = 0.0F;
        }
        AppData.Prefs.setBackgroundR(OptionsScreen.this.r);
        AppData.Prefs.setBackgroundG(OptionsScreen.this.g);
        AppData.Prefs.setBackgroundB(OptionsScreen.this.b);
        Gdx.gl.glClearColor(OptionsScreen.this.r, OptionsScreen.this.g, OptionsScreen.this.b, 1.0F);
      }
    });
    ((TextButton)localObject).setPosition(20.0F, localCheckBox.getY() - ((TextButton)localObject).getHeight() - localCheckBox.getHeight());
    this.stageui.addActor((Actor)localObject);
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\screens\OptionsScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */