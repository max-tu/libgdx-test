package com.google.android.gms.internal;

import android.util.Log;

public final class ea
{
  private final String pM;
  
  public ea(String paramString)
  {
    this.pM = ((String)eg.f(paramString));
  }
  
  public boolean K(int paramInt)
  {
    return Log.isLoggable(this.pM, paramInt);
  }
  
  public void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (K(6)) {
      Log.e(paramString1, paramString2, paramThrowable);
    }
  }
  
  public void c(String paramString1, String paramString2)
  {
    if (K(5)) {
      Log.w(paramString1, paramString2);
    }
  }
  
  public void d(String paramString1, String paramString2)
  {
    if (K(6)) {
      Log.e(paramString1, paramString2);
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\internal\ea.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */