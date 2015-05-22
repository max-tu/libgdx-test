package com.johnathongoss.libgdxtests.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.johnathongoss.libgdxtests.AppData.Prefs;
import com.johnathongoss.libgdxtests.Assets;
import com.johnathongoss.libgdxtests.MyGame;
import com.johnathongoss.libgdxtests.MyInputProcessor;
import com.johnathongoss.libgdxtests.ParticleCache;
import com.johnathongoss.libgdxtests.screens.MainMenu;
import java.util.Iterator;

public class Particles
  extends BlankTestScreen
{
  private Array<ParticleEffectPool.PooledEffect> Effects;
  private String[] Names = { "Fire", "Frost", "Blood", "Bubbles", "Spark", "Firework" };
  private boolean continuous = false;
  protected int index = 0;
  MyInputProcessor input = new MyInputProcessor()
  {
    public boolean keyUp(int paramAnonymousInt)
    {
      if ((paramAnonymousInt == 4) || (paramAnonymousInt == 67) || (paramAnonymousInt == 131)) {
        Particles.this.game.setScreen(new MainMenu(Particles.this.game));
      }
      return false;
    }
  };
  private TextButton limitButton;
  private boolean limitReached = false;
  protected int noParticles = 5;
  private TextButton switchButton;
  
  public Particles(MyGame paramMyGame)
  {
    super(paramMyGame);
    this.testName = "Particles Test |";
  }
  
  protected void clearParticles()
  {
    this.Effects.clear();
    Iterator localIterator = this.Effects.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      ParticleEffectPool.PooledEffect localPooledEffect = (ParticleEffectPool.PooledEffect)localIterator.next();
      localPooledEffect.reset();
      localPooledEffect.free();
    }
  }
  
  public void createParticle(int paramInt, float paramFloat1, float paramFloat2)
  {
    if ((AppData.Prefs.isLimitParticles()) && (this.Effects.size > 19))
    {
      this.limitReached = true;
      return;
    }
    if (!this.continuous)
    {
      this.Effects.add(ParticleCache.getParticleEffect(paramInt));
      paramInt = 0;
      if (paramInt < ((ParticleEffectPool.PooledEffect)this.Effects.get(this.Effects.size - 1)).getEmitters().size) {}
    }
    for (;;)
    {
      ((ParticleEffectPool.PooledEffect)this.Effects.get(this.Effects.size - 1)).setPosition(paramFloat1, this.height - paramFloat2);
      return;
      ((ParticleEmitter)((ParticleEffectPool.PooledEffect)this.Effects.get(this.Effects.size - 1)).getEmitters().get(paramInt)).setContinuous(false);
      paramInt += 1;
      break;
      if (this.continuous)
      {
        this.Effects.add(ParticleCache.getParticleEffect(paramInt));
        paramInt = 0;
        while (paramInt < ((ParticleEffectPool.PooledEffect)this.Effects.get(this.Effects.size - 1)).getEmitters().size)
        {
          ((ParticleEmitter)((ParticleEffectPool.PooledEffect)this.Effects.get(this.Effects.size - 1)).getEmitters().get(paramInt)).setContinuous(true);
          paramInt += 1;
        }
      }
    }
  }
  
  public void dispose()
  {
    super.dispose();
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
  
  public void render(float paramFloat)
  {
    Gdx.gl.glClear(16384);
    if (this.Effects.size < 5) {
      this.limitReached = false;
    }
    this.stage.act();
    this.stage.draw();
    this.batch.setProjectionMatrix(this.cam.combined);
    this.batch.begin();
    Iterator localIterator = this.Effects.iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        renderTestName(this.batch);
        this.batch.end();
        updateText();
        this.batchui.begin();
        renderText();
        this.batchui.end();
        this.stageui.act();
        this.stageui.draw();
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
  
  protected void renderText()
  {
    int i = 0;
    for (;;)
    {
      if (i >= this.Text.size) {
        return;
      }
      Assets.font24.drawMultiLine(this.batchui, (CharSequence)this.Text.get(i), 0.0F, this.height - this.BUTTON_HEIGHT * 3.0F - i * 24 - this.BUTTON_HEIGHT, this.width, BitmapFont.HAlignment.RIGHT);
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
    addBackButton();
    Object localObject = new InputMultiplexer(new InputProcessor[] { this.stageui, this.stage, this, this.input });
    Gdx.input.setInputProcessor((InputProcessor)localObject);
    Gdx.input.setCatchBackKey(true);
    this.switchButton = new TextButton(this.Names[this.index], this.skin);
    this.switchButton.setHeight(this.BUTTON_HEIGHT);
    this.switchButton.setWidth(this.BUTTON_WIDTH);
    this.switchButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        paramAnonymousInputEvent = Particles.this;
        paramAnonymousInputEvent.index += 1;
        if (Particles.this.index > Particles.this.noParticles) {
          Particles.this.index = 0;
        }
        Particles.this.switchButton.setText(Particles.this.Names[Particles.this.index]);
      }
    });
    this.switchButton.setPosition(this.width - this.BUTTON_WIDTH, this.height - this.BUTTON_HEIGHT * 2.0F);
    this.stageui.addActor(this.switchButton);
    this.limitButton = new TextButton("Cont.: " + this.continuous, this.skin);
    this.limitButton.setHeight(this.BUTTON_HEIGHT);
    this.limitButton.setWidth(this.BUTTON_WIDTH);
    this.limitButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        paramAnonymousInputEvent = Particles.this;
        if (Particles.this.continuous) {}
        for (boolean bool = false;; bool = true)
        {
          paramAnonymousInputEvent.continuous = bool;
          Particles.this.limitButton.setText("Cont.: " + Particles.this.continuous);
          return;
        }
      }
    });
    this.limitButton.setPosition(this.width - this.BUTTON_WIDTH, this.height - this.BUTTON_HEIGHT * 3.0F);
    this.stageui.addActor(this.limitButton);
    localObject = new TextButton("Clear", this.skin);
    ((TextButton)localObject).setHeight(this.BUTTON_HEIGHT);
    ((TextButton)localObject).setWidth(this.BUTTON_WIDTH);
    ((TextButton)localObject).addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        Particles.this.clearParticles();
      }
    });
    ((TextButton)localObject).setPosition(this.width - this.BUTTON_WIDTH, this.height - this.BUTTON_HEIGHT * 4.0F);
    this.stageui.addActor((Actor)localObject);
    this.Effects = new Array();
  }
  
  public boolean touchDown(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    createParticle(this.index, paramInt1, paramInt2);
    return false;
  }
  
  public boolean touchDragged(int paramInt1, int paramInt2, int paramInt3)
  {
    createParticle(this.index, paramInt1, paramInt2);
    return false;
  }
  
  public boolean touchUp(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return false;
  }
  
  protected void updateText()
  {
    this.Text.clear();
    this.Text.add("Count: " + this.Effects.size + " |");
    if (this.limitReached) {
      this.Text.add("Limit reached |");
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\tests\Particles.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */