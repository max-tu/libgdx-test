package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ee;
import com.google.android.gms.internal.ee.a;
import com.google.android.gms.maps.internal.r;

public final class VisibleRegion
  implements SafeParcelable
{
  public static final VisibleRegionCreator CREATOR = new VisibleRegionCreator();
  public final LatLng farLeft;
  public final LatLng farRight;
  private final int kg;
  public final LatLngBounds latLngBounds;
  public final LatLng nearLeft;
  public final LatLng nearRight;
  
  VisibleRegion(int paramInt, LatLng paramLatLng1, LatLng paramLatLng2, LatLng paramLatLng3, LatLng paramLatLng4, LatLngBounds paramLatLngBounds)
  {
    this.kg = paramInt;
    this.nearLeft = paramLatLng1;
    this.nearRight = paramLatLng2;
    this.farLeft = paramLatLng3;
    this.farRight = paramLatLng4;
    this.latLngBounds = paramLatLngBounds;
  }
  
  public VisibleRegion(LatLng paramLatLng1, LatLng paramLatLng2, LatLng paramLatLng3, LatLng paramLatLng4, LatLngBounds paramLatLngBounds)
  {
    this(1, paramLatLng1, paramLatLng2, paramLatLng3, paramLatLng4, paramLatLngBounds);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof VisibleRegion)) {
        return false;
      }
      paramObject = (VisibleRegion)paramObject;
    } while ((this.nearLeft.equals(((VisibleRegion)paramObject).nearLeft)) && (this.nearRight.equals(((VisibleRegion)paramObject).nearRight)) && (this.farLeft.equals(((VisibleRegion)paramObject).farLeft)) && (this.farRight.equals(((VisibleRegion)paramObject).farRight)) && (this.latLngBounds.equals(((VisibleRegion)paramObject).latLngBounds)));
    return false;
  }
  
  int getVersionCode()
  {
    return this.kg;
  }
  
  public int hashCode()
  {
    return ee.hashCode(new Object[] { this.nearLeft, this.nearRight, this.farLeft, this.farRight, this.latLngBounds });
  }
  
  public String toString()
  {
    return ee.e(this).a("nearLeft", this.nearLeft).a("nearRight", this.nearRight).a("farLeft", this.farLeft).a("farRight", this.farRight).a("latLngBounds", this.latLngBounds).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (r.eD())
    {
      k.a(this, paramParcel, paramInt);
      return;
    }
    VisibleRegionCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\maps\model\VisibleRegion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */