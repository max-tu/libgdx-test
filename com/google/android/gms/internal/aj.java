package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class aj
  implements Parcelable.Creator<ai>
{
  static void a(ai paramai, Parcel paramParcel, int paramInt)
  {
    paramInt = b.o(paramParcel);
    b.c(paramParcel, 1, paramai.versionCode);
    b.c(paramParcel, 2, paramai.eZ);
    b.c(paramParcel, 3, paramai.backgroundColor);
    b.c(paramParcel, 4, paramai.fa);
    b.c(paramParcel, 5, paramai.fb);
    b.c(paramParcel, 6, paramai.fc);
    b.c(paramParcel, 7, paramai.fd);
    b.c(paramParcel, 8, paramai.fe);
    b.c(paramParcel, 9, paramai.ff);
    b.a(paramParcel, 10, paramai.fg, false);
    b.c(paramParcel, 11, paramai.fh);
    b.a(paramParcel, 12, paramai.fi, false);
    b.c(paramParcel, 13, paramai.fj);
    b.c(paramParcel, 14, paramai.fk);
    b.a(paramParcel, 15, paramai.fl, false);
    b.D(paramParcel, paramInt);
  }
  
  public ai c(Parcel paramParcel)
  {
    int i8 = a.n(paramParcel);
    int i7 = 0;
    int i6 = 0;
    int i5 = 0;
    int i4 = 0;
    int i3 = 0;
    int i2 = 0;
    int i1 = 0;
    int n = 0;
    int m = 0;
    String str3 = null;
    int k = 0;
    String str2 = null;
    int j = 0;
    int i = 0;
    String str1 = null;
    while (paramParcel.dataPosition() < i8)
    {
      int i9 = a.m(paramParcel);
      switch (a.M(i9))
      {
      default: 
        a.b(paramParcel, i9);
        break;
      case 1: 
        i7 = a.g(paramParcel, i9);
        break;
      case 2: 
        i6 = a.g(paramParcel, i9);
        break;
      case 3: 
        i5 = a.g(paramParcel, i9);
        break;
      case 4: 
        i4 = a.g(paramParcel, i9);
        break;
      case 5: 
        i3 = a.g(paramParcel, i9);
        break;
      case 6: 
        i2 = a.g(paramParcel, i9);
        break;
      case 7: 
        i1 = a.g(paramParcel, i9);
        break;
      case 8: 
        n = a.g(paramParcel, i9);
        break;
      case 9: 
        m = a.g(paramParcel, i9);
        break;
      case 10: 
        str3 = a.m(paramParcel, i9);
        break;
      case 11: 
        k = a.g(paramParcel, i9);
        break;
      case 12: 
        str2 = a.m(paramParcel, i9);
        break;
      case 13: 
        j = a.g(paramParcel, i9);
        break;
      case 14: 
        i = a.g(paramParcel, i9);
        break;
      case 15: 
        str1 = a.m(paramParcel, i9);
      }
    }
    if (paramParcel.dataPosition() != i8) {
      throw new a.a("Overread allowed size end=" + i8, paramParcel);
    }
    return new ai(i7, i6, i5, i4, i3, i2, i1, n, m, str3, k, str2, j, i, str1);
  }
  
  public ai[] e(int paramInt)
  {
    return new ai[paramInt];
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\internal\aj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */