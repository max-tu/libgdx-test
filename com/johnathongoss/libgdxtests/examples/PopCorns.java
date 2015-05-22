package com.johnathongoss.libgdxtests.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.johnathongoss.libgdxtests.Assets;
import com.johnathongoss.libgdxtests.MyGame;
import com.johnathongoss.libgdxtests.MyInputProcessor;
import com.johnathongoss.libgdxtests.Sounds;
import com.johnathongoss.libgdxtests.Sounds.SoundPointer;
import com.johnathongoss.libgdxtests.entities.MyTimer;
import com.johnathongoss.libgdxtests.screens.Examples;

public class PopCorns
  implements Screen
{
  TextButton backButton;
  private SpriteBatch batchui;
  private float conservedEnergy;
  private Array<Corn> corns;
  public float friction;
  MyGame game;
  private float gravity;
  private float hardness;
  MyInputProcessor input = new MyInputProcessor()
  {
    public boolean keyUp(int paramAnonymousInt)
    {
      if ((paramAnonymousInt == 4) || (paramAnonymousInt == 67) || (paramAnonymousInt == 131)) {
        PopCorns.this.game.setScreen(new Examples(PopCorns.this.game));
      }
      return false;
    }
  };
  private int numBalls;
  public ShapeRenderer shapeRenderer;
  private Stage stage;
  private Stage stageui;
  String testName;
  MyTimer timer;
  
  public PopCorns(MyGame paramMyGame)
  {
    this.game = paramMyGame;
    this.testName = "Pop Corn Example |";
    this.stage = new Stage();
    this.stageui = new Stage();
    this.batchui = new SpriteBatch();
    this.shapeRenderer = new ShapeRenderer();
    this.timer = new MyTimer(MathUtils.random(2.0F, 6.0F))
    {
      protected void perform()
      {
        PopCorns.this.popTheCorn();
        PopCorns.this.timer.setCap(MathUtils.random(0.0F, 3.0F));
      }
    };
    this.backButton = new TextButton("Back", Assets.skin);
    this.corns = new Array();
  }
  
  private void popTheCorn()
  {
    int i = MathUtils.random(0, this.corns.size - 1);
    if (!((Corn)this.corns.get(i)).popped) {
      ((Corn)this.corns.get(i)).pop();
    }
  }
  
  public void dispose()
  {
    this.stage.dispose();
    this.stageui.dispose();
    this.batchui.dispose();
    Sounds.ClearSounds();
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
    this.stageui.act(paramFloat);
    this.stageui.draw();
    if (this.gravity > -0.8F) {
      this.gravity -= paramFloat;
    }
    this.timer.update(paramFloat);
    this.batchui.begin();
    Assets.font24.drawMultiLine(this.batchui, this.testName, 0.0F, 24.0F, this.game.getWidth(), BitmapFont.HAlignment.RIGHT);
    this.batchui.end();
  }
  
  public void resize(int paramInt1, int paramInt2) {}
  
  public void resume() {}
  
  public void show()
  {
    this.game.showAds(true);
    InputMultiplexer localInputMultiplexer = new InputMultiplexer(new InputProcessor[] { this.stageui, this.stage, this.input });
    Gdx.input.setInputProcessor(localInputMultiplexer);
    Gdx.input.setCatchBackKey(true);
    this.timer.start();
    this.timer.setRepeating(true);
    this.backButton.setBounds(0.0F, this.game.getHeight() - this.game.getButtonHeight() * 2.0F, this.game.getButtonWidth(), this.game.getButtonHeight());
    this.backButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        PopCorns.this.game.setScreen(new Examples(PopCorns.this.game));
      }
    });
    this.stageui.addActor(this.backButton);
    this.gravity = -0.1F;
    this.hardness = 0.6F;
    this.conservedEnergy = 0.85F;
    this.friction = -0.75F;
    this.numBalls = 50;
    int i = 0;
    for (;;)
    {
      if (i >= this.numBalls) {
        return;
      }
      this.corns.add(new Corn(MathUtils.random(this.game.getWidth()), MathUtils.random(0.0F, this.game.getHeight() / 2.0F), this.game.getWidth() / 30.0F, i, this.corns));
      ((Corn)this.corns.get(i)).setVelocity(0.0F, 0.0F);
      this.stage.addActor((Actor)this.corns.get(i));
      i += 1;
    }
  }
  
  private class Corn
    extends Actor
  {
    public float diameter;
    int id;
    Array<Corn> others;
    public boolean popped;
    float vx = 0.0F;
    float vy = 0.0F;
    
    public Corn(float paramFloat1, float paramFloat2, int paramInt, Array<Corn> paramArray)
    {
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
          if (!PopCorns.Corn.this.popped) {
            PopCorns.Corn.this.pop();
          }
        }
      });
      setTouchable(Touchable.enabled);
    }
    
    public void act(float paramFloat)
    {
      collide(paramFloat);
      move(paramFloat);
      int j;
      for (int i = 0;; i = j + 1)
      {
        if (i >= getActions().size) {
          return;
        }
        Action localAction = (Action)getActions().get(i);
        j = i;
        if (localAction.act(paramFloat))
        {
          j = i;
          if (i < getActions().size)
          {
            getActions().removeIndex(i);
            localAction.setActor(null);
            j = i - 1;
          }
        }
      }
    }
    
    public void addX(float paramFloat)
    {
      setX(getX() + paramFloat);
    }
    
    public void addY(float paramFloat)
    {
      setY(getY() + paramFloat);
    }
    
    void collide(float paramFloat)
    {
      int i = this.id + 1;
      for (;;)
      {
        if (i >= PopCorns.this.corns.size) {
          return;
        }
        paramFloat = ((Corn)this.others.get(i)).getXOffset() - getXOffset();
        float f1 = ((Corn)this.others.get(i)).getYOffset() - getYOffset();
        double d1 = Math.sqrt(paramFloat * paramFloat + f1 * f1);
        float f2 = ((Corn)this.others.get(i)).diameter / 2.0F + this.diameter / 2.0F;
        if (d1 < f2)
        {
          this.vx *= PopCorns.this.conservedEnergy;
          this.vy *= PopCorns.this.conservedEnergy;
          Corn localCorn = (Corn)this.others.get(i);
          localCorn.vx *= PopCorns.this.conservedEnergy;
          localCorn = (Corn)this.others.get(i);
          localCorn.vy *= PopCorns.this.conservedEnergy;
          double d5 = Math.atan2(f1, paramFloat);
          double d2 = getXOffset();
          double d3 = Math.cos(d5);
          double d4 = f2;
          d1 = getYOffset();
          d5 = Math.sin(d5);
          double d6 = f2;
          d2 = (d2 + d3 * d4 - ((Corn)this.others.get(i)).getXOffset()) * PopCorns.this.hardness;
          d1 = (d1 + d5 * d6 - ((Corn)this.others.get(i)).getYOffset()) * PopCorns.this.hardness;
          this.vx = ((float)(this.vx - d2));
          this.vy = ((float)(this.vy - d1));
          localCorn = (Corn)this.others.get(i);
          localCorn.vx = ((float)(localCorn.vx + d2));
          localCorn = (Corn)this.others.get(i);
          localCorn.vy = ((float)(localCorn.vy + d1));
        }
        i += 1;
      }
    }
    
    public void draw(Batch paramBatch, float paramFloat)
    {
      PopCorns.this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
      PopCorns.this.shapeRenderer.setProjectionMatrix(paramBatch.getProjectionMatrix());
      PopCorns.this.shapeRenderer.setTransformMatrix(paramBatch.getTransformMatrix());
      PopCorns.this.shapeRenderer.scale(getScaleX(), getScaleY(), 0.0F);
      PopCorns.this.shapeRenderer.setColor(getColor());
      PopCorns.this.shapeRenderer.circle(getXOffset(), getYOffset(), this.diameter / 2.0F);
      PopCorns.this.shapeRenderer.end();
      PopCorns.this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
      PopCorns.this.shapeRenderer.setProjectionMatrix(paramBatch.getProjectionMatrix());
      PopCorns.this.shapeRenderer.setTransformMatrix(paramBatch.getTransformMatrix());
      PopCorns.this.shapeRenderer.scale(getScaleX(), getScaleY(), 0.0F);
      PopCorns.this.shapeRenderer.setColor(Color.BLACK);
      PopCorns.this.shapeRenderer.circle(getXOffset(), getYOffset(), this.diameter / 2.0F);
      PopCorns.this.shapeRenderer.end();
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
      this.vy += PopCorns.this.gravity;
      addX(this.vx);
      addY(this.vy);
      if (getXOffset() + this.diameter / 2.0F > PopCorns.this.game.getWidth())
      {
        setX(PopCorns.this.game.getWidth() - this.diameter);
        this.vx *= PopCorns.this.friction;
      }
      do
      {
        while (getYOffset() + this.diameter / 2.0F > PopCorns.this.game.getHeight())
        {
          setY(PopCorns.this.game.getHeight() - this.diameter);
          this.vy *= PopCorns.this.friction;
          return;
          if (getXOffset() - this.diameter / 2.0F < 0.0F)
          {
            setX(0.0F);
            this.vx *= PopCorns.this.friction;
          }
        }
      } while (getYOffset() - this.diameter / 2.0F >= 0.0F);
      setY(0.0F);
      this.vy *= PopCorns.this.friction;
    }
    
    public void pop()
    {
      Sounds.PlaySound(Sounds.SoundPointer.POPCORN_POP);
      this.popped = true;
      setDiameter(MathUtils.random(PopCorns.this.game.getWidth() / 18.0F, PopCorns.this.game.getWidth() / 12.0F));
      setColor(1.0F, 0.8F, 0.0F, 1.0F);
      setVelocity(MathUtils.random(-9.0F, 9.0F), MathUtils.random(-9.0F, 9.0F));
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


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\examples\PopCorns.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */