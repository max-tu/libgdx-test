package com.johnathongoss.libgdxtests.tests;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.johnathongoss.libgdxtests.Assets;
import com.johnathongoss.libgdxtests.MyGame;
import com.johnathongoss.libgdxtests.screens.BlankScreen;
import com.johnathongoss.libgdxtests.screens.MainMenu;

public abstract class BlankTestScreen
  extends BlankScreen
{
  protected float BUTTON_HEIGHT = Gdx.app.getGraphics().getHeight() / 8;
  protected float BUTTON_WIDTH = Gdx.app.getGraphics().getWidth() / 7;
  protected Array<String> Text = new Array();
  protected TextButton backButton;
  protected Array<TextButton> buttons = new Array();
  protected TextButton debugButton;
  protected boolean showBackButton = false;
  protected Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
  protected String testName = "";
  
  public BlankTestScreen(MyGame paramMyGame)
  {
    super(paramMyGame);
  }
  
  protected void addBackButton()
  {
    this.showBackButton = true;
    if (this.showBackButton)
    {
      this.backButton = new TextButton("Back", this.skin);
      this.backButton.setHeight(this.BUTTON_HEIGHT);
      this.backButton.setWidth(this.BUTTON_WIDTH);
      this.backButton.setPosition(0.0F, this.height - this.BUTTON_HEIGHT * 2.0F);
      this.backButton.addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          BlankTestScreen.this.game.setScreen(new MainMenu(BlankTestScreen.this.game));
        }
      });
      this.stageui.addActor(this.backButton);
    }
  }
  
  public abstract void render(float paramFloat);
  
  protected void renderTestName(SpriteBatch paramSpriteBatch)
  {
    Assets.font24.setColor(1.0F, 1.0F, 1.0F, 0.8F);
    Assets.font24.drawMultiLine(paramSpriteBatch, "FPS: " + Gdx.app.getGraphics().getFramesPerSecond(), 0.0F, 24.0F, this.width, BitmapFont.HAlignment.LEFT);
    Assets.font24.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    Assets.font24.drawMultiLine(paramSpriteBatch, this.testName, 0.0F, 24.0F, this.width, BitmapFont.HAlignment.RIGHT);
  }
  
  protected abstract void renderText();
  
  public void resize(int paramInt1, int paramInt2)
  {
    super.resize(paramInt1, paramInt2);
    this.BUTTON_WIDTH = (Gdx.app.getGraphics().getWidth() / 7);
    this.BUTTON_HEIGHT = (Gdx.app.getGraphics().getHeight() / 8);
    int i = 0;
    int k = 0;
    float f1 = paramInt1;
    float f2 = this.BUTTON_WIDTH;
    float f3 = paramInt2;
    float f4 = this.BUTTON_HEIGHT;
    int j = 0;
    paramInt1 = i;
    for (;;)
    {
      if (j >= this.buttons.size)
      {
        if (this.showBackButton) {
          this.backButton.setPosition(0.0F, paramInt2 - this.backButton.getHeight() * 2.0F);
        }
        return;
      }
      ((TextButton)this.buttons.get(j)).setSize(this.BUTTON_WIDTH, this.BUTTON_HEIGHT);
      ((TextButton)this.buttons.get(j)).setPosition(f1 - f2 - this.BUTTON_WIDTH * k, f3 - f4 * 2.0F - this.BUTTON_HEIGHT * paramInt1);
      int m = paramInt1 + 1;
      i = k;
      paramInt1 = m;
      if (m == 3)
      {
        k += 1;
        paramInt1 = 0;
        i = k;
        if (k == 2)
        {
          i = 0;
          paramInt1 = 3;
        }
      }
      j += 1;
      k = i;
    }
  }
  
  public abstract void show();
  
  protected abstract void updateText();
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\tests\BlankTestScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */