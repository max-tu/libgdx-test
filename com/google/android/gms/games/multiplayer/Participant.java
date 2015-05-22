package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Player;

public abstract interface Participant
  extends Parcelable, Freezable<Participant>
{
  public static final int STATUS_DECLINED = 3;
  public static final int STATUS_FINISHED = 5;
  public static final int STATUS_INVITED = 1;
  public static final int STATUS_JOINED = 2;
  public static final int STATUS_LEFT = 4;
  public static final int STATUS_NOT_INVITED_YET = 0;
  public static final int STATUS_UNRESPONSIVE = 6;
  
  public abstract String dy();
  
  public abstract int getCapabilities();
  
  public abstract String getDisplayName();
  
  public abstract void getDisplayName(CharArrayBuffer paramCharArrayBuffer);
  
  public abstract Uri getHiResImageUri();
  
  public abstract Uri getIconImageUri();
  
  public abstract String getParticipantId();
  
  public abstract Player getPlayer();
  
  public abstract ParticipantResult getResult();
  
  public abstract int getStatus();
  
  public abstract boolean isConnectedToRoom();
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\games\multiplayer\Participant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */