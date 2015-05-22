package com.johnathongoss.libgdxtests.entities;

public abstract class MyTimer
{
  float cap = 1.0F;
  boolean complete = false;
  float current = 0.0F;
  boolean repeating = false;
  boolean ticking = false;
  
  public MyTimer(float paramFloat)
  {
    this.cap = paramFloat;
  }
  
  public float getCap()
  {
    return this.cap;
  }
  
  public float getCurrent()
  {
    return this.current;
  }
  
  public boolean isComplete()
  {
    return this.complete;
  }
  
  public boolean isRepeating()
  {
    return this.repeating;
  }
  
  public void pause()
  {
    this.ticking = false;
  }
  
  protected abstract void perform();
  
  public void reset()
  {
    this.current = 0.0F;
    this.complete = false;
  }
  
  public void setCap(float paramFloat)
  {
    this.cap = paramFloat;
  }
  
  public void setRepeating(boolean paramBoolean)
  {
    this.repeating = paramBoolean;
  }
  
  public void start()
  {
    this.ticking = true;
  }
  
  public void update(float paramFloat)
  {
    if (this.ticking) {
      this.current += paramFloat;
    }
    if (this.current >= this.cap) {
      this.complete = true;
    }
    if (this.complete)
    {
      if (this.ticking) {
        perform();
      }
      pause();
      if (this.repeating)
      {
        reset();
        start();
      }
    }
    else
    {
      return;
    }
    pause();
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\entities\MyTimer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */