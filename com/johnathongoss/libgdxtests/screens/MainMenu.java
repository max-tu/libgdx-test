package com.johnathongoss.libgdxtests.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.johnathongoss.libgdxtests.tests.AnimationTest;
import com.johnathongoss.libgdxtests.tests.BlankTestScreen;
import com.johnathongoss.libgdxtests.tests.Box2D;
import com.johnathongoss.libgdxtests.tests.Camera2D;
import com.johnathongoss.libgdxtests.tests.Collision;
import com.johnathongoss.libgdxtests.tests.HexGridTest;
import com.johnathongoss.libgdxtests.tests.LightTest;
import com.johnathongoss.libgdxtests.tests.Particles;
import com.johnathongoss.libgdxtests.tests.SpeechTest;
import com.johnathongoss.libgdxtests.tests.TooltipTest;
import java.util.Iterator;

public class MainMenu
  extends BlankTestScreen
{
  private Array<TextButton> buttons;
  TextButton exampleButton;
  TextButton miscButton;
  
  public MainMenu(MyGame paramMyGame)
  {
    super(paramMyGame);
    this.Text.add("libGDX Tests |");
    this.Text.add("0.6.4.3 |");
    this.Text.add("johnathongoss.com |");
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
    return false;
  }
  
  public boolean mouseMoved(int paramInt1, int paramInt2)
  {
    return false;
  }
  
  public void render(float paramFloat)
  {
    Gdx.gl.glClear(16384);
    this.stage.act(paramFloat);
    this.stage.draw();
    this.stageui.act(paramFloat);
    this.stageui.draw();
    renderText();
  }
  
  protected void renderText()
  {
    this.batchui.begin();
    int i = 0;
    for (;;)
    {
      if (i >= this.Text.size)
      {
        this.batchui.end();
        return;
      }
      Assets.font24.drawMultiLine(this.batchui, (CharSequence)this.Text.get(i), -this.BUTTON_WIDTH, this.Text.size * 24 - i * 24, this.width, BitmapFont.HAlignment.RIGHT);
      i += 1;
    }
  }
  
  public void resize(int paramInt1, int paramInt2)
  {
    super.resize(paramInt1, paramInt2);
    this.exampleButton.setWidth(this.BUTTON_WIDTH);
    this.exampleButton.setHeight(this.BUTTON_HEIGHT);
    int i = 0;
    float f1 = paramInt2 - this.BUTTON_HEIGHT * 2.0F;
    float f2 = 0.0F;
    paramInt1 = 0;
    for (;;)
    {
      if (paramInt1 >= this.buttons.size) {
        return;
      }
      ((TextButton)this.buttons.get(paramInt1)).setPosition(f2, f1);
      float f4 = f1 - this.BUTTON_HEIGHT;
      int j = i;
      float f3 = f2;
      f1 = f4;
      if (paramInt1 % 6 == 0)
      {
        j = i;
        f3 = f2;
        f1 = f4;
        if (paramInt1 != 0)
        {
          f1 = paramInt2 - this.BUTTON_HEIGHT * 2.0F;
          j = i + 1;
          f3 = this.BUTTON_WIDTH * j;
        }
      }
      paramInt1 += 1;
      i = j;
      f2 = f3;
    }
  }
  
  public boolean scrolled(int paramInt)
  {
    return false;
  }
  
  public void show()
  {
    this.game.showAds(true);
    Gdx.input.setInputProcessor(this.stageui);
    Gdx.input.setCatchBackKey(false);
    this.buttons = new Array();
    this.exampleButton = new TextButton("Examples", this.skin);
    this.exampleButton.setHeight(this.BUTTON_HEIGHT);
    this.exampleButton.setWidth(this.BUTTON_WIDTH);
    this.exampleButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        MainMenu.this.game.setScreen(new Examples(MainMenu.this.game));
      }
    });
    this.exampleButton.setPosition(this.width - this.BUTTON_WIDTH, this.height - this.BUTTON_HEIGHT * 2.0F);
    this.stageui.addActor(this.exampleButton);
    this.miscButton = new TextButton("Misc", this.skin);
    this.miscButton.setHeight(this.BUTTON_HEIGHT);
    this.miscButton.setWidth(this.BUTTON_WIDTH);
    this.miscButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        MainMenu.this.game.setScreen(new Misc(MainMenu.this.game));
      }
    });
    this.miscButton.setPosition(this.width - this.BUTTON_WIDTH, this.height - this.BUTTON_HEIGHT * 3.0F);
    this.stageui.addActor(this.miscButton);
    this.miscButton = new TextButton("Options", this.skin);
    this.miscButton.setHeight(this.BUTTON_HEIGHT);
    this.miscButton.setWidth(this.BUTTON_WIDTH);
    this.miscButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        MainMenu.this.game.setScreen(new OptionsScreen(MainMenu.this.game));
      }
    });
    this.miscButton.setPosition(this.width - this.BUTTON_WIDTH, 0.0F);
    this.stageui.addActor(this.miscButton);
    this.debugButton = new TextButton("Animation", this.skin);
    this.debugButton.setHeight(this.BUTTON_HEIGHT);
    this.debugButton.setWidth(this.BUTTON_WIDTH);
    this.debugButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        MainMenu.this.game.setScreen(new AnimationTest(MainMenu.this.game));
      }
    });
    this.buttons.add(this.debugButton);
    this.debugButton = new TextButton("Box2D", this.skin);
    this.debugButton.setHeight(this.BUTTON_HEIGHT);
    this.debugButton.setWidth(this.BUTTON_WIDTH);
    this.debugButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        MainMenu.this.game.setScreen(new Box2D(MainMenu.this.game));
      }
    });
    this.buttons.add(this.debugButton);
    this.debugButton = new TextButton("Camera 2D", this.skin);
    this.debugButton.setHeight(this.BUTTON_HEIGHT);
    this.debugButton.setWidth(this.BUTTON_WIDTH);
    this.debugButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        MainMenu.this.game.setScreen(new Camera2D(MainMenu.this.game));
      }
    });
    this.buttons.add(this.debugButton);
    this.debugButton = new TextButton("Collision", this.skin);
    this.debugButton.setHeight(this.BUTTON_HEIGHT);
    this.debugButton.setWidth(this.BUTTON_WIDTH);
    this.debugButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        MainMenu.this.game.setScreen(new Collision(MainMenu.this.game));
      }
    });
    this.buttons.add(this.debugButton);
    this.debugButton = new TextButton("Hex Grid", this.skin);
    this.debugButton.setHeight(this.BUTTON_HEIGHT);
    this.debugButton.setWidth(this.BUTTON_WIDTH);
    this.debugButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        MainMenu.this.game.setScreen(new HexGridTest(MainMenu.this.game));
      }
    });
    this.buttons.add(this.debugButton);
    this.debugButton = new TextButton("Light 1", this.skin);
    this.debugButton.setHeight(this.BUTTON_HEIGHT);
    this.debugButton.setWidth(this.BUTTON_WIDTH);
    this.debugButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        MainMenu.this.game.setScreen(new LightTest(MainMenu.this.game));
      }
    });
    this.buttons.add(this.debugButton);
    this.debugButton = new TextButton("Particles", this.skin);
    this.debugButton.setHeight(this.BUTTON_HEIGHT);
    this.debugButton.setWidth(this.BUTTON_WIDTH);
    this.debugButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        MainMenu.this.game.setScreen(new Particles(MainMenu.this.game));
      }
    });
    this.buttons.add(this.debugButton);
    this.debugButton = new TextButton("Speech", this.skin);
    this.debugButton.setHeight(this.BUTTON_HEIGHT);
    this.debugButton.setWidth(this.BUTTON_WIDTH);
    this.debugButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        MainMenu.this.game.setScreen(new SpeechTest(MainMenu.this.game));
      }
    });
    this.buttons.add(this.debugButton);
    this.debugButton = new TextButton("Tooltip", this.skin);
    this.debugButton.setHeight(this.BUTTON_HEIGHT);
    this.debugButton.setWidth(this.BUTTON_WIDTH);
    this.debugButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        MainMenu.this.game.setScreen(new TooltipTest(MainMenu.this.game));
      }
    });
    Iterator localIterator = this.buttons.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      TextButton localTextButton = (TextButton)localIterator.next();
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
  
  protected void updateText() {}
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\screens\MainMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */