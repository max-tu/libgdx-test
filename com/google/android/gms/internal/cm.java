package com.google.android.gms.internal;

public abstract class cm
{
  private final Runnable ep = new Runnable()
  {
    public final void run()
    {
      cm.a(cm.this, Thread.currentThread());
      cm.this.ai();
    }
  };
  private volatile Thread ix;
  
  public abstract void ai();
  
  public final void cancel()
  {
    onStop();
    if (this.ix != null) {
      this.ix.interrupt();
    }
  }
  
  public abstract void onStop();
  
  public final void start()
  {
    cn.execute(this.ep);
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\internal\cm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */