package com.johnathongoss.libgdxtests.entities;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.johnathongoss.libgdxtests.Assets;

public abstract class TextBox
  extends Actor
  implements Pool.Poolable
{
  protected boolean alive = false;
  protected NinePatch background;
  protected boolean follow = false;
  protected Actor followed;
  protected BitmapFont font = Assets.font24;
  protected MyTimer life;
  protected float padX = 10.0F;
  protected float padY = 20.0F;
  protected float readSpeed = 0.07F;
  protected String text;
  protected float wrapWidth;
  protected float yStretch;
  
  public void act(float paramFloat)
  {
    super.act(paramFloat);
    this.life.update(paramFloat);
    checkBounds();
  }
  
  abstract void animate();
  
  protected void checkBounds()
  {
    if (this.follow)
    {
      setX(this.followed.getX() - getWidth() / 2.0F);
      setY(this.followed.getY() + this.followed.getHeight());
    }
    if (getX() + getWidth() > Gdx.app.getGraphics().getWidth()) {
      setX(getX() - (getWidth() - (Gdx.app.getGraphics().getWidth() - getX())));
    }
    if (getY() + getHeight() > Gdx.app.getGraphics().getHeight()) {
      setY(getY() - (getHeight() - (Gdx.app.getGraphics().getHeight() - getY())));
    }
    if (getX() < 0.0F) {
      setX(0.0F);
    }
    if (getY() < 0.0F) {
      setY(0.0F);
    }
  }
  
  public void init(String paramString, float paramFloat1, float paramFloat2)
  {
    setUp(paramString, paramFloat1, paramFloat2);
    animate();
  }
  
  public boolean isAlive()
  {
    return this.alive;
  }
  
  public void reset()
  {
    this.alive = false;
    setX(0.0F);
    setY(0.0F);
    setWidth(0.0F);
    setHeight(0.0F);
    this.wrapWidth = 0.0F;
    this.text = "";
    this.life.reset();
    this.life.pause();
    this.follow = false;
    this.followed = null;
  }
  
  public void setColor(Color paramColor)
  {
    this.background.setColor(paramColor);
  }
  
  public void setFollow(Actor paramActor)
  {
    this.follow = true;
    this.followed = paramActor;
    checkBounds();
  }
  
  public void setFont(BitmapFont paramBitmapFont)
  {
    this.font = paramBitmapFont;
  }
  
  public void setLife(float paramFloat)
  {
    this.life = new MyTimer(paramFloat)
    {
      protected void perform()
      {
        TextBox.this.alive = false;
      }
    };
    this.life.reset();
    this.life.start();
  }
  
  public void setPadding(float paramFloat1, float paramFloat2)
  {
    this.padX = paramFloat1;
    this.padY = paramFloat2;
  }
  
  protected void setUp(String paramString, float paramFloat1, float paramFloat2)
  {
    this.alive = true;
    this.text = paramString;
    float f2 = this.font.getBounds(paramString).width + this.padX * 2.0F;
    float f3 = this.font.getBounds(paramString).height + this.padY * 2.0F;
    float f1 = f2;
    if (f2 < 50.0F) {
      f1 = 50.0F;
    }
    this.yStretch = ((float)Math.ceil(0.5F + f1 / (Gdx.app.getGraphics().getWidth() / 2.0F)));
    f2 = f1;
    if (f1 > Gdx.app.getGraphics().getWidth() / 2)
    {
      f2 = Gdx.app.getGraphics().getWidth() / 2 + this.padX * 2.0F;
      f3 = this.font.getBounds(paramString).height * this.yStretch + this.padY * this.yStretch;
    }
    setWidth(f2);
    setHeight(f3);
    this.wrapWidth = (getWidth() - this.padX * 2.0F);
    setOrigin(getWidth() / 2.0F, getHeight() / 2.0F);
    setX(paramFloat1 - getWidth() / 2.0F);
    setY(paramFloat2);
    setLife(0.6F + paramString.length() * this.readSpeed);
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\entities\TextBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */