package com.google.android.gms.internal;

public final class gf
{
  public static String aG(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      fn.d("MatchTurnStatus", "Unknown match turn status: " + paramInt);
      return "TURN_STATUS_UNKNOWN";
    case 0: 
      return "TURN_STATUS_INVITED";
    case 1: 
      return "TURN_STATUS_MY_TURN";
    case 2: 
      return "TURN_STATUS_THEIR_TURN";
    }
    return "TURN_STATUS_COMPLETE";
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\internal\gf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */