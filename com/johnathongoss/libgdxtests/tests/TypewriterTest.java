package com.johnathongoss.libgdxtests.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.johnathongoss.libgdxtests.Assets;
import net.dermetfan.utils.Appender;
import net.dermetfan.utils.libgdx.CharSequenceInterpolator;
import net.dermetfan.utils.libgdx.Typewriter;

public class TypewriterTest
  implements Screen
{
  private SpriteBatch batch;
  private BitmapFont font;
  private String text = "This is a long multiline string. Some text that you can read through in a RPG, that scrolls like in the pokemon games or something";
  private Typewriter typewriter = new Typewriter();
  
  public void dispose()
  {
    this.batch.dispose();
    this.font.dispose();
  }
  
  public void hide()
  {
    dispose();
  }
  
  public void pause() {}
  
  public void render(float paramFloat)
  {
    Gdx.gl.glClear(16384);
    this.batch.begin();
    this.font.drawWrapped(this.batch, this.typewriter.updateAndType(this.text, paramFloat), 0.0F, Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
    this.batch.end();
  }
  
  public void resize(int paramInt1, int paramInt2) {}
  
  public void resume() {}
  
  public void show()
  {
    this.batch = new SpriteBatch();
    this.font = Assets.font24;
    this.typewriter.getInterpolator().setInterpolation(Interpolation.linear);
    this.typewriter.getAppender().set(new CharSequence[] { "", ".", "..", "..." }, 0.375F);
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\tests\TypewriterTest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */