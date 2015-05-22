package com.johnathongoss.libgdxtests.tests;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.johnathongoss.libgdxtests.Assets;
import com.johnathongoss.libgdxtests.ImageCache;
import com.johnathongoss.libgdxtests.MyGame;
import com.johnathongoss.libgdxtests.MyInputProcessor;
import com.johnathongoss.libgdxtests.screens.BlankScreen.CameraController;
import com.johnathongoss.libgdxtests.screens.MainMenu;
import java.util.Iterator;

public class Camera2D
  extends BlankTestScreen
{
  protected TextureRegion background = ImageCache.getTexture("background");
  protected float camX;
  protected float camY;
  protected float camZ;
  protected float camZoom;
  MyInputProcessor input = new MyInputProcessor()
  {
    public boolean keyUp(int paramAnonymousInt)
    {
      if ((paramAnonymousInt == 4) || (paramAnonymousInt == 67) || (paramAnonymousInt == 131)) {
        Camera2D.this.game.setScreen(new MainMenu(Camera2D.this.game));
      }
      return false;
    }
  };
  protected Sprite sprite = new Sprite(this.background);
  
  public Camera2D(MyGame paramMyGame)
  {
    super(paramMyGame);
    this.sprite.setPosition(0.0F, 0.0F);
    this.sprite.setOrigin(0.0F, 0.0F);
    this.sprite.setScale(Gdx.app.getGraphics().getWidth() / this.background.getRegionWidth(), Gdx.app.getGraphics().getHeight() / this.background.getRegionHeight());
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
    this.batch.begin();
    this.sprite.draw(this.batch);
    this.batch.end();
    this.stage.act(paramFloat);
    this.stage.draw();
    updateText();
    renderText();
    this.stageui.act();
    this.stageui.draw();
    this.controller.update();
    this.cam.update();
    this.batch.setProjectionMatrix(this.cam.combined);
  }
  
  protected void renderText()
  {
    this.batchui.begin();
    Assets.font24.setColor(1.0F, 1.0F, 1.0F, 0.8F);
    int i = 0;
    for (;;)
    {
      if (i >= this.Text.size)
      {
        renderTestName(this.batchui);
        this.batchui.end();
        return;
      }
      Assets.font24.drawMultiLine(this.batchui, (CharSequence)this.Text.get(i), 0.0F, this.height - this.BUTTON_HEIGHT * 2.0F - 24.0F - i * 24, this.width, BitmapFont.HAlignment.RIGHT);
      i += 1;
    }
  }
  
  public boolean scrolled(int paramInt)
  {
    return false;
  }
  
  public void show()
  {
    this.game.showAds(true);
    this.testName = "Camera 2D Test |";
    addCameraControl(this.width, 0.0F, this.height, 0.0F);
    addBackButton();
    this.controller = new BlankScreen.CameraController(this);
    this.controller.setBounds(this.width, 0.0F, this.height, 0.0F);
    this.gestureDetector = new GestureDetector(20.0F, 0.5F, 2.0F, 0.15F, this.controller);
    Object localObject = new InputMultiplexer(new InputProcessor[] { this.stageui, this.stage, this.gestureDetector, this, this.input });
    Gdx.input.setInputProcessor((InputProcessor)localObject);
    Gdx.input.setCatchBackKey(true);
    this.cam.zoom = 1.4F;
    this.camX = this.cam.position.x;
    this.camY = this.cam.position.y;
    this.camZ = this.cam.position.z;
    this.camZoom = this.cam.zoom;
    this.debugButton = new TextButton("Reset Camera", this.skin);
    this.debugButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        Camera2D.this.cam.position.set(Camera2D.this.camX, Camera2D.this.camY, Camera2D.this.camZ);
        Camera2D.this.cam.zoom = Camera2D.this.camZoom;
        Camera2D.this.controller.velX = 0.0F;
        Camera2D.this.controller.velY = 0.0F;
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
    this.Text.add("x: " + (int)this.cam.position.x + " y: " + (int)this.cam.position.y + " |");
    this.Text.add("Zoom: " + this.cam.zoom + " |");
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\tests\Camera2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */