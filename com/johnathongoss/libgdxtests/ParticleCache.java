package com.johnathongoss.libgdxtests;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;

public class ParticleCache
{
  public static final int BLOOD = 2;
  public static final int BUBBLES = 3;
  public static final int EXPL_FIRE = 4;
  public static final int FIRE = 0;
  public static final int FIREWORK_LARGE = 5;
  public static final int SNOW = 1;
  static ParticleEffectPool.PooledEffect effect;
  private static ParticleEffect fx_blood;
  private static ParticleEffect fx_bubbles;
  private static ParticleEffect fx_expl_fire;
  private static ParticleEffect fx_fire;
  private static ParticleEffect fx_firework_large;
  private static ParticleEffect fx_snow;
  private static ParticleEffectPool pool_blood;
  private static ParticleEffectPool pool_bubbles;
  private static ParticleEffectPool pool_expl_fire;
  private static ParticleEffectPool pool_fire;
  private static ParticleEffectPool pool_firework_large;
  private static ParticleEffectPool pool_snow;
  
  public static void Load()
  {
    fx_fire = new ParticleEffect();
    fx_fire.load(Gdx.files.internal("fx/fire.p"), Gdx.files.internal("fx"));
    pool_fire = new ParticleEffectPool(fx_fire, 0, 150);
    fx_snow = new ParticleEffect();
    fx_snow.load(Gdx.files.internal("fx/snow.p"), Gdx.files.internal("fx"));
    pool_snow = new ParticleEffectPool(fx_snow, 0, 150);
    fx_blood = new ParticleEffect();
    fx_blood.load(Gdx.files.internal("fx/blood.p"), Gdx.files.internal("fx"));
    pool_blood = new ParticleEffectPool(fx_blood, 0, 150);
    fx_bubbles = new ParticleEffect();
    fx_bubbles.load(Gdx.files.internal("fx/bubbles.p"), Gdx.files.internal("fx"));
    pool_bubbles = new ParticleEffectPool(fx_bubbles, 0, 150);
    fx_expl_fire = new ParticleEffect();
    fx_expl_fire.load(Gdx.files.internal("fx/explosion_fire.p"), Gdx.files.internal("fx"));
    pool_expl_fire = new ParticleEffectPool(fx_expl_fire, 0, 150);
    fx_firework_large = new ParticleEffect();
    fx_firework_large.load(Gdx.files.internal("fx/firework_large.p"), Gdx.files.internal("fx"));
    pool_firework_large = new ParticleEffectPool(fx_firework_large, 0, 150);
  }
  
  public static ParticleEffectPool.PooledEffect getParticleEffect(int paramInt)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      effect.start();
      return effect;
      effect = pool_fire.obtain();
      continue;
      effect = pool_snow.obtain();
      continue;
      effect = pool_blood.obtain();
      continue;
      effect = pool_bubbles.obtain();
      continue;
      effect = pool_expl_fire.obtain();
      continue;
      effect = pool_firework_large.obtain();
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\ParticleCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */