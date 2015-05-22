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
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
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

public class AnimationTest
  implements Screen
{
  SpriteBatch batch;
  OrthographicCamera cam;
  MyGame game;
  MyInputProcessor input = new MyInputProcessor()
  {
    public boolean keyUp(int paramAnonymousInt)
    {
      if ((paramAnonymousInt == 4) || (paramAnonymousInt == 67) || (paramAnonymousInt == 131)) {
        AnimationTest.this.game.setScreen(new MainMenu(AnimationTest.this.game));
      }
      return false;
    }
    
    public boolean touchDown(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
    {
      AnimationTest.this.man.setTarget(new Vector2(paramAnonymousInt1, Gdx.app.getGraphics().getHeight() - paramAnonymousInt2));
      return false;
    }
    
    public boolean touchDragged(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      AnimationTest.this.man.setTarget(new Vector2(paramAnonymousInt1, Gdx.app.getGraphics().getHeight() - paramAnonymousInt2));
      return false;
    }
  };
  Walker man;
  Stage stage;
  Stage stageui;
  private String testName = "Animation Test |";
  
  public AnimationTest(MyGame paramMyGame)
  {
    this.game = paramMyGame;
  }
  
  public void dispose()
  {
    this.batch.dispose();
    this.stage.dispose();
    this.stageui.dispose();
  }
  
  public void hide()
  {
    dispose();
  }
  
  public void pause() {}
  
  public void render(float paramFloat)
  {
    Gdx.gl.glClear(16384);
    this.stage.act(paramFloat);
    this.stage.draw();
    this.batch.begin();
    this.man.act(paramFloat);
    this.man.draw(this.batch, 1.0F);
    Assets.font24.drawMultiLine(this.batch, this.testName, 0.0F, 24.0F, Gdx.app.getGraphics().getWidth(), BitmapFont.HAlignment.RIGHT);
    Assets.font24.setColor(1.0F, 1.0F, 1.0F, 0.8F);
    Assets.font24.drawMultiLine(this.batch, "FPS: " + Gdx.app.getGraphics().getFramesPerSecond(), 0.0F, 24.0F, Gdx.app.getGraphics().getWidth(), BitmapFont.HAlignment.LEFT);
    Assets.font24.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    this.batch.end();
    this.stageui.act(paramFloat);
    this.stageui.draw();
  }
  
  public void resize(int paramInt1, int paramInt2)
  {
    this.stageui.setViewport(paramInt1, paramInt2);
  }
  
  public void resume() {}
  
  public void show()
  {
    this.game.showAds(true);
    this.batch = new SpriteBatch();
    this.stage = new Stage();
    this.stageui = new Stage();
    this.cam = new OrthographicCamera();
    this.cam.setToOrtho(false, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
    this.cam.update();
    this.stage.setCamera(this.cam);
    this.man = new Walker("man");
    this.man.setPosition(new Vector2(Gdx.app.getGraphics().getWidth() / 2, Gdx.app.getGraphics().getHeight() / 2));
    Object localObject = new InputMultiplexer(new InputProcessor[] { this.stageui, this.stage, this.input });
    Gdx.input.setInputProcessor((InputProcessor)localObject);
    Gdx.input.setCatchBackKey(true);
    localObject = new TextButton("Back", Assets.skin);
    ((TextButton)localObject).setWidth(Gdx.app.getGraphics().getWidth() / 7);
    ((TextButton)localObject).setHeight(Gdx.app.getGraphics().getHeight() / 8);
    ((TextButton)localObject).addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        AnimationTest.this.game.setScreen(new MainMenu(AnimationTest.this.game));
      }
    });
    ((TextButton)localObject).setPosition(0.0F, Gdx.app.getGraphics().getHeight() - ((TextButton)localObject).getHeight() * 2.0F);
    this.stageui.addActor((Actor)localObject);
  }
  
  class Walker
    extends Actor
  {
    Animation aWalk_d;
    Animation aWalk_l;
    Animation aWalk_r;
    Animation aWalk_u;
    private TextureRegion currentFrame;
    private boolean moving = false;
    Vector2 position;
    private Array<Sprite> sprites;
    Vector2 target;
    private float time = 0.0F;
    Vector2 velocity;
    private float walkSpeed = 1.5F;
    
    Walker(String paramString)
    {
      this.sprites = Assets.spriteSheet.createSprites(paramString + "_d");
      this.aWalk_d = new Animation(0.1F / this.walkSpeed, this.sprites, 2);
      this.sprites = Assets.spriteSheet.createSprites(paramString + "_u");
      this.aWalk_u = new Animation(0.1F / this.walkSpeed, this.sprites, 2);
      this.sprites = Assets.spriteSheet.createSprites(paramString + "_l");
      this.aWalk_l = new Animation(0.1F / this.walkSpeed, this.sprites, 2);
      this.sprites = Assets.spriteSheet.createSprites(paramString + "_r");
      this.aWalk_r = new Animation(0.1F / this.walkSpeed, this.sprites, 2);
      this.position = new Vector2();
      this.target = new Vector2();
      this.velocity = new Vector2();
    }
    
    public void act(float paramFloat)
    {
      super.act(paramFloat);
      this.time += paramFloat;
      if ((this.velocity.y > 0.0F) && (Math.abs(this.velocity.y) >= Math.abs(this.velocity.x)))
      {
        this.currentFrame = this.aWalk_u.getKeyFrame(this.time);
        if (!this.moving) {
          this.currentFrame = this.aWalk_d.getKeyFrame(0.0F);
        }
        if ((this.velocity.x > 0.0F) && (this.position.x > this.target.x)) {
          this.velocity.x = 0.0F;
        }
        if ((this.velocity.x < 0.0F) && (this.position.x < this.target.x)) {
          this.velocity.x = 0.0F;
        }
        if ((this.velocity.y > 0.0F) && (this.position.y > this.target.y)) {
          this.velocity.y = 0.0F;
        }
        if ((this.velocity.y < 0.0F) && (this.position.y < this.target.y)) {
          this.velocity.y = 0.0F;
        }
        if (this.velocity.len() != 0.0F) {
          break label411;
        }
        this.moving = false;
        this.time = 0.0F;
      }
      for (;;)
      {
        if (this.moving) {
          this.position.add(this.velocity);
        }
        return;
        if ((this.velocity.y < 0.0F) && (Math.abs(this.velocity.y) >= Math.abs(this.velocity.x)))
        {
          this.currentFrame = this.aWalk_d.getKeyFrame(this.time);
          break;
        }
        if (this.velocity.x > 0.0F)
        {
          this.currentFrame = this.aWalk_r.getKeyFrame(this.time);
          break;
        }
        if (this.velocity.x < 0.0F)
        {
          this.currentFrame = this.aWalk_l.getKeyFrame(this.time);
          break;
        }
        this.currentFrame = this.aWalk_d.getKeyFrame(this.time);
        break;
        label411:
        this.moving = true;
      }
    }
    
    public void draw(Batch paramBatch, float paramFloat)
    {
      paramBatch.draw(this.currentFrame, this.position.x - 32.0F, this.position.y);
    }
    
    public Vector2 getPosition()
    {
      return this.position;
    }
    
    public void setPosition(Vector2 paramVector2)
    {
      this.position = paramVector2;
    }
    
    public void setTarget(Vector2 paramVector2)
    {
      this.target = paramVector2;
      paramVector2 = new Vector2(paramVector2);
      paramVector2.scl(1.0F / paramVector2.sub(this.position).len());
      this.velocity = paramVector2.scl(this.walkSpeed);
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\tests\AnimationTest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */