package com.johnathongoss.libgdxtests.screens;

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
import com.johnathongoss.libgdxtests.MyInputProcessor;
import com.johnathongoss.libgdxtests.misc.Dice;
import com.johnathongoss.libgdxtests.tests.BlankTestScreen;
import java.util.Iterator;

public class Misc
  extends BlankTestScreen
{
  private Array<TextButton> buttons;
  TextButton exampleButton;
  MyInputProcessor input = new MyInputProcessor()
  {
    public boolean keyUp(int paramAnonymousInt)
    {
      if ((paramAnonymousInt == 4) || (paramAnonymousInt == 67) || (paramAnonymousInt == 131)) {
        Misc.this.game.setScreen(new MainMenu(Misc.this.game));
      }
      return false;
    }
  };
  
  public Misc(MyGame paramMyGame)
  {
    super(paramMyGame);
    this.Text.add("Misc |");
    this.Text.add("bits and bobs |");
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
      this.game.setScreen(new MainMenu(this.game));
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
      Assets.font24.drawMultiLine(this.batchui, (CharSequence)this.Text.get(i), 0.0F, this.Text.size * 24 - i * 24, this.width, BitmapFont.HAlignment.RIGHT);
      i += 1;
    }
  }
  
  public void resize(int paramInt1, int paramInt2)
  {
    super.resize(paramInt1, paramInt2);
    int i = 0;
    paramInt1 = 0;
    for (;;)
    {
      if (paramInt1 >= this.buttons.size) {
        return;
      }
      TextButton localTextButton = (TextButton)this.buttons.get(paramInt1);
      float f1 = ((TextButton)this.buttons.get(paramInt1)).getWidth();
      float f2 = i;
      float f3 = paramInt2;
      float f4 = paramInt1 + 2;
      localTextButton.setPosition(f2 * f1, f3 - ((TextButton)this.buttons.get(paramInt1)).getHeight() * f4);
      int j = i;
      if (paramInt1 == 8) {
        j = i + 1;
      }
      paramInt1 += 1;
      i = j;
    }
  }
  
  public boolean scrolled(int paramInt)
  {
    return false;
  }
  
  public void show()
  {
    this.game.showAds(true);
    Object localObject = new InputMultiplexer(new InputProcessor[] { this.stageui, this.stage, this.input });
    Gdx.input.setInputProcessor((InputProcessor)localObject);
    Gdx.input.setCatchBackKey(true);
    this.backButton = new TextButton("Main Menu", this.skin);
    this.backButton.setHeight(this.BUTTON_HEIGHT);
    this.backButton.setWidth(this.BUTTON_WIDTH);
    this.backButton.setPosition(this.width - this.BUTTON_WIDTH, this.height - this.BUTTON_HEIGHT * 2.0F);
    this.backButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        Misc.this.game.setScreen(new MainMenu(Misc.this.game));
      }
    });
    this.stageui.addActor(this.backButton);
    this.buttons = new Array();
    this.debugButton = new TextButton("Dice", this.skin);
    this.debugButton.setHeight(this.BUTTON_HEIGHT);
    this.debugButton.setWidth(this.BUTTON_WIDTH);
    this.debugButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        Misc.this.game.setScreen(new Dice(Misc.this.game));
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
      this.stage.addActor(localTextButton);
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


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\screens\Misc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */