package com.johnathongoss.libgdxtests.examples;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.johnathongoss.libgdxtests.Assets;
import com.johnathongoss.libgdxtests.ImageCache;
import com.johnathongoss.libgdxtests.MyGame;
import com.johnathongoss.libgdxtests.MyInputProcessor;
import com.johnathongoss.libgdxtests.ParticleCache;
import com.johnathongoss.libgdxtests.entities.MyTimer;
import com.johnathongoss.libgdxtests.screens.Examples;
import java.util.Iterator;

public class FishTank
  implements Screen
{
  private Array<ParticleEffectPool.PooledEffect> Effects;
  private TextButton backButton;
  private SpriteBatch batch;
  private SpriteBatch batchui;
  private MyTimer bubbleTimer;
  private OrthographicCamera cam;
  public float conservedEnergy;
  public Array<Fish> fishes;
  private Fish followedFish;
  private boolean following = false;
  public float friction;
  private MyGame game;
  public float gravity;
  public double hardness;
  MyInputProcessor input = new MyInputProcessor()
  {
    public boolean keyUp(int paramAnonymousInt)
    {
      if ((paramAnonymousInt == 4) || (paramAnonymousInt == 67) || (paramAnonymousInt == 131)) {
        FishTank.this.game.setScreen(new Examples(FishTank.this.game));
      }
      return false;
    }
  };
  private Stage stage;
  private Stage stageui;
  Sprite tank;
  Sprite tank_shine;
  private String testName;
  public float viscosity = 0.992F;
  
  public FishTank(MyGame paramMyGame)
  {
    this.game = paramMyGame;
    this.testName = "Fish Tank Example |";
    this.batch = new SpriteBatch();
    this.batchui = new SpriteBatch();
    this.stage = new Stage();
    this.stageui = new Stage();
    this.cam = new OrthographicCamera();
    this.Effects = new Array();
    this.fishes = new Array();
    this.tank = new Sprite(ImageCache.getTexture("tank"));
    this.tank_shine = new Sprite(ImageCache.getTexture("tank_shine"));
  }
  
  private void followFish()
  {
    this.cam.zoom = 0.5F;
    this.cam.position.x = this.followedFish.getX();
    this.cam.position.y = this.followedFish.getY();
  }
  
  private void resetCamera()
  {
    this.cam.position.x = (this.game.getWidth() / 2.0F);
    this.cam.position.y = (this.game.getHeight() / 2.0F);
    this.cam.zoom = 1.4F;
  }
  
  public void createBubbles()
  {
    this.Effects.add(ParticleCache.getParticleEffect(3));
    ((ParticleEffectPool.PooledEffect)this.Effects.get(this.Effects.size - 1)).setPosition(MathUtils.random(50.0F, this.game.getWidth() - 50.0F), 0.0F);
  }
  
  public void dispose()
  {
    this.batch.dispose();
    this.batchui.dispose();
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
    this.bubbleTimer.update(paramFloat);
    if (this.following) {
      followFish();
    }
    this.cam.update();
    this.batch.setProjectionMatrix(this.cam.combined);
    this.batch.begin();
    this.tank.draw(this.batch);
    this.batch.end();
    this.stage.act(paramFloat);
    this.stage.draw();
    this.batch.begin();
    Iterator localIterator = this.Effects.iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        this.tank_shine.draw(this.batch);
        this.batch.end();
        this.stageui.act(paramFloat);
        this.stageui.draw();
        this.batchui.begin();
        Assets.font24.drawMultiLine(this.batchui, this.testName, 0.0F, 24.0F, this.game.getWidth(), BitmapFont.HAlignment.RIGHT);
        this.batchui.end();
        return;
      }
      ParticleEffectPool.PooledEffect localPooledEffect = (ParticleEffectPool.PooledEffect)localIterator.next();
      localPooledEffect.draw(this.batch, paramFloat);
      if (localPooledEffect.isComplete())
      {
        this.Effects.removeValue(localPooledEffect, true);
        localPooledEffect.reset();
        localPooledEffect.free();
      }
    }
  }
  
  public void resize(int paramInt1, int paramInt2)
  {
    this.stage.setCamera(this.cam);
    this.batch.setProjectionMatrix(this.cam.combined);
    this.stage.setViewport(paramInt1, paramInt2, true);
    this.stageui.setViewport(paramInt1, paramInt2, true);
    this.backButton.setBounds(0.0F, paramInt2 - this.game.getButtonHeight() * 2.0F, this.game.getButtonWidth(), this.game.getButtonHeight());
    this.tank.setScale(Gdx.app.getGraphics().getWidth() / this.tank.getRegionWidth(), Gdx.app.getGraphics().getHeight() / this.tank.getRegionHeight());
    this.tank_shine.setScale(Gdx.app.getGraphics().getWidth() / this.tank.getRegionWidth(), Gdx.app.getGraphics().getHeight() / this.tank.getRegionHeight());
  }
  
  public void resume() {}
  
  public void show()
  {
    this.game.showAds(true);
    InputMultiplexer localInputMultiplexer = new InputMultiplexer(new InputProcessor[] { this.stageui, this.stage, this.input });
    Gdx.input.setInputProcessor(localInputMultiplexer);
    Gdx.input.setCatchBackKey(true);
    this.tank.setPosition(0.0F, 0.0F);
    this.tank.setOrigin(0.0F, 0.0F);
    this.tank.setScale(this.game.getWidth() / this.tank.getRegionWidth(), Gdx.app.getGraphics().getHeight() / this.tank.getRegionHeight());
    this.tank_shine.setPosition(-20.0F, -20.0F);
    this.tank_shine.setOrigin(20.0F, 20.0F);
    this.tank_shine.setScale(this.game.getWidth() / this.tank.getRegionWidth(), Gdx.app.getGraphics().getHeight() / this.tank.getRegionHeight());
    this.bubbleTimer = new MyTimer(1.0F)
    {
      protected void perform()
      {
        FishTank.this.createBubbles();
        setCap(MathUtils.random(0.4F, 1.0F));
      }
    };
    this.bubbleTimer.setRepeating(true);
    this.bubbleTimer.start();
    this.cam.setToOrtho(false, this.game.getWidth(), this.game.getHeight());
    this.cam.update();
    this.stage.setCamera(this.cam);
    this.cam.zoom = 1.4F;
    this.gravity = 0.0F;
    this.friction = -0.1F;
    this.hardness = 1.0D;
    this.conservedEnergy = 0.5F;
    this.viscosity = 0.99F;
    this.backButton = new TextButton("Back", Assets.skin);
    this.backButton.setBounds(0.0F, this.game.getHeight() - this.game.getButtonHeight() * 2.0F, this.game.getWidth(), this.game.getButtonHeight());
    this.backButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        FishTank.this.game.setScreen(new Examples(FishTank.this.game));
      }
    });
    this.stageui.addActor(this.backButton);
    int i = 0;
    for (;;)
    {
      if (i >= 8) {
        return;
      }
      this.fishes.add(new Fish(MathUtils.random(this.game.getWidth()), MathUtils.random(this.game.getHeight()), MathUtils.random(this.game.getWidth() / 15.0F, this.game.getWidth() / 10.0F), i, this.fishes));
      ((Fish)this.fishes.get(i)).setVelocity(MathUtils.random(-1.2F, 1.2F), MathUtils.random(-1.2F, 1.2F));
      ((Fish)this.fishes.get(i)).changeColor();
      this.stage.addActor((Actor)this.fishes.get(i));
      i += 1;
    }
  }
  
  public class Fish
    extends Actor
  {
    Color color = Color.WHITE;
    public float diameter;
    int id;
    Array<Fish> others;
    Sprite sprite = new Sprite(ImageCache.getTexture("fish"));
    Color tempColor;
    Fish thisFish = this;
    MyTimer timer = new MyTimer(MathUtils.random(1.0F, 8.0F))
    {
      protected void perform()
      {
        FishTank.Fish.this.changeDirection(1.0F);
        FishTank.Fish.this.timer.setCap(MathUtils.random(3.5F, 9.0F));
      }
    };
    float vx = 0.0F;
    float vy = 0.0F;
    private float ySca = 1.0F;
    
    public Fish(float paramFloat1, float paramFloat2, int paramInt, Array<Fish> paramArray)
    {
      this.timer.start();
      this.timer.setRepeating(true);
      setX(paramFloat1);
      setY(paramFloat2);
      this.diameter = paramInt;
      this.id = paramArray;
      Array localArray;
      this.others = localArray;
      setBounds(getX() - this.diameter / 2.0F, getY() - this.diameter / 2.0F, this.diameter, this.diameter);
      setOrigin(this.diameter / 2.0F, this.diameter / 2.0F);
      setScale(1.0F);
      addListener(new ActorGestureListener()
      {
        public void fling(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt) {}
        
        public void tap(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt1, int paramAnonymousInt2)
        {
          if (!FishTank.this.following)
          {
            FishTank.this.followedFish = FishTank.Fish.this.thisFish;
            FishTank.this.following = true;
          }
          do
          {
            return;
            if ((FishTank.this.following) && (FishTank.Fish.this.thisFish != FishTank.this.followedFish))
            {
              FishTank.this.followedFish = FishTank.Fish.this.thisFish;
              FishTank.this.following = true;
              return;
            }
          } while ((!FishTank.this.following) || (FishTank.Fish.this.thisFish != FishTank.this.followedFish));
          FishTank.this.resetCamera();
          FishTank.this.following = false;
        }
      });
    }
    
    private void animate()
    {
      if (getScaleX() == 1.0F) {
        addAction(Actions.sequence(Actions.scaleTo(0.4F, 1.0F, 0.15F), Actions.scaleTo(1.0F, 1.0F, 0.15F)));
      }
    }
    
    private void calcRotation()
    {
      float f2 = MathUtils.atan2(this.vy, this.vx) * 180.0F / 3.1415927F;
      float f1 = f2;
      if (f2 < 0.0F) {
        f1 = f2 + 360.0F;
      }
      if ((f1 > 90.0F) && (f1 < 270.0F)) {}
      for (this.ySca = -1.0F;; this.ySca = 1.0F)
      {
        setRotation(f1);
        return;
      }
    }
    
    private void changeDirection(float paramFloat)
    {
      animate();
      this.vx += MathUtils.random(-2.0F * paramFloat, 2.0F * paramFloat);
      this.vy += MathUtils.random(-0.6F * paramFloat, 0.6F * paramFloat);
    }
    
    public void Fling(float paramFloat1, float paramFloat2)
    {
      this.vx = (paramFloat1 * 0.01F);
      this.vy = (paramFloat2 * 0.01F);
    }
    
    public void act(float paramFloat)
    {
      super.act(paramFloat);
      this.timer.update(paramFloat);
      collide(paramFloat);
      move(paramFloat);
      calcRotation();
    }
    
    public void addX(float paramFloat)
    {
      setX(getX() + paramFloat);
    }
    
    public void addY(float paramFloat)
    {
      setY(getY() + paramFloat);
    }
    
    public void changeColor()
    {
      setColor(new Color(MathUtils.random(0.0F, 1.0F), MathUtils.random(0.0F, 1.0F), MathUtils.random(0.0F, 1.0F), 1.0F));
    }
    
    void collide(float paramFloat)
    {
      int i = this.id + 1;
      for (;;)
      {
        if (i >= FishTank.this.fishes.size) {
          return;
        }
        paramFloat = ((Fish)this.others.get(i)).getXOffset() - getXOffset();
        float f1 = ((Fish)this.others.get(i)).getYOffset() - getYOffset();
        double d1 = Math.sqrt(paramFloat * paramFloat + f1 * f1);
        float f2 = ((Fish)this.others.get(i)).diameter / 2.0F + this.diameter / 2.0F;
        if (d1 < f2)
        {
          this.vx *= FishTank.this.conservedEnergy;
          this.vy *= FishTank.this.conservedEnergy;
          Fish localFish = (Fish)this.others.get(i);
          localFish.vx *= FishTank.this.conservedEnergy;
          localFish = (Fish)this.others.get(i);
          localFish.vy *= FishTank.this.conservedEnergy;
          double d5 = Math.atan2(f1, paramFloat);
          d1 = getXOffset();
          double d2 = Math.cos(d5);
          double d3 = f2;
          double d4 = getYOffset();
          d5 = Math.sin(d5);
          double d6 = f2;
          double d7 = ((Fish)this.others.get(i)).getXOffset();
          double d8 = FishTank.this.hardness;
          double d9 = ((Fish)this.others.get(i)).getYOffset();
          double d10 = FishTank.this.hardness;
          this.vx = ((float)(this.vx - (d1 + d2 * d3 - d7) * d8));
          this.vy = ((float)(this.vy - (d4 + d5 * d6 - d9) * d10));
          changeDirection(2.0F);
          ((Fish)this.others.get(i)).changeDirection(1.0F);
        }
        i += 1;
      }
    }
    
    public void draw(Batch paramBatch, float paramFloat)
    {
      paramBatch.setColor(getColor());
      Sprite localSprite = this.sprite;
      paramFloat = getX();
      float f1 = getY();
      float f2 = getOriginX();
      float f3 = getOriginY();
      float f4 = getWidth();
      float f5 = getHeight();
      float f6 = -getScaleX();
      float f7 = this.ySca;
      paramBatch.draw(localSprite, paramFloat, f1, f2, f3, f4, f5, f6, getScaleY() * f7, getRotation());
      paramBatch.setColor(Color.WHITE);
    }
    
    public float getXOffset()
    {
      return getX() + this.diameter / 2.0F;
    }
    
    public float getYOffset()
    {
      return getY() + this.diameter / 2.0F;
    }
    
    void move(float paramFloat)
    {
      this.vy *= FishTank.this.viscosity;
      this.vx *= FishTank.this.viscosity;
      if (this.vx > 2.0F) {
        this.vx = 2.0F;
      }
      if (this.vy > 2.0F) {
        this.vy = 2.0F;
      }
      addX(this.vx);
      addY(this.vy);
      if (getXOffset() + this.diameter / 2.0F > FishTank.this.game.getWidth())
      {
        animate();
        setX(FishTank.this.game.getWidth() - this.diameter);
        this.vx *= FishTank.this.friction;
      }
      do
      {
        while (getYOffset() + this.diameter / 2.0F > FishTank.this.game.getHeight())
        {
          animate();
          setY(FishTank.this.game.getHeight() - this.diameter);
          this.vy *= FishTank.this.friction;
          return;
          if (getXOffset() - this.diameter / 2.0F < 0.0F)
          {
            animate();
            setX(0.0F);
            this.vx *= FishTank.this.friction;
          }
        }
      } while (getYOffset() - this.diameter / 2.0F >= 0.0F);
      animate();
      setY(0.0F);
      this.vy *= FishTank.this.friction;
    }
    
    public void setDiameter(float paramFloat)
    {
      this.diameter = paramFloat;
      setBounds(getX() - paramFloat / 2.0F, getY() - paramFloat / 2.0F, paramFloat, paramFloat);
      setOrigin(paramFloat / 2.0F, paramFloat / 2.0F);
      setScale(1.0F);
    }
    
    public void setVelocity(float paramFloat1, float paramFloat2)
    {
      this.vx = paramFloat1;
      this.vy = paramFloat2;
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\examples\FishTank.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */