package com.google.android.gms.appstate;

@Deprecated
public abstract interface OnStateLoadedListener
{
  public abstract void onStateConflict(int paramInt, String paramString, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);
  
  public abstract void onStateLoaded(int paramInt1, int paramInt2, byte[] paramArrayOfByte);
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\appstate\OnStateLoadedListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */