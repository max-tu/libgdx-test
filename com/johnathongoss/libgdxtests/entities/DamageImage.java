package com.johnathongoss.libgdxtests.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.johnathongoss.libgdxtests.Assets;
import com.johnathongoss.libgdxtests.ImageCache;

public class DamageImage
  extends Effect
{
  String damage;
  BitmapFont font = Assets.font32;
  TextureRegion image = ImageCache.getTexture("damage_icon");
  Color imageColor = new Color(0.8F, 0.3F, 0.0F, 1.0F);
  Timer timer = new Timer();
  
  public void act(float paramFloat)
  {
    super.act(paramFloat);
    if (getColor().a == 0.0F) {
      this.alive = false;
    }
  }
  
  public void draw(Batch paramBatch, float paramFloat)
  {
    paramBatch.setColor(getColor());
    paramBatch.draw(this.image, getX() - this.image.getRegionWidth() / 2, getY() - this.image.getRegionHeight() / 2, getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    this.font.setColor(1.0F, 1.0F, 1.0F, getColor().a);
    BitmapFont localBitmapFont = this.font;
    String str = this.damage;
    paramFloat = getX();
    float f = getY();
    localBitmapFont.drawMultiLine(paramBatch, str, paramFloat, this.font.getLineHeight() / 2.0F + f, 0.0F, BitmapFont.HAlignment.CENTER);
    this.font.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    paramBatch.setColor(1.0F, 1.0F, 1.0F, 1.0F);
  }
  
  public void init(int paramInt, float paramFloat1, float paramFloat2)
  {
    this.alive = true;
    if (paramInt > 0)
    {
      this.damage = ("+" + paramInt);
      setColor(0.0F, 1.0F, 0.5F, 1.0F);
      this.image = ImageCache.getTexture("heal_icon");
    }
    for (;;)
    {
      this.timer.scheduleTask(new Timer.Task()
      {
        public void run()
        {
          DamageImage.this.kill();
        }
      }, 0.8F);
      this.timer.start();
      setX(paramFloat1);
      setY(paramFloat2);
      setWidth(this.image.getRegionWidth());
      setHeight(this.image.getRegionHeight());
      setOrigin(getWidth() / 2.0F, getHeight() / 2.0F);
      addAction(Actions.sequence(Actions.scaleTo(0.25F, 0.25F, 0.0F, Interpolation.elasticIn), Actions.scaleTo(1.0F, 1.0F, 0.8F, Interpolation.elasticOut)));
      return;
      if (paramInt < 0)
      {
        this.damage = paramInt;
        setColor(1.0F, 0.1F, 0.1F, 1.0F);
      }
      else
      {
        this.damage = paramInt;
        setColor(1.0F, 1.0F, 1.0F, 1.0F);
      }
    }
  }
  
  protected void kill()
  {
    addAction(Actions.fadeOut(1.0F));
  }
  
  public void reset()
  {
    this.timer = new Timer();
    this.alive = false;
    setColor(1.0F, 1.0F, 1.0F, 1.0F);
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\entities\DamageImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */