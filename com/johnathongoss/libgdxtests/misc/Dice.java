package com.johnathongoss.libgdxtests.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.johnathongoss.libgdxtests.Assets;
import com.johnathongoss.libgdxtests.MyGame;
import com.johnathongoss.libgdxtests.MyInputProcessor;
import com.johnathongoss.libgdxtests.screens.MainMenu;
import com.johnathongoss.libgdxtests.screens.Misc;
import com.johnathongoss.libgdxtests.tests.BlankTestScreen;

public class Dice
  extends BlankTestScreen
{
  int diceLower = 1;
  int diceUpper = 6;
  MyInputProcessor input = new MyInputProcessor()
  {
    public boolean keyUp(int paramAnonymousInt)
    {
      if ((paramAnonymousInt == 4) || (paramAnonymousInt == 67) || (paramAnonymousInt == 131)) {
        Dice.this.game.setScreen(new MainMenu(Dice.this.game));
      }
      return false;
    }
  };
  int noDice = 1;
  String result = "";
  
  public Dice(MyGame paramMyGame)
  {
    super(paramMyGame);
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
    this.stage.act(paramFloat);
    this.stage.draw();
    this.stageui.act(paramFloat);
    this.stageui.draw();
    this.batch.begin();
    Assets.font32.drawMultiLine(this.batch, this.noDice + "d" + this.diceUpper + " |", 0.0F, 32.0F, this.width, BitmapFont.HAlignment.RIGHT);
    Assets.font32.drawMultiLine(this.batch, this.result, 0.0F, 64.0F, this.width, BitmapFont.HAlignment.CENTER);
    this.batch.end();
  }
  
  protected void renderText() {}
  
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
    this.backButton = new TextButton("Back", this.skin);
    this.backButton.setHeight(this.BUTTON_HEIGHT);
    this.backButton.setWidth(this.BUTTON_WIDTH);
    this.backButton.setPosition(0.0F, this.height - this.BUTTON_HEIGHT * 2.0F);
    this.backButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        Dice.this.game.setScreen(new Misc(Dice.this.game));
      }
    });
    this.stageui.addActor(this.backButton);
    localObject = new TextButton("[+] Die", this.skin);
    ((TextButton)localObject).setBounds(this.width - this.BUTTON_WIDTH, this.height - this.BUTTON_HEIGHT * 2.0F, this.BUTTON_WIDTH, this.BUTTON_HEIGHT);
    ((TextButton)localObject).addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        paramAnonymousInputEvent = Dice.this;
        paramAnonymousInputEvent.noDice += 1;
        if (Dice.this.noDice > 10) {
          Dice.this.noDice = 10;
        }
      }
    });
    this.stageui.addActor((Actor)localObject);
    localObject = new TextButton("[-] Die", this.skin);
    ((TextButton)localObject).setBounds(this.width - this.BUTTON_WIDTH * 2.0F, this.height - this.BUTTON_HEIGHT * 2.0F, this.BUTTON_WIDTH, this.BUTTON_HEIGHT);
    ((TextButton)localObject).addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        paramAnonymousInputEvent = Dice.this;
        paramAnonymousInputEvent.noDice -= 1;
        if (Dice.this.noDice == 0) {
          Dice.this.noDice = 1;
        }
      }
    });
    this.stageui.addActor((Actor)localObject);
    localObject = new TextButton("[+] Grade", this.skin);
    ((TextButton)localObject).setBounds(this.width - this.BUTTON_WIDTH, this.height - this.BUTTON_HEIGHT * 3.0F, this.BUTTON_WIDTH, this.BUTTON_HEIGHT);
    ((TextButton)localObject).addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        paramAnonymousInputEvent = Dice.this;
        paramAnonymousInputEvent.diceUpper += 1;
        if (Dice.this.diceUpper > 20) {
          Dice.this.diceUpper = 20;
        }
      }
    });
    this.stageui.addActor((Actor)localObject);
    localObject = new TextButton("[-] Grade", this.skin);
    ((TextButton)localObject).setBounds(this.width - this.BUTTON_WIDTH * 2.0F, this.height - this.BUTTON_HEIGHT * 3.0F, this.BUTTON_WIDTH, this.BUTTON_HEIGHT);
    ((TextButton)localObject).addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        paramAnonymousInputEvent = Dice.this;
        paramAnonymousInputEvent.diceUpper -= 1;
        if (Dice.this.diceUpper < 2) {
          Dice.this.diceUpper = 2;
        }
      }
    });
    this.stageui.addActor((Actor)localObject);
    localObject = new TextButton("Roll", this.skin);
    ((TextButton)localObject).setBounds(this.width - this.BUTTON_WIDTH, this.height - this.BUTTON_HEIGHT * 4.0F, this.BUTTON_WIDTH, this.BUTTON_HEIGHT);
    ((TextButton)localObject).addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        paramAnonymousInputEvent = new Array();
        Dice.this.result = "Result: ";
        int i = 0;
        int j;
        if (i >= Dice.this.noDice)
        {
          paramAnonymousInputEvent.sort();
          j = 0;
          i = 0;
        }
        for (;;)
        {
          if (i >= paramAnonymousInputEvent.size)
          {
            paramAnonymousInputEvent = Dice.this;
            paramAnonymousInputEvent.result = (paramAnonymousInputEvent.result + "\nSum: " + j);
            return;
            paramAnonymousInputEvent.add(Integer.valueOf(MathUtils.random(Dice.this.diceLower, Dice.this.diceUpper)));
            i += 1;
            break;
          }
          Dice localDice = Dice.this;
          localDice.result += paramAnonymousInputEvent.get(i);
          j += ((Integer)paramAnonymousInputEvent.get(i)).intValue();
          if (i < Dice.this.noDice - 1)
          {
            localDice = Dice.this;
            localDice.result += ", ";
          }
          i += 1;
        }
      }
    });
    this.stageui.addActor((Actor)localObject);
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


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\misc\Dice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */