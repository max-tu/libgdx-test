package com.google.android.gms.games.multiplayer.turnbased;

public abstract interface OnTurnBasedMatchUpdateReceivedListener
{
  public abstract void onTurnBasedMatchReceived(TurnBasedMatch paramTurnBasedMatch);
  
  public abstract void onTurnBasedMatchRemoved(String paramString);
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\games\multiplayer\turnbased\OnTurnBasedMatchUpdateReceivedListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */