package com.google.android.gms.maps;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.b;

public class a
{
  static void a(GoogleMapOptions paramGoogleMapOptions, Parcel paramParcel, int paramInt)
  {
    int i = b.o(paramParcel);
    b.c(paramParcel, 1, paramGoogleMapOptions.getVersionCode());
    b.a(paramParcel, 2, paramGoogleMapOptions.eo());
    b.a(paramParcel, 3, paramGoogleMapOptions.ep());
    b.c(paramParcel, 4, paramGoogleMapOptions.getMapType());
    b.a(paramParcel, 5, paramGoogleMapOptions.getCamera(), paramInt, false);
    b.a(paramParcel, 6, paramGoogleMapOptions.eq());
    b.a(paramParcel, 7, paramGoogleMapOptions.er());
    b.a(paramParcel, 8, paramGoogleMapOptions.es());
    b.a(paramParcel, 9, paramGoogleMapOptions.et());
    b.a(paramParcel, 10, paramGoogleMapOptions.eu());
    b.a(paramParcel, 11, paramGoogleMapOptions.ev());
    b.D(paramParcel, i);
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\maps\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */