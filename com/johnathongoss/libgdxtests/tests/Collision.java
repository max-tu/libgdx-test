package com.johnathongoss.libgdxtests.tests;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.johnathongoss.libgdxtests.Assets;
import com.johnathongoss.libgdxtests.MyGame;
import com.johnathongoss.libgdxtests.MyInputProcessor;
import com.johnathongoss.libgdxtests.screens.MainMenu;
import java.util.Iterator;

public class Collision
  extends BlankTestScreen
{
  protected float[] CEnergyLevel = { 0.25F, 0.5F, 0.75F, 0.85F, 0.95F, 1.0F, 1.01F, 1.02F, 1.04F, 1.06F, 1.08F, 1.1F };
  protected float[] GravityLevel = { 1.0F, 0.5F, 0.25F, 0.1F, 0.05F, 0.02F, 0.01F, 0.0F, -0.01F, -0.02F, -0.05F, -0.1F, -0.25F, -0.5F, -1.0F };
  protected float[] HardnessLevel = { 0.02F, 0.05F, 0.1F, 0.25F, 0.5F, 1.0F };
  protected Array<Ball> balls;
  protected int cEnergyPointer = 0;
  protected float conservedEnergy = 0.95F;
  protected float friction = -0.85F;
  protected float gravity = -0.07F;
  protected int gravityPointer = 0;
  protected int hardnessPointer = 0;
  MyInputProcessor input = new MyInputProcessor()
  {
    public boolean keyUp(int paramAnonymousInt)
    {
      if ((paramAnonymousInt == 4) || (paramAnonymousInt == 67) || (paramAnonymousInt == 131)) {
        Collision.this.game.setScreen(new MainMenu(Collision.this.game));
      }
      return false;
    }
  };
  protected int numBalls = 16;
  ShapeRenderer shapeRenderer = new ShapeRenderer();
  protected float spring = 0.03F;
  
  public Collision(MyGame paramMyGame)
  {
    super(paramMyGame);
  }
  
  protected void addBall()
  {
    this.balls.add(new Ball(MathUtils.random(this.width), MathUtils.random(this.height), MathUtils.random(this.width / 25.0F, this.width / 10.0F), this.balls.size, this.balls));
    ((Ball)this.balls.get(this.balls.size - 1)).setVelocity(MathUtils.random(-1.2F, 1.2F), MathUtils.random(-1.2F, 1.2F));
    ((Ball)this.balls.get(this.balls.size - 1)).changeColor();
    this.stage.addActor((Actor)this.balls.get(this.balls.size - 1));
  }
  
  public void dispose()
  {
    super.dispose();
    this.shapeRenderer.dispose();
  }
  
  protected void increaseCEnergyLevel(int paramInt)
  {
    this.cEnergyPointer += paramInt;
    if (this.cEnergyPointer > this.CEnergyLevel.length - 1) {
      this.cEnergyPointer = (this.CEnergyLevel.length - 1);
    }
    for (;;)
    {
      this.conservedEnergy = this.CEnergyLevel[this.cEnergyPointer];
      return;
      if (this.cEnergyPointer < 0) {
        this.cEnergyPointer = 0;
      }
    }
  }
  
  protected void increaseGravityLevel(int paramInt)
  {
    this.gravityPointer += paramInt;
    if (this.gravityPointer > this.GravityLevel.length - 1) {
      this.gravityPointer = (this.GravityLevel.length - 1);
    }
    for (;;)
    {
      this.gravity = this.GravityLevel[this.gravityPointer];
      return;
      if (this.gravityPointer < 0) {
        this.gravityPointer = 0;
      }
    }
  }
  
  protected void increaseHardnessLevel(int paramInt)
  {
    this.hardnessPointer += paramInt;
    if (this.hardnessPointer > this.HardnessLevel.length - 1) {
      this.hardnessPointer = (this.HardnessLevel.length - 1);
    }
    for (;;)
    {
      this.spring = this.HardnessLevel[this.hardnessPointer];
      return;
      if (this.hardnessPointer < 0) {
        this.hardnessPointer = 0;
      }
    }
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
  
  public void removeBall(Ball paramBall)
  {
    this.stage.getActors().removeValue(paramBall, true);
    Gdx.app.log("", this.balls.removeValue(paramBall, true));
  }
  
  public void render(float paramFloat)
  {
    Gdx.gl.glClear(16384);
    this.stage.act(paramFloat);
    this.stage.draw();
    this.stageui.act(paramFloat);
    this.stageui.draw();
    updateText();
    renderText();
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
      Assets.font24.drawMultiLine(this.batchui, (CharSequence)this.Text.get(i), 0.0F, this.height - this.BUTTON_HEIGHT / 3.0F - i * this.BUTTON_HEIGHT - this.BUTTON_HEIGHT, this.width - this.BUTTON_WIDTH * 2.0F, BitmapFont.HAlignment.RIGHT);
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
    this.testName = "Collision Test |";
    addBackButton();
    Object localObject = new InputMultiplexer(new InputProcessor[] { this.stageui, this.stage, this, this.input });
    Gdx.input.setInputProcessor((InputProcessor)localObject);
    Gdx.input.setCatchBackKey(true);
    this.gravityPointer = MathUtils.random(this.GravityLevel.length - 8, this.GravityLevel.length - 1);
    this.gravity = this.GravityLevel[this.gravityPointer];
    this.hardnessPointer = MathUtils.random(this.HardnessLevel.length - 1);
    this.spring = this.HardnessLevel[this.hardnessPointer];
    this.cEnergyPointer = 4;
    this.conservedEnergy = this.CEnergyLevel[this.cEnergyPointer];
    this.balls = new Array();
    int i = 0;
    if (i >= this.numBalls)
    {
      this.debugButton = new TextButton("[-] Gravity", this.skin);
      this.debugButton.addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          Collision.this.increaseGravityLevel(-1);
        }
      });
      this.buttons.add(this.debugButton);
      this.debugButton = new TextButton("[-] Hardness", this.skin);
      this.debugButton.addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          Collision.this.increaseHardnessLevel(-1);
        }
      });
      this.buttons.add(this.debugButton);
      this.debugButton = new TextButton("[-] Con. Energy", this.skin);
      this.debugButton.addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          Collision.this.increaseCEnergyLevel(-1);
        }
      });
      this.buttons.add(this.debugButton);
      this.debugButton = new TextButton("[+] Gravity", this.skin);
      this.debugButton.addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          Collision.this.increaseGravityLevel(1);
        }
      });
      this.buttons.add(this.debugButton);
      this.debugButton = new TextButton("[+] Hardness", this.skin);
      this.debugButton.addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          Collision.this.increaseHardnessLevel(1);
        }
      });
      this.buttons.add(this.debugButton);
      this.debugButton = new TextButton("[+] Con. Energy", this.skin);
      this.debugButton.addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          Collision.this.increaseCEnergyLevel(1);
        }
      });
      this.buttons.add(this.debugButton);
      this.debugButton = new TextButton("Add Ball", this.skin);
      this.debugButton.addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          if (Collision.this.balls.size < 60) {
            Collision.this.addBall();
          }
        }
      });
      this.buttons.add(this.debugButton);
      localObject = this.buttons.iterator();
    }
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        return;
        this.balls.add(new Ball(MathUtils.random(this.width), MathUtils.random(this.height), MathUtils.random(this.width / 25.0F, this.width / 10.0F), i, this.balls));
        ((Ball)this.balls.get(i)).setVelocity(MathUtils.random(-1.2F, 1.2F), MathUtils.random(-1.2F, 1.2F));
        ((Ball)this.balls.get(i)).changeColor();
        this.stage.addActor((Actor)this.balls.get(i));
        i += 1;
        break;
      }
      TextButton localTextButton = (TextButton)((Iterator)localObject).next();
      this.stageui.addActor(localTextButton);
      localTextButton.setHeight(this.BUTTON_HEIGHT);
      localTextButton.setWidth(this.BUTTON_WIDTH);
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
    if (this.gravity != 0.0F) {
      this.Text.add(-this.gravity + " |");
    }
    for (;;)
    {
      this.Text.add(this.spring + " |");
      this.Text.add(this.conservedEnergy + " |");
      return;
      this.Text.add(this.gravity + " |");
    }
  }
  
  public class Ball
    extends Actor
  {
    Color color = Color.WHITE;
    public float diameter;
    int id;
    Array<Ball> others;
    Color tempColor;
    float vx = 0.0F;
    float vy = 0.0F;
    
    public Ball(float paramFloat1, float paramFloat2, int paramInt, Array<Ball> paramArray)
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
        public void fling(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt)
        {
          Collision.Ball.this.Fling(paramAnonymousFloat1, paramAnonymousFloat2);
        }
        
        public void tap(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt1, int paramAnonymousInt2)
        {
          Collision.Ball.this.changeColor();
        }
      });
      setTouchable(Touchable.enabled);
    }
    
    public void Fling(float paramFloat1, float paramFloat2)
    {
      this.vx = (paramFloat1 * 0.01F);
      this.vy = (paramFloat2 * 0.01F);
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
    
    public void changeColor()
    {
      this.tempColor = new Color(MathUtils.random(0.0F, 1.0F), MathUtils.random(0.0F, 1.0F), MathUtils.random(0.0F, 1.0F), MathUtils.random(0.0F, 1.0F));
      addAction(Actions.color(this.tempColor, 1.4F, Interpolation.swingOut));
    }
    
    void collide(float paramFloat)
    {
      int i = this.id + 1;
      for (;;)
      {
        if (i >= Collision.this.balls.size) {
          return;
        }
        paramFloat = ((Ball)this.others.get(i)).getXOffset() - getXOffset();
        float f1 = ((Ball)this.others.get(i)).getYOffset() - getYOffset();
        double d1 = Math.sqrt(paramFloat * paramFloat + f1 * f1);
        float f2 = ((Ball)this.others.get(i)).diameter / 2.0F + this.diameter / 2.0F;
        if (d1 < f2)
        {
          this.vx *= Collision.this.conservedEnergy;
          this.vy *= Collision.this.conservedEnergy;
          Ball localBall = (Ball)this.others.get(i);
          localBall.vx *= Collision.this.conservedEnergy;
          localBall = (Ball)this.others.get(i);
          localBall.vy *= Collision.this.conservedEnergy;
          double d5 = Math.atan2(f1, paramFloat);
          double d2 = getXOffset();
          double d3 = Math.cos(d5);
          double d4 = f2;
          d1 = getYOffset();
          d5 = Math.sin(d5);
          double d6 = f2;
          d2 = (d2 + d3 * d4 - ((Ball)this.others.get(i)).getXOffset()) * Collision.this.spring;
          d1 = (d1 + d5 * d6 - ((Ball)this.others.get(i)).getYOffset()) * Collision.this.spring;
          this.vx = ((float)(this.vx - d2));
          this.vy = ((float)(this.vy - d1));
          localBall = (Ball)this.others.get(i);
          localBall.vx = ((float)(localBall.vx + d2));
          localBall = (Ball)this.others.get(i);
          localBall.vy = ((float)(localBall.vy + d1));
        }
        i += 1;
      }
    }
    
    public void draw(Batch paramBatch, float paramFloat)
    {
      Collision.this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
      Collision.this.shapeRenderer.setProjectionMatrix(paramBatch.getProjectionMatrix());
      Collision.this.shapeRenderer.setTransformMatrix(paramBatch.getTransformMatrix());
      Collision.this.shapeRenderer.scale(getScaleX(), getScaleY(), 0.0F);
      Collision.this.shapeRenderer.setColor(getColor());
      Collision.this.shapeRenderer.circle(getXOffset(), getYOffset(), this.diameter / 2.0F);
      Collision.this.shapeRenderer.end();
      Collision.this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
      Collision.this.shapeRenderer.setProjectionMatrix(paramBatch.getProjectionMatrix());
      Collision.this.shapeRenderer.setTransformMatrix(paramBatch.getTransformMatrix());
      Collision.this.shapeRenderer.scale(getScaleX(), getScaleY(), 0.0F);
      Collision.this.shapeRenderer.setColor(Color.BLACK);
      Collision.this.shapeRenderer.circle(getXOffset(), getYOffset(), this.diameter / 2.0F);
      Collision.this.shapeRenderer.end();
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
      this.vy += Collision.this.gravity;
      addX(this.vx);
      addY(this.vy);
      if (getXOffset() + this.diameter / 2.0F > Collision.this.width)
      {
        setX(Collision.this.width - this.diameter);
        this.vx *= Collision.this.friction;
      }
      do
      {
        while (getYOffset() + this.diameter / 2.0F > Collision.this.height)
        {
          setY(Collision.this.height - this.diameter);
          this.vy *= Collision.this.friction;
          return;
          if (getXOffset() - this.diameter / 2.0F < 0.0F)
          {
            setX(0.0F);
            this.vx *= Collision.this.friction;
          }
        }
      } while (getYOffset() - this.diameter / 2.0F >= 0.0F);
      setY(0.0F);
      this.vy *= Collision.this.friction;
    }
    
    public void removeBall(Ball paramBall)
    {
      Gdx.app.log("", this.others.removeValue(paramBall, true));
      int i = 0;
      for (;;)
      {
        if (i >= this.others.size) {
          return;
        }
        if (this.others.get(i) == paramBall) {
          Gdx.app.log("", this.others.removeValue((Ball)this.others.get(i), true));
        }
        i += 1;
      }
    }
    
    public void setDiameter(float paramFloat)
    {
      this.diameter = paramFloat;
    }
    
    public void setVelocity(float paramFloat1, float paramFloat2)
    {
      this.vx = paramFloat1;
      this.vy = paramFloat2;
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\tests\Collision.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */