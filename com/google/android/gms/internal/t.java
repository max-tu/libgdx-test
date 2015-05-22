package com.google.android.gms.internal;

import com.google.android.gms.ads.AdListener;

public final class t
  extends ab.a
{
  private final AdListener ev;
  
  public t(AdListener paramAdListener)
  {
    this.ev = paramAdListener;
  }
  
  public void onAdClosed()
  {
    this.ev.onAdClosed();
  }
  
  public void onAdFailedToLoad(int paramInt)
  {
    this.ev.onAdFailedToLoad(paramInt);
  }
  
  public void onAdLeftApplication()
  {
    this.ev.onAdLeftApplication();
  }
  
  public void onAdLoaded()
  {
    this.ev.onAdLoaded();
  }
  
  public void onAdOpened()
  {
    this.ev.onAdOpened();
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\internal\t.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */