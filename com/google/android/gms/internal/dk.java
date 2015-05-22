package com.google.android.gms.internal;

import android.util.Log;

public class dk
{
  private static boolean mb = false;
  private String mTag;
  private boolean mc;
  private boolean md;
  
  public dk(String paramString)
  {
    this(paramString, bc());
  }
  
  public dk(String paramString, boolean paramBoolean)
  {
    this.mTag = paramString;
    this.mc = paramBoolean;
  }
  
  public static boolean bc()
  {
    return mb;
  }
  
  public void a(String paramString, Object... paramVarArgs)
  {
    if (this.md) {
      Log.v(this.mTag, String.format(paramString, paramVarArgs));
    }
  }
  
  public void b(String paramString, Object... paramVarArgs)
  {
    if ((this.mc) || (mb)) {
      Log.d(this.mTag, String.format(paramString, paramVarArgs));
    }
  }
  
  public void c(String paramString, Object... paramVarArgs)
  {
    Log.i(this.mTag, String.format(paramString, paramVarArgs));
  }
  
  public void d(String paramString, Object... paramVarArgs)
  {
    Log.w(this.mTag, String.format(paramString, paramVarArgs));
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\internal\dk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */