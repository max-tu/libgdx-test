package com.google.android.gms.ads;

import android.content.Context;
import android.location.Location;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.internal.af;
import com.google.android.gms.internal.af.a;
import java.util.Date;
import java.util.Set;

public final class AdRequest
{
  public static final String DEVICE_ID_EMULATOR = af.DEVICE_ID_EMULATOR;
  public static final int ERROR_CODE_INTERNAL_ERROR = 0;
  public static final int ERROR_CODE_INVALID_REQUEST = 1;
  public static final int ERROR_CODE_NETWORK_ERROR = 2;
  public static final int ERROR_CODE_NO_FILL = 3;
  public static final int GENDER_FEMALE = 2;
  public static final int GENDER_MALE = 1;
  public static final int GENDER_UNKNOWN = 0;
  private final af dW;
  
  private AdRequest(Builder paramBuilder)
  {
    this.dW = new af(Builder.a(paramBuilder));
  }
  
  public Date getBirthday()
  {
    return this.dW.getBirthday();
  }
  
  public int getGender()
  {
    return this.dW.getGender();
  }
  
  public Set<String> getKeywords()
  {
    return this.dW.getKeywords();
  }
  
  public Location getLocation()
  {
    return this.dW.getLocation();
  }
  
  public <T extends NetworkExtras> T getNetworkExtras(Class<T> paramClass)
  {
    return this.dW.getNetworkExtras(paramClass);
  }
  
  public boolean isTestDevice(Context paramContext)
  {
    return this.dW.isTestDevice(paramContext);
  }
  
  af v()
  {
    return this.dW;
  }
  
  public static final class Builder
  {
    private final af.a dX = new af.a();
    
    public Builder addKeyword(String paramString)
    {
      this.dX.g(paramString);
      return this;
    }
    
    public Builder addNetworkExtras(NetworkExtras paramNetworkExtras)
    {
      this.dX.a(paramNetworkExtras);
      return this;
    }
    
    public Builder addTestDevice(String paramString)
    {
      this.dX.h(paramString);
      return this;
    }
    
    public AdRequest build()
    {
      return new AdRequest(this, null);
    }
    
    public Builder setBirthday(Date paramDate)
    {
      this.dX.a(paramDate);
      return this;
    }
    
    public Builder setGender(int paramInt)
    {
      this.dX.d(paramInt);
      return this;
    }
    
    public Builder setLocation(Location paramLocation)
    {
      this.dX.a(paramLocation);
      return this;
    }
    
    public Builder tagForChildDirectedTreatment(boolean paramBoolean)
    {
      this.dX.e(paramBoolean);
      return this;
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\ads\AdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */