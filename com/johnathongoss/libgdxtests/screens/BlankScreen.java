package com.johnathongoss.libgdxtests.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.johnathongoss.libgdxtests.MyGame;

public abstract class BlankScreen
  implements Screen, InputProcessor
{
  protected static final int BOX_POSITION_ITERATIONS = 2;
  protected static final float BOX_STEP = 0.016666668F;
  protected static final int BOX_VELOCITY_ITERATIONS = 6;
  protected static final float BOX_WORLD_TO = 50.0F;
  protected static final float WORLD_TO_BOX = 0.02F;
  protected SpriteBatch batch;
  protected SpriteBatch batchui;
  protected OrthographicCamera cam;
  protected CameraController controller;
  Box2DDebugRenderer debugRenderer;
  protected MyGame game;
  protected GestureDetector gestureDetector;
  protected float height;
  protected boolean showDebug = false;
  protected Stage stage;
  protected Stage stageui;
  protected float width;
  public World world;
  
  public BlankScreen(MyGame paramMyGame)
  {
    this.game = paramMyGame;
    this.batch = new SpriteBatch();
    this.batchui = new SpriteBatch();
    this.stage = new Stage();
    this.stageui = new Stage();
    this.width = Gdx.app.getGraphics().getWidth();
    this.height = Gdx.app.getGraphics().getHeight();
    this.cam = new OrthographicCamera();
    this.cam.setToOrtho(false, this.width, this.height);
    this.cam.update();
    this.stage.setCamera(this.cam);
    paramMyGame = new InputMultiplexer(new InputProcessor[] { this.stageui, this.stage });
    Gdx.input.setInputProcessor(paramMyGame);
  }
  
  protected void addCameraControl(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    this.controller = new CameraController();
    this.controller.setBounds(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
    this.gestureDetector = new GestureDetector(20.0F, 0.5F, 2.0F, 0.15F, this.controller);
    InputMultiplexer localInputMultiplexer = new InputMultiplexer(new InputProcessor[] { this.stageui, this.stage, this.gestureDetector });
    Gdx.input.setInputProcessor(localInputMultiplexer);
  }
  
  protected void addInput()
  {
    InputMultiplexer localInputMultiplexer = new InputMultiplexer(new InputProcessor[] { this.stageui, this.stage });
    Gdx.input.setInputProcessor(localInputMultiplexer);
  }
  
  public void dispose()
  {
    this.stage.dispose();
    this.stageui.dispose();
    this.batch.dispose();
    this.batchui.dispose();
  }
  
  public void hide()
  {
    dispose();
  }
  
  public void pause() {}
  
  public abstract void render(float paramFloat);
  
  public void resize(int paramInt1, int paramInt2)
  {
    paramInt1 = Gdx.app.getGraphics().getWidth();
    paramInt2 = Gdx.app.getGraphics().getHeight();
    this.stage.setCamera(this.cam);
    Gdx.gl.glViewport(0, 0, paramInt1, paramInt2);
    this.cam.update();
    this.batch.setProjectionMatrix(this.cam.combined);
    this.stage.setViewport(paramInt1, paramInt2, false);
    this.stageui.setViewport(paramInt1, paramInt2, false);
  }
  
  public void resume() {}
  
  public abstract void show();
  
  protected class CameraController
    implements GestureDetector.GestureListener
  {
    float boundX = BlankScreen.this.width * BlankScreen.this.cam.zoom / 2.0F;
    float boundX2 = 0.0F;
    float boundY = 0.0F;
    float boundY2 = BlankScreen.this.height;
    boolean flinging = false;
    float initialScale = 1.0F;
    Vector2 tempInV1;
    Vector2 tempInV2;
    Vector2 tempV1;
    Vector2 tempV2;
    public float velX;
    public float velY;
    
    public CameraController() {}
    
    private float checkBoundsX(float paramFloat)
    {
      float f;
      if ((BlankScreen.this.cam.position.x >= this.boundX) && (paramFloat < 0.0F))
      {
        this.velX = 0.0F;
        BlankScreen.this.cam.position.x = this.boundX;
        f = 0.0F;
      }
      do
      {
        do
        {
          return f;
          f = paramFloat;
        } while (BlankScreen.this.cam.position.x > this.boundX2);
        f = paramFloat;
      } while (paramFloat <= 0.0F);
      this.velX = 0.0F;
      BlankScreen.this.cam.position.x = this.boundX2;
      return 0.0F;
    }
    
    private float checkBoundsY(float paramFloat)
    {
      float f;
      if ((BlankScreen.this.cam.position.y >= this.boundY) && (paramFloat > 0.0F))
      {
        this.velY = 0.0F;
        BlankScreen.this.cam.position.y = this.boundY;
        f = 0.0F;
      }
      do
      {
        do
        {
          return f;
          f = paramFloat;
        } while (BlankScreen.this.cam.position.y > this.boundY2);
        f = paramFloat;
      } while (paramFloat >= 0.0F);
      this.velX = 0.0F;
      BlankScreen.this.cam.position.y = this.boundY2;
      return 0.0F;
    }
    
    private float dotProduct(Vector2 paramVector21, Vector2 paramVector22)
    {
      return paramVector21.x * paramVector22.x + paramVector21.y * paramVector22.y;
    }
    
    public boolean fling(float paramFloat1, float paramFloat2, int paramInt)
    {
      Gdx.app.log("GestureDetectorTest", "fling " + paramFloat1 + ", " + paramFloat2);
      this.flinging = true;
      this.velX = (BlankScreen.this.cam.zoom * paramFloat1 * 0.5F);
      this.velY = (BlankScreen.this.cam.zoom * paramFloat2 * 0.5F);
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
      BlankScreen.this.cam.position.add(-checkBoundsX(paramFloat3) * BlankScreen.this.cam.zoom, checkBoundsY(paramFloat4) * BlankScreen.this.cam.zoom, 0.0F);
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
      this.initialScale = BlankScreen.this.cam.zoom;
      return false;
    }
    
    public void update()
    {
      OrthographicCamera localOrthographicCamera;
      if (Gdx.input.isKeyPressed(47))
      {
        localOrthographicCamera = BlankScreen.this.cam;
        localOrthographicCamera.zoom = ((float)(localOrthographicCamera.zoom + 0.03D));
      }
      if (Gdx.input.isKeyPressed(51))
      {
        localOrthographicCamera = BlankScreen.this.cam;
        localOrthographicCamera.zoom = ((float)(localOrthographicCamera.zoom - 0.03D));
      }
      if (this.flinging)
      {
        this.velX *= 0.92F;
        this.velY *= 0.92F;
        BlankScreen.this.cam.position.add(-checkBoundsX(this.velX) * Gdx.graphics.getDeltaTime(), checkBoundsY(this.velY) * Gdx.graphics.getDeltaTime(), 0.0F);
        if (Math.abs(this.velX) < 0.01F) {
          this.velX = 0.0F;
        }
        if (Math.abs(this.velY) < 0.01F) {
          this.velY = 0.0F;
        }
      }
    }
    
    public void updateBounds()
    {
      this.boundX = (BlankScreen.this.width * BlankScreen.this.cam.zoom / 2.0F);
      this.boundX2 = (BlankScreen.this.width - BlankScreen.this.width * BlankScreen.this.cam.zoom / 2.0F);
      this.boundY = (BlankScreen.this.height * BlankScreen.this.cam.zoom / 2.0F);
      this.boundY2 = (BlankScreen.this.height - BlankScreen.this.height * BlankScreen.this.cam.zoom / 2.0F);
    }
    
    public boolean zoom(float paramFloat1, float paramFloat2)
    {
      paramFloat1 /= paramFloat2;
      BlankScreen.this.cam.zoom = (this.initialScale * paramFloat1);
      if (BlankScreen.this.cam.zoom < 0.1F) {
        BlankScreen.this.cam.zoom = 0.1F;
      }
      if (BlankScreen.this.cam.zoom > 100.0F) {
        BlankScreen.this.cam.zoom = 100.0F;
      }
      return false;
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\screens\BlankScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */