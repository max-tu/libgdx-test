package com.johnathongoss.libgdxtests;

import com.badlogic.gdx.utils.Pool;
import com.johnathongoss.libgdxtests.entities.DamageImage;
import com.johnathongoss.libgdxtests.entities.Effect;

public class EffectsCache
{
  public static final int DAMAGE = 0;
  private static final Pool<DamageImage> damagePool = new Pool()
  {
    protected DamageImage newObject()
    {
      return new DamageImage();
    }
  };
  
  public static void Load() {}
  
  public static void free(Effect paramEffect)
  {
    if ((paramEffect instanceof DamageImage)) {
      damagePool.free((DamageImage)paramEffect);
    }
  }
  
  public static DamageImage getDamageEffect()
  {
    return (DamageImage)damagePool.obtain();
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\EffectsCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */