package com.johnathongoss.libgdxtests.tests;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.johnathongoss.libgdxtests.Assets;
import com.johnathongoss.libgdxtests.ImageCache;
import com.johnathongoss.libgdxtests.MyGame;
import com.johnathongoss.libgdxtests.entities.SpeechBubble;
import com.johnathongoss.libgdxtests.screens.BlankScreen;
import com.johnathongoss.libgdxtests.screens.MainMenu;
import java.util.Iterator;

public class TooltipTest
  extends BlankScreen
  implements InputProcessor
{
  private float BUTTON_HEIGHT = this.height / 8.0F;
  private float BUTTON_WIDTH = this.width / 7.0F;
  private float height = Gdx.app.getGraphics().getHeight();
  private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
  private final Pool<SpeechBubble> speechBubblePool = new Pool()
  {
    protected SpeechBubble newObject()
    {
      return new SpeechBubble();
    }
  };
  private String testName = "Tooltip Test |";
  private String[] texts = { "Did you see that ludicrous display last night?", "What did the apple say to the tree?", "Don't do that!", "Watch it!", "Take your time then.", "Ah!", "Ouch!", "This piece of String is needlessly long! It's ridiculous!", "Stop touching that!", "", "You should check it out.", "...", "You'll break the screen doing that.", "A wild speech bubble appears!", "Leeeeeeeeeeeeeeee eeeennnnnnnnnn nnngggggggggg ttttthhhhhhhhh hhhhhh Teeeeeess sssssssttt ttt!" };
  private boolean usePics = false;
  private float width = Gdx.app.getGraphics().getWidth();
  
  public TooltipTest(MyGame paramMyGame)
  {
    super(paramMyGame);
  }
  
  private void checkSpeechBubbles()
  {
    Iterator localIterator = this.stage.getActors().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      Actor localActor = (Actor)localIterator.next();
      if (((localActor instanceof SpeechBubble)) && (!((SpeechBubble)localActor).isAlive()))
      {
        this.stage.getActors().removeValue(localActor, true);
        this.speechBubblePool.free((SpeechBubble)localActor);
      }
    }
  }
  
  public void dispose()
  {
    super.dispose();
    this.skin.dispose();
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
    checkSpeechBubbles();
    this.stage.draw();
    this.stageui.act(paramFloat);
    this.stageui.draw();
    this.batchui.begin();
    Assets.font24.drawMultiLine(this.batchui, "Free: " + this.speechBubblePool.getFree() + " |", 0.0F, 48.0F, this.width, BitmapFont.HAlignment.RIGHT);
    Assets.font24.drawMultiLine(this.batchui, this.testName, 0.0F, 24.0F, this.width, BitmapFont.HAlignment.RIGHT);
    Assets.font24.setColor(1.0F, 1.0F, 1.0F, 0.8F);
    Assets.font24.drawMultiLine(this.batchui, "FPS: " + Gdx.app.getGraphics().getFramesPerSecond(), 0.0F, 24.0F, Gdx.app.getGraphics().getWidth(), BitmapFont.HAlignment.LEFT);
    Assets.font24.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    this.batchui.end();
  }
  
  public boolean scrolled(int paramInt)
  {
    return false;
  }
  
  public void show()
  {
    this.game.showAds(false);
    final Object localObject = new InputMultiplexer(new InputProcessor[] { this.stageui, this.stage, this });
    Gdx.input.setInputProcessor((InputProcessor)localObject);
    Gdx.input.setCatchBackKey(true);
    localObject = new TextButton("Back", this.skin);
    ((TextButton)localObject).setBounds(0.0F, this.height - this.BUTTON_HEIGHT, this.BUTTON_WIDTH, this.BUTTON_HEIGHT);
    ((TextButton)localObject).addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        TooltipTest.this.game.setScreen(new MainMenu(TooltipTest.this.game));
      }
    });
    this.stageui.addActor((Actor)localObject);
    localObject = new TextButton("Pics: " + this.usePics, this.skin);
    ((TextButton)localObject).setBounds(this.width - this.BUTTON_WIDTH, this.height - this.BUTTON_HEIGHT, this.BUTTON_WIDTH, this.BUTTON_HEIGHT);
    ((TextButton)localObject).addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        paramAnonymousInputEvent = TooltipTest.this;
        if (TooltipTest.this.usePics) {}
        for (boolean bool = false;; bool = true)
        {
          paramAnonymousInputEvent.usePics = bool;
          localObject.setText("Pics: " + TooltipTest.this.usePics);
          return;
        }
      }
    });
    this.stageui.addActor((Actor)localObject);
  }
  
  public boolean touchDown(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    SpeechBubble localSpeechBubble = (SpeechBubble)this.speechBubblePool.obtain();
    if (this.usePics) {
      localSpeechBubble.init(this.texts[MathUtils.random(0, this.texts.length - 1)], paramInt1, this.height + -paramInt2, ImageCache.getTexture("background"));
    }
    for (;;)
    {
      localSpeechBubble.setColor(new Color(MathUtils.random(0.0F, 1.0F), MathUtils.random(0.0F, 1.0F), MathUtils.random(0.0F, 1.0F), MathUtils.random(1.0F, 1.0F)));
      this.stage.addActor(localSpeechBubble);
      return false;
      localSpeechBubble.init(this.texts[MathUtils.random(0, this.texts.length - 1)], paramInt1, this.height + -paramInt2);
    }
  }
  
  public boolean touchDragged(int paramInt1, int paramInt2, int paramInt3)
  {
    return false;
  }
  
  public boolean touchUp(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return false;
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\tests\TooltipTest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */