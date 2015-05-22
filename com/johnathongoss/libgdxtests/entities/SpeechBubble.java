package com.johnathongoss.libgdxtests.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.johnathongoss.libgdxtests.ImageCache;

public class SpeechBubble
  extends TextBox
{
  private Sprite pic;
  private float picOffset;
  private boolean usePic = false;
  
  public SpeechBubble()
  {
    this.background = ImageCache.CreatePatch("bubble");
  }
  
  private void addPic(TextureRegion paramTextureRegion)
  {
    this.usePic = true;
    this.pic = new Sprite(paramTextureRegion);
    this.pic.setSize(80.0F, 80.0F);
    if (getHeight() < 100.0F) {
      setHeight(100.0F);
    }
    this.picOffset = (getHeight() - 80.0F - this.padX);
    setWidth(getWidth() + this.pic.getWidth() + this.padX);
    animate();
  }
  
  void animate()
  {
    addAction(Actions.sequence(Actions.sizeTo(getWidth(), getHeight() * 0.75F), Actions.sizeTo(getWidth(), getHeight(), 0.2F, Interpolation.bounceOut)));
  }
  
  public void draw(Batch paramBatch, float paramFloat)
  {
    this.background.draw(paramBatch, getX(), getY(), getWidth(), getHeight());
    if (!this.usePic)
    {
      localObject = this.font;
      str = this.text;
      paramFloat = getX();
      ((BitmapFont)localObject).drawWrapped(paramBatch, str, this.padX + paramFloat, getY() + getHeight() - this.padY, this.wrapWidth);
      return;
    }
    Object localObject = this.pic;
    paramFloat = getX();
    float f1 = this.padX;
    float f2 = getY();
    paramBatch.draw((TextureRegion)localObject, f1 + paramFloat, this.picOffset + f2, this.pic.getWidth(), this.pic.getHeight());
    localObject = this.font;
    String str = this.text;
    paramFloat = getX();
    f1 = this.padX;
    ((BitmapFont)localObject).drawWrapped(paramBatch, str, this.pic.getWidth() + (paramFloat + f1 * 2.0F), getY() + getHeight() - this.padY, this.wrapWidth);
  }
  
  public void init(String paramString, float paramFloat1, float paramFloat2)
  {
    setUp(paramString, paramFloat1, paramFloat2);
    animate();
  }
  
  public void init(String paramString, float paramFloat1, float paramFloat2, TextureRegion paramTextureRegion)
  {
    setUp(paramString, paramFloat1, paramFloat2);
    addPic(paramTextureRegion);
  }
  
  public void reset()
  {
    super.reset();
    this.usePic = false;
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\entities\SpeechBubble.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */