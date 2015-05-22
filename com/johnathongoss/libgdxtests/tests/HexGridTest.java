package com.johnathongoss.libgdxtests.tests;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.johnathongoss.libgdxtests.Assets;
import com.johnathongoss.libgdxtests.ImageCache;
import com.johnathongoss.libgdxtests.MyGame;
import com.johnathongoss.libgdxtests.MyInputProcessor;
import com.johnathongoss.libgdxtests.screens.MainMenu;
import java.lang.reflect.Array;

public class HexGridTest
  implements Screen
{
  SpriteBatch batch;
  OrthographicCamera cam;
  OrthographicCamera cam_ui;
  protected CameraController controller;
  protected boolean debug = false;
  MyGame game;
  protected GestureDetector gestureDetector;
  HexGrid hexGrid;
  MyInputProcessor input = new MyInputProcessor()
  {
    public boolean keyUp(int paramAnonymousInt)
    {
      if ((paramAnonymousInt == 4) || (paramAnonymousInt == 67) || (paramAnonymousInt == 131)) {
        HexGridTest.this.game.setScreen(new MainMenu(HexGridTest.this.game));
      }
      return false;
    }
  };
  Stage stageaction;
  Stage stagehexes;
  Stage stageui;
  
  public HexGridTest(MyGame paramMyGame)
  {
    this.game = paramMyGame;
    this.batch = new SpriteBatch();
    this.cam = new OrthographicCamera();
    this.cam_ui = new OrthographicCamera();
    this.hexGrid = new HexGrid(paramMyGame.getWidth(), paramMyGame.getHeight());
    this.stageui = new Stage(paramMyGame.getWidth(), paramMyGame.getHeight(), true);
    this.stageaction = new Stage(paramMyGame.getWidth(), paramMyGame.getHeight(), true);
    this.stagehexes = new Stage(paramMyGame.getWidth(), paramMyGame.getHeight(), true);
  }
  
  public void dispose()
  {
    this.batch.dispose();
    this.stageui.dispose();
    this.stageaction.dispose();
    this.stagehexes.dispose();
  }
  
  public void hide() {}
  
  public void pause() {}
  
  public void render(float paramFloat)
  {
    Gdx.gl.glClear(16384);
    this.controller.update();
    this.stagehexes.act(paramFloat);
    this.stagehexes.draw();
    this.stageaction.act(paramFloat);
    this.stageaction.draw();
    this.stageui.act(paramFloat);
    this.stageui.draw();
    this.cam.update();
    this.cam_ui.update();
    this.batch.setProjectionMatrix(this.cam_ui.combined);
    this.batch.begin();
    Assets.font24.drawMultiLine(this.batch, "Hex Grid Test |", 0.0F, Assets.font24.getLineHeight(), this.game.getWidth(), BitmapFont.HAlignment.RIGHT);
    this.batch.end();
  }
  
  public void resize(int paramInt1, int paramInt2) {}
  
  public void resume() {}
  
  public void show()
  {
    this.game.showAds(true);
    this.cam.setToOrtho(false, this.game.getWidth(), this.game.getHeight());
    this.cam.position.set(this.hexGrid.getWidth(), this.hexGrid.getHeight() / 2.0F, 0.0F);
    float f1 = this.hexGrid.getWidth();
    float f2 = this.hexGrid.getWidth() / 2.0F;
    float f3 = this.game.getWidth() / 2.0F;
    float f4 = this.hexGrid.getWidth();
    float f5 = this.hexGrid.getWidth() / 2.0F;
    float f6 = this.game.getWidth() / 2.0F;
    float f7 = this.hexGrid.getHeight() / 2.0F;
    this.controller = new CameraController(f2 - f3 + f1, f4 - (f5 - f6), this.hexGrid.getHeight() / 2.0F - this.game.getHeight() / 4.0F + f7, this.hexGrid.getHeight() / 2.0F - (this.hexGrid.getHeight() / 2.0F - this.game.getHeight() / 4.0F));
    if (this.game.getWidth() < this.hexGrid.getWidth()) {
      this.controller.allowCameraPanning = true;
    }
    this.gestureDetector = new GestureDetector(20.0F, 0.5F, 2.0F, 0.15F, this.controller);
    this.stagehexes.setCamera(this.cam);
    this.cam_ui.setToOrtho(false, this.game.getWidth(), this.game.getHeight());
    Object localObject = new InputMultiplexer(new InputProcessor[] { this.gestureDetector, this.stageui, this.stagehexes, this.input });
    Gdx.input.setInputProcessor((InputProcessor)localObject);
    Gdx.input.setCatchBackKey(true);
    int i = 0;
    this.hexGrid.getClass();
    if (i >= 11)
    {
      localObject = new TextButton("Back", Assets.skin);
      ((TextButton)localObject).setWidth(Gdx.app.getGraphics().getWidth() / 7);
      ((TextButton)localObject).setHeight(Gdx.app.getGraphics().getHeight() / 8);
      ((TextButton)localObject).addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          HexGridTest.this.game.setScreen(new MainMenu(HexGridTest.this.game));
        }
      });
      ((TextButton)localObject).setPosition(0.0F, Gdx.app.getGraphics().getHeight() - ((TextButton)localObject).getHeight() * 2.0F);
      this.stageui.addActor((Actor)localObject);
      localObject = new TextButton("Debug", Assets.skin);
      ((TextButton)localObject).setWidth(Gdx.app.getGraphics().getWidth() / 7);
      ((TextButton)localObject).setHeight(Gdx.app.getGraphics().getHeight() / 8);
      ((TextButton)localObject).addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          paramAnonymousInputEvent = HexGridTest.this;
          if (HexGridTest.this.debug) {}
          for (boolean bool = false;; bool = true)
          {
            paramAnonymousInputEvent.debug = bool;
            return;
          }
        }
      });
      ((TextButton)localObject).setPosition(this.game.getWidth() - this.game.getButtonWidth(), Gdx.app.getGraphics().getHeight() - ((TextButton)localObject).getHeight() * 2.0F);
      this.stageui.addActor((Actor)localObject);
      return;
    }
    int j = 0;
    for (;;)
    {
      this.hexGrid.getClass();
      if (j >= 7)
      {
        i += 1;
        break;
      }
      this.stagehexes.addActor(this.hexGrid.getHex(i, j));
      j += 1;
    }
  }
  
  protected class CameraController
    implements GestureDetector.GestureListener
  {
    public boolean allowCameraPanning = false;
    float boundX;
    float boundX2;
    float boundY;
    float boundY2;
    boolean flinging = false;
    float initialScale = 1.0F;
    public float velX;
    public float velY;
    
    public CameraController(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
    {
      this.boundX = paramFloat1;
      this.boundX2 = paramFloat2;
      this.boundY = paramFloat3;
      this.boundY2 = paramFloat4;
    }
    
    private float checkBoundsX(float paramFloat)
    {
      float f;
      if ((HexGridTest.this.cam.position.x >= this.boundX) && (paramFloat < 0.0F))
      {
        this.velX = 0.0F;
        HexGridTest.this.cam.position.x = this.boundX;
        f = 0.0F;
      }
      do
      {
        do
        {
          return f;
          f = paramFloat;
        } while (HexGridTest.this.cam.position.x > this.boundX2);
        f = paramFloat;
      } while (paramFloat <= 0.0F);
      this.velX = 0.0F;
      HexGridTest.this.cam.position.x = this.boundX2;
      return 0.0F;
    }
    
    private float checkBoundsY(float paramFloat)
    {
      float f;
      if ((HexGridTest.this.cam.position.y >= this.boundY) && (paramFloat > 0.0F))
      {
        this.velY = 0.0F;
        HexGridTest.this.cam.position.y = this.boundY;
        f = 0.0F;
      }
      do
      {
        do
        {
          return f;
          f = paramFloat;
        } while (HexGridTest.this.cam.position.y > this.boundY2);
        f = paramFloat;
      } while (paramFloat >= 0.0F);
      this.velX = 0.0F;
      HexGridTest.this.cam.position.y = this.boundY2;
      return 0.0F;
    }
    
    public boolean fling(float paramFloat1, float paramFloat2, int paramInt)
    {
      Gdx.app.log("GestureDetectorTest", "fling " + paramFloat1 + ", " + paramFloat2);
      this.flinging = true;
      if (this.allowCameraPanning)
      {
        this.velX = (HexGridTest.this.cam.zoom * paramFloat1 * 0.5F);
        this.velY = (HexGridTest.this.cam.zoom * paramFloat2 * 0.5F);
      }
      return false;
    }
    
    public Vector2 getNormalized(Vector2 paramVector2)
    {
      float f = paramVector2.len();
      if (f == 0.0F) {
        return new Vector2();
      }
      return new Vector2(paramVector2.x / f, paramVector2.y / f);
    }
    
    public boolean longPress(float paramFloat1, float paramFloat2)
    {
      Gdx.app.log("GestureDetectorTest", "long press at " + paramFloat1 + ", " + paramFloat2);
      return false;
    }
    
    public boolean pan(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
    {
      if (this.allowCameraPanning) {
        HexGridTest.this.cam.position.add(-checkBoundsX(paramFloat3) * HexGridTest.this.cam.zoom, checkBoundsY(paramFloat4) * HexGridTest.this.cam.zoom, 0.0F);
      }
      return false;
    }
    
    public boolean panStop(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
    {
      Gdx.app.log("GestureDetectorTest", "pan stop at " + paramFloat1 + ", " + paramFloat2);
      return false;
    }
    
    public boolean pinch(Vector2 paramVector21, Vector2 paramVector22, Vector2 paramVector23, Vector2 paramVector24)
    {
      return false;
    }
    
    public void setBounds(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
    {
      this.boundX = paramFloat1;
      this.boundX2 = paramFloat2;
      this.boundY = paramFloat3;
      this.boundY2 = paramFloat4;
    }
    
    public boolean tap(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
    {
      Gdx.app.log("GestureDetectorTest", "tap at " + paramFloat1 + ", " + paramFloat2 + ", count: " + paramInt1);
      return false;
    }
    
    public boolean touchDown(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
    {
      this.flinging = false;
      this.initialScale = HexGridTest.this.cam.zoom;
      return false;
    }
    
    public void update()
    {
      OrthographicCamera localOrthographicCamera;
      if (Gdx.input.isKeyPressed(47))
      {
        localOrthographicCamera = HexGridTest.this.cam;
        localOrthographicCamera.zoom = ((float)(localOrthographicCamera.zoom + 0.03D));
      }
      if (Gdx.input.isKeyPressed(51))
      {
        localOrthographicCamera = HexGridTest.this.cam;
        localOrthographicCamera.zoom = ((float)(localOrthographicCamera.zoom - 0.03D));
      }
      if (this.flinging)
      {
        this.velX *= 0.92F;
        this.velY *= 0.92F;
        HexGridTest.this.cam.position.add(-checkBoundsX(this.velX) * Gdx.graphics.getDeltaTime(), checkBoundsY(this.velY) * Gdx.graphics.getDeltaTime(), 0.0F);
        if (Math.abs(this.velX) < 0.01F) {
          this.velX = 0.0F;
        }
        if (Math.abs(this.velY) < 0.01F) {
          this.velY = 0.0F;
        }
      }
    }
    
    public boolean zoom(float paramFloat1, float paramFloat2)
    {
      paramFloat1 /= paramFloat2;
      HexGridTest.this.cam.zoom = (this.initialScale * paramFloat1);
      if (HexGridTest.this.cam.zoom < 1.0F) {
        HexGridTest.this.cam.zoom = 1.0F;
      }
      if (HexGridTest.this.cam.zoom > 1.7F) {
        HexGridTest.this.cam.zoom = 1.7F;
      }
      return false;
    }
  }
  
  public class HexGrid
  {
    public final int ARRAY_HEIGHT = 7;
    public final int ARRAY_WIDTH = 11;
    public final int BOTTOM_LEFT = 3;
    public final int BOTTOM_RIGHT = 2;
    private final Hex[][] Hexes = (Hex[][])Array.newInstance(Hex.class, new int[] { 11, 7 });
    public final int LEFT = 4;
    private final int Q = 8;
    private final int R = 7;
    public final int RIGHT = 1;
    public final int TOP_LEFT = 5;
    public final int TOP_RIGHT = 0;
    public int[][] neighbors;
    float x;
    float y;
    
    public HexGrid(float paramFloat1, float paramFloat2)
    {
      this$1 = new int[2];
      HexGridTest.this[1] = 1;
      int[] arrayOfInt1 = new int[2];
      arrayOfInt1[0] = 1;
      int[] arrayOfInt4 = { 1, -1 };
      int[] arrayOfInt2 = new int[2];
      arrayOfInt2[1] = -1;
      int[] arrayOfInt3 = new int[2];
      arrayOfInt3[0] = -1;
      this.neighbors = new int[][] { HexGridTest.this, arrayOfInt1, arrayOfInt4, arrayOfInt2, arrayOfInt3, { -1, 1 } };
      int i = 0;
      if (i >= 11) {
        return;
      }
      int j = 0;
      for (;;)
      {
        if (j >= 7)
        {
          i += 1;
          break;
        }
        this.Hexes[i][j] = new Hex(i, j);
        if (((i == 0) && (j != 6)) || ((i == 1) && ((j == 0) || (j == 1) || (j == 2) || (j == 3))) || ((i == 2) && ((j == 0) || (j == 1))) || ((i == 10) && (j != 0)) || ((i == 9) && ((j == 3) || (j == 4) || (j == 5) || (j == 6))) || ((i == 8) && ((j == 5) || (j == 6)))) {
          this.Hexes[i][j].setUsed(false);
        }
        j += 1;
      }
    }
    
    public void draw(SpriteBatch paramSpriteBatch)
    {
      int i = 10;
      if (i < 0) {
        return;
      }
      int j = 6;
      for (;;)
      {
        if (j < 0)
        {
          i -= 1;
          break;
        }
        this.Hexes[i][j].draw(paramSpriteBatch, 1.0F);
        j -= 1;
      }
    }
    
    public float getHeight()
    {
      return 432.0F;
    }
    
    public Hex getHex(int paramInt1, int paramInt2)
    {
      return this.Hexes[paramInt1][paramInt2];
    }
    
    public Hex getNeighbor(int paramInt1, int paramInt2, int paramInt3)
    {
      Object localObject = this.neighbors[paramInt3];
      try
      {
        localObject = this.Hexes[(localObject[0] + paramInt1)][(localObject[1] + paramInt2)];
        return (Hex)localObject;
      }
      catch (Exception localException) {}
      return null;
    }
    
    public float getWidth()
    {
      return 816.0F;
    }
    
    class Hex
      extends Actor
    {
      public static final int BOTTOM_LEFT = 3;
      public static final int BOTTOM_RIGHT = 2;
      public static final int LEFT = 4;
      public static final int RIGHT = 1;
      public static final int TOP_LEFT = 5;
      public static final int TOP_RIGHT = 0;
      public static final float height = 96.0F;
      public static final float hor_dist = 48.0F;
      public static final float size = 48.0F;
      public static final float vert_dist = 72.0F;
      public static final float width = 96.0F;
      private boolean isSelected = false;
      private boolean isUsed = true;
      public int q;
      public int r;
      public float x;
      public float y;
      
      public Hex(final int paramInt1, final int paramInt2)
      {
        this.q = paramInt1;
        this.r = paramInt2;
        setBounds(getX(), getY() + 24.0F, 96.0F, 48.0F);
        addListener(new ActorGestureListener()
        {
          public void fling(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt) {}
          
          public void tap(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt1, int paramAnonymousInt2)
          {
            Gdx.app.log("Hex", "Tapped [" + paramInt1 + ", " + paramInt2 + "]");
            paramAnonymousInputEvent = HexGridTest.HexGrid.Hex.this;
            if (HexGridTest.HexGrid.Hex.this.isSelected) {}
            for (boolean bool = false;; bool = true)
            {
              paramAnonymousInputEvent.isSelected = bool;
              return;
            }
          }
        });
      }
      
      public void act(float paramFloat)
      {
        super.act(paramFloat);
      }
      
      public void draw(Batch paramBatch, float paramFloat)
      {
        if (isUsed())
        {
          if (this.isSelected) {
            break label107;
          }
          paramBatch.draw(ImageCache.getTexture("hex"), getX(), getY());
        }
        for (;;)
        {
          if (HexGridTest.this.debug) {
            Assets.font24.drawMultiLine(paramBatch, "[" + this.q + ", " + this.r + "]", getX(), 60.0F + getY(), 96.0F, BitmapFont.HAlignment.CENTER);
          }
          return;
          label107:
          paramBatch.draw(ImageCache.getTexture("hex_selected"), getX(), getY());
        }
      }
      
      protected void flipNeighbors()
      {
        int i = 0;
        if (i >= 6) {
          return;
        }
        for (;;)
        {
          try
          {
            Hex localHex = HexGridTest.HexGrid.this.Hexes[(this.q + HexGridTest.HexGrid.this.neighbors[i][0])][(this.r + HexGridTest.HexGrid.this.neighbors[i][1])];
            if (!HexGridTest.HexGrid.this.Hexes[(this.q + HexGridTest.HexGrid.this.neighbors[i][0])][(this.r + HexGridTest.HexGrid.this.neighbors[i][1])].isSelected) {
              continue;
            }
            bool = false;
            localHex.isSelected = bool;
          }
          catch (Exception localException)
          {
            boolean bool;
            Gdx.app.log("Error", "Accessed Hex not in array.");
            continue;
          }
          i += 1;
          break;
          bool = true;
        }
      }
      
      public int getAxialQ()
      {
        return this.q;
      }
      
      public int getAxialR()
      {
        return this.r;
      }
      
      public int getCubeX()
      {
        return this.q;
      }
      
      public int getCubeY()
      {
        return -this.q - this.r;
      }
      
      public int getCubeZ()
      {
        return this.r;
      }
      
      public float getX()
      {
        return (float)(55.68000030517578D * Math.sqrt(3.0D) * (this.q + this.r / 2.0F)) - 48.0F;
      }
      
      public float getY()
      {
        return 72.0F * this.r - 48.0F;
      }
      
      public boolean isUsed()
      {
        return this.isUsed;
      }
      
      public void setUsed(boolean paramBoolean)
      {
        this.isUsed = paramBoolean;
        if (!this.isUsed) {
          setTouchable(Touchable.disabled);
        }
      }
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\tests\HexGridTest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */