package com.johnathongoss.libgdxtests.entities;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool.Poolable;

public class Effect
  extends Actor
  implements Pool.Poolable
{
  protected boolean alive = false;
  
  public boolean isAlive()
  {
    return this.alive;
  }
  
  public void reset()
  {
    this.alive = false;
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\entities\Effect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */