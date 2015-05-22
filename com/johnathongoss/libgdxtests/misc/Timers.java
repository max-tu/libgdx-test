package com.johnathongoss.libgdxtests.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.johnathongoss.libgdxtests.Assets;
import com.johnathongoss.libgdxtests.MyGame;
import com.johnathongoss.libgdxtests.entities.MyTimer;
import com.johnathongoss.libgdxtests.screens.Misc;
import com.johnathongoss.libgdxtests.tests.BlankTestScreen;
import java.util.Iterator;

public class Timers
  extends BlankTestScreen
{
  int count = 0;
  MyTimer timer = new MyTimer(3.0F)
  {
    protected void perform()
    {
      Timers localTimers = Timers.this;
      localTimers.count += 1;
    }
  };
  
  public Timers(MyGame paramMyGame)
  {
    super(paramMyGame);
    this.testName = "Timers Test |";
  }
  
  public boolean keyDown(int paramInt)
  {
    return false;
  }
  
  public boolean keyTyped(char paramChar)
  {
    return false;
  }
  
  public boolean keyUp(int paramInt)
  {
    if (paramInt == 4) {
      this.game.setScreen(new Misc(this.game));
    }
    return false;
  }
  
  public boolean mouseMoved(int paramInt1, int paramInt2)
  {
    return false;
  }
  
  public void render(float paramFloat)
  {
    Gdx.gl.glClear(16384);
    this.stage.act();
    this.stage.draw();
    this.stageui.act();
    this.stageui.draw();
    this.batchui.begin();
    renderText();
    renderTestName(this.batchui);
    this.batchui.end();
    updateText();
    this.timer.update(paramFloat);
  }
  
  protected void renderText()
  {
    int i = 0;
    for (;;)
    {
      if (i >= this.Text.size) {
        return;
      }
      Assets.font32.drawMultiLine(this.batchui, (CharSequence)this.Text.get(i), this.width / 2.0F, this.height / 2.0F - i * 24, 0.0F, BitmapFont.HAlignment.CENTER);
      i += 1;
    }
  }
  
  public boolean scrolled(int paramInt)
  {
    return false;
  }
  
  public void show()
  {
    addBackButton();
    Object localObject = new InputMultiplexer(new InputProcessor[] { this.stageui, this.stage, this });
    Gdx.input.setInputProcessor((InputProcessor)localObject);
    Gdx.input.setCatchBackKey(true);
    this.debugButton = new TextButton("Start", this.skin);
    this.debugButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        Timers.this.timer.start();
      }
    });
    this.buttons.add(this.debugButton);
    this.debugButton = new TextButton("Pause", this.skin);
    this.debugButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        Timers.this.timer.pause();
      }
    });
    this.buttons.add(this.debugButton);
    this.debugButton = new TextButton("Repeat", this.skin);
    this.debugButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        paramAnonymousInputEvent = Timers.this.timer;
        if (Timers.this.timer.isRepeating()) {}
        for (boolean bool = false;; bool = true)
        {
          paramAnonymousInputEvent.setRepeating(bool);
          return;
        }
      }
    });
    this.buttons.add(this.debugButton);
    this.debugButton = new TextButton("Reset", this.skin);
    this.debugButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        Timers.this.timer.reset();
        Timers.this.timer.pause();
        Timers.this.count = 0;
      }
    });
    this.buttons.add(this.debugButton);
    localObject = this.buttons.iterator();
    for (;;)
    {
      if (!((Iterator)localObject).hasNext()) {
        return;
      }
      TextButton localTextButton = (TextButton)((Iterator)localObject).next();
      this.stageui.addActor(localTextButton);
    }
  }
  
  public boolean touchDown(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return false;
  }
  
  public boolean touchDragged(int paramInt1, int paramInt2, int paramInt3)
  {
    return false;
  }
  
  public boolean touchUp(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return false;
  }
  
  protected void updateText()
  {
    this.Text.clear();
    this.Text.add("Times Completed: " + this.count);
    this.Text.add("Complete: " + this.timer.isComplete());
    this.Text.add((int)this.timer.getCurrent() + "/" + (int)this.timer.getCap());
    this.Text.add("Repeat: " + this.timer.isRepeating());
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\misc\Timers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */