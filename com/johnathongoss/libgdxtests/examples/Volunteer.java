package com.johnathongoss.libgdxtests.examples;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.johnathongoss.libgdxtests.Assets;
import com.johnathongoss.libgdxtests.EffectsCache;
import com.johnathongoss.libgdxtests.MyGame;
import com.johnathongoss.libgdxtests.MyGame.CameraShaker;
import com.johnathongoss.libgdxtests.MyInputProcessor;
import com.johnathongoss.libgdxtests.Sounds;
import com.johnathongoss.libgdxtests.Sounds.SoundPointer;
import com.johnathongoss.libgdxtests.entities.DamageImage;
import com.johnathongoss.libgdxtests.entities.Effect;
import com.johnathongoss.libgdxtests.entities.SpeechBubble;
import com.johnathongoss.libgdxtests.screens.Examples;
import java.util.Iterator;

public class Volunteer
  implements Screen
{
  private Array<ParticleEffectPool.PooledEffect> Effects;
  private TextButton backButton;
  SpriteBatch batch;
  OrthographicCamera cam;
  int damage = -1;
  MyGame game;
  MyInputProcessor input = new MyInputProcessor()
  {
    public boolean keyUp(int paramAnonymousInt)
    {
      if ((paramAnonymousInt == 4) || (paramAnonymousInt == 67) || (paramAnonymousInt == 131)) {
        Volunteer.this.game.setScreen(new Examples(Volunteer.this.game));
      }
      return false;
    }
    
    public boolean touchDown(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
    {
      Volunteer.this.attackWithWeapon();
      return false;
    }
  };
  Jeremy jeremy;
  private final Pool<SpeechBubble> speechBubblePool = new Pool()
  {
    protected SpeechBubble newObject()
    {
      return new SpeechBubble();
    }
  };
  Stage stage;
  Stage stageui;
  private int weaponIndex = 0;
  String[] weaponNames = { "Sword", "Heal" };
  
  public Volunteer(MyGame paramMyGame)
  {
    this.game = paramMyGame;
    this.cam = new OrthographicCamera();
    this.stage = new Stage();
    this.stageui = new Stage();
    this.batch = new SpriteBatch();
    this.Effects = new Array();
    this.jeremy = new Jeremy();
    this.jeremy.setPosition(paramMyGame.getWidth() / 2.0F, paramMyGame.getHeight() / 2.0F);
  }
  
  private void checkDamageImages()
  {
    Iterator localIterator = this.stage.getActors().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      Actor localActor = (Actor)localIterator.next();
      if (((localActor instanceof DamageImage)) && (!((DamageImage)localActor).isAlive()))
      {
        this.stage.getActors().removeValue(localActor, true);
        EffectsCache.free((DamageImage)localActor);
      }
    }
  }
  
  private void checkSpeechBubbles()
  {
    Iterator localIterator = this.stageui.getActors().iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      Actor localActor = (Actor)localIterator.next();
      if (((localActor instanceof SpeechBubble)) && (!((SpeechBubble)localActor).isAlive()))
      {
        this.stageui.getActors().removeValue(localActor, true);
        this.speechBubblePool.free((SpeechBubble)localActor);
      }
    }
  }
  
  protected void addTesterSpeech()
  {
    SpeechBubble localSpeechBubble = (SpeechBubble)this.speechBubblePool.obtain();
    localSpeechBubble.setColor(new Color(0.5F, 0.5F, 0.5F, 1.0F));
    switch (this.weaponIndex)
    {
    }
    for (;;)
    {
      this.stageui.addActor(localSpeechBubble);
      return;
      localSpeechBubble.init("This might hurt a bit mate.", this.game.getWidth(), 0.0F);
      continue;
      localSpeechBubble.init("Hang in there Jeremy!", this.game.getWidth(), 0.0F);
    }
  }
  
  public void attackWithWeapon()
  {
    switch (this.weaponIndex)
    {
    default: 
      return;
    case 0: 
      this.stage.addActor(new Sword(this.jeremy, MathUtils.random(-2, -1)));
      return;
    }
    this.stage.addActor(new Heal(this.jeremy, MathUtils.random(1, 2)));
  }
  
  public void dispose()
  {
    this.stage.dispose();
    this.stageui.dispose();
    this.batch.dispose();
  }
  
  public void hide()
  {
    dispose();
  }
  
  public void pause() {}
  
  public void render(float paramFloat)
  {
    Gdx.gl.glClear(16384);
    checkSpeechBubbles();
    Iterator localIterator = this.stage.getActors().iterator();
    if (!localIterator.hasNext())
    {
      checkDamageImages();
      this.batch.begin();
      localIterator = this.Effects.iterator();
    }
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        this.batch.end();
        this.stage.act(paramFloat);
        this.stage.draw();
        this.stageui.act(paramFloat);
        this.stageui.draw();
        this.game.CameraShaker.update(paramFloat);
        return;
        localObject = (Actor)localIterator.next();
        if ((!(localObject instanceof Effect)) || (((Effect)localObject).isAlive())) {
          break;
        }
        this.stage.getActors().removeValue(localObject, true);
        break;
      }
      Object localObject = (ParticleEffectPool.PooledEffect)localIterator.next();
      ((ParticleEffectPool.PooledEffect)localObject).draw(this.batch, paramFloat);
      if (((ParticleEffectPool.PooledEffect)localObject).isComplete())
      {
        this.Effects.removeValue(localObject, true);
        ((ParticleEffectPool.PooledEffect)localObject).reset();
        ((ParticleEffectPool.PooledEffect)localObject).free();
      }
    }
  }
  
  public void resize(int paramInt1, int paramInt2)
  {
    this.backButton.setBounds(0.0F, paramInt2 - this.game.getButtonHeight() * 2.0F, this.game.getButtonWidth(), this.game.getButtonHeight());
  }
  
  public void resume() {}
  
  public void show()
  {
    this.game.showAds(true);
    final Object localObject = new InputMultiplexer(new InputProcessor[] { this.stage, this.stageui, this.input });
    Gdx.input.setInputProcessor((InputProcessor)localObject);
    Gdx.input.setCatchBackKey(true);
    this.cam.setToOrtho(false, this.game.getWidth(), this.game.getHeight());
    this.cam.update();
    this.stage.setCamera(this.cam);
    this.cam.zoom = 1.0F;
    this.stage.addActor(this.jeremy);
    this.backButton = new TextButton("Back", Assets.skin);
    this.backButton.setBounds(0.0F, this.game.getHeight() - this.game.getButtonHeight() * 2.0F, this.game.getButtonWidth(), this.game.getButtonHeight());
    this.backButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        Volunteer.this.game.setScreen(new Examples(Volunteer.this.game));
      }
    });
    this.stageui.addActor(this.backButton);
    localObject = new TextButton(this.weaponNames[this.weaponIndex], Assets.skin);
    ((TextButton)localObject).setBounds(this.game.getWidth() - this.game.getButtonWidth(), this.game.getHeight() - this.game.getButtonHeight() * 2.0F, this.game.getButtonWidth(), this.game.getButtonHeight());
    ((TextButton)localObject).addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        paramAnonymousInputEvent = Volunteer.this;
        paramAnonymousInputEvent.weaponIndex += 1;
        if (Volunteer.this.weaponIndex >= Volunteer.this.weaponNames.length) {
          Volunteer.this.weaponIndex = 0;
        }
        localObject.setText(Volunteer.this.weaponNames[Volunteer.this.weaponIndex]);
        Volunteer.this.addTesterSpeech();
      }
    });
    this.stageui.addActor((Actor)localObject);
    localObject = new TextButton("Zoom: " + this.cam.zoom, Assets.skin);
    ((TextButton)localObject).setBounds(this.game.getWidth() - this.game.getButtonWidth(), this.game.getHeight() - this.game.getButtonHeight() * 3.0F, this.game.getButtonWidth(), this.game.getButtonHeight());
    ((TextButton)localObject).addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        if (Volunteer.this.cam.zoom == 0.4F) {
          Volunteer.this.cam.zoom = 1.0F;
        }
        for (;;)
        {
          localObject.setText("Zoom: " + Volunteer.this.cam.zoom);
          return;
          if (Volunteer.this.cam.zoom == 1.0F) {
            Volunteer.this.cam.zoom = 0.4F;
          }
        }
      }
    });
    this.stageui.addActor((Actor)localObject);
    addTesterSpeech();
  }
  
  class Heal
    extends Volunteer.WeaponEffect
  {
    Heal(Volunteer.Jeremy paramJeremy, int paramInt)
    {
      super();
      this.alive = true;
      this.color = new Color(0.0F, 1.0F, 0.5F, 1.0F);
      this.damage = paramInt;
      this.actor = paramJeremy;
      this.animation = new Animation(0.1F, Assets.spriteSheet.createSprites("fx_sparkle"), 0);
    }
    
    protected void perform()
    {
      DamageImage localDamageImage = EffectsCache.getDamageEffect();
      localDamageImage.init(this.damage, this.actor.getX() + this.actor.getWidth() / 2.0F, this.actor.getY() + this.actor.getHeight() / 3.0F);
      Volunteer.this.stage.addActor(localDamageImage);
      this.actor.addHP(this.damage);
      Sounds.PlaySound(Sounds.SoundPointer.HEAL_01);
    }
  }
  
  class Jeremy
    extends Actor
  {
    private Animation animation = new Animation(1.0F, Assets.spriteSheet.createSprites("man_die"));
    private TextureRegion currentFrame = this.animation.getKeyFrame(0.0F);
    private int hp_current = 30;
    private int hp_max = 30;
    
    public Jeremy()
    {
      setBounds(0.0F, 0.0F, this.currentFrame.getRegionWidth(), this.currentFrame.getRegionHeight());
    }
    
    public void act(float paramFloat)
    {
      super.act(paramFloat);
      if (this.hp_current <= 3)
      {
        this.currentFrame = this.animation.getKeyFrame(5.0F);
        return;
      }
      if (this.hp_current <= 8)
      {
        this.currentFrame = this.animation.getKeyFrame(4.0F);
        return;
      }
      if (this.hp_current <= 15)
      {
        this.currentFrame = this.animation.getKeyFrame(3.0F);
        return;
      }
      if (this.hp_current <= 22)
      {
        this.currentFrame = this.animation.getKeyFrame(2.0F);
        return;
      }
      if (this.hp_current <= 26)
      {
        this.currentFrame = this.animation.getKeyFrame(1.0F);
        return;
      }
      this.currentFrame = this.animation.getKeyFrame(0.0F);
    }
    
    public void addHP(int paramInt)
    {
      this.hp_current += paramInt;
      if (this.hp_current > this.hp_max) {
        this.hp_current = this.hp_max;
      }
      if (this.hp_current < 0) {
        this.hp_current = 0;
      }
    }
    
    public void draw(Batch paramBatch, float paramFloat)
    {
      paramBatch.draw(this.currentFrame, getX(), getY());
      Assets.font24.drawMultiLine(paramBatch, this.hp_current + "/" + this.hp_max, getX(), getY(), getWidth(), BitmapFont.HAlignment.CENTER);
    }
    
    public void setPosition(float paramFloat1, float paramFloat2)
    {
      super.setPosition(paramFloat1, paramFloat2);
    }
  }
  
  class Sword
    extends Volunteer.WeaponEffect
  {
    Sword(Volunteer.Jeremy paramJeremy, int paramInt)
    {
      super();
      this.alive = true;
      this.damage = paramInt;
      this.actor = paramJeremy;
      this.animation = new Animation(0.05F, Assets.spriteSheet.createSprites("fx_slash"), 0);
      Sounds.PlaySound(Sounds.SoundPointer.SLASH_01);
    }
    
    protected void perform()
    {
      DamageImage localDamageImage = EffectsCache.getDamageEffect();
      localDamageImage.init(this.damage, this.actor.getX() + this.actor.getWidth() / 2.0F, this.actor.getY() + this.actor.getHeight() / 3.0F);
      Volunteer.this.stage.addActor(localDamageImage);
      this.actor.addHP(this.damage);
      Sounds.PlaySound(Sounds.SoundPointer.HIT_01);
      Volunteer.this.game.CameraShaker.Shake(Volunteer.this.cam, 0.1F, 0.3F);
    }
  }
  
  abstract class WeaponEffect
    extends Actor
  {
    protected Volunteer.Jeremy actor;
    protected boolean alive = false;
    protected Animation animation;
    protected Color color = Color.WHITE;
    private TextureRegion currentFrame;
    protected int damage;
    private float time = 0.0F;
    
    WeaponEffect() {}
    
    public void act(float paramFloat)
    {
      super.act(paramFloat);
      this.time += paramFloat;
      this.currentFrame = this.animation.getKeyFrame(this.time);
      if ((this.alive) && (this.animation.isAnimationFinished(this.time)))
      {
        perform();
        this.alive = false;
      }
    }
    
    public void draw(Batch paramBatch, float paramFloat)
    {
      paramBatch.setColor(this.color);
      if (this.alive) {
        paramBatch.draw(this.currentFrame, this.actor.getX(), this.actor.getY());
      }
      paramBatch.setColor(Color.WHITE);
    }
    
    public boolean isAlive()
    {
      return this.alive;
    }
    
    protected abstract void perform();
    
    public void setActor(Volunteer.Jeremy paramJeremy)
    {
      this.actor = paramJeremy;
    }
    
    public void setAnimation(Animation paramAnimation)
    {
      this.animation = paramAnimation;
    }
    
    public void setDamage(int paramInt)
    {
      this.damage = paramInt;
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\examples\Volunteer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */