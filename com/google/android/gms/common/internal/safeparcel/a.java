package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class a
{
  public static Parcel[] A(Parcel paramParcel, int paramInt)
  {
    int i = a(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    if (i == 0) {
      return null;
    }
    int k = paramParcel.readInt();
    Parcel[] arrayOfParcel = new Parcel[k];
    paramInt = 0;
    if (paramInt < k)
    {
      int m = paramParcel.readInt();
      if (m != 0)
      {
        int n = paramParcel.dataPosition();
        Parcel localParcel = Parcel.obtain();
        localParcel.appendFrom(paramParcel, n, m);
        arrayOfParcel[paramInt] = localParcel;
        paramParcel.setDataPosition(m + n);
      }
      for (;;)
      {
        paramInt += 1;
        break;
        arrayOfParcel[paramInt] = null;
      }
    }
    paramParcel.setDataPosition(j + i);
    return arrayOfParcel;
  }
  
  public static int M(int paramInt)
  {
    return 0xFFFF & paramInt;
  }
  
  public static int a(Parcel paramParcel, int paramInt)
  {
    if ((paramInt & 0xFFFF0000) != -65536) {
      return paramInt >> 16 & 0xFFFF;
    }
    return paramParcel.readInt();
  }
  
  public static <T extends Parcelable> T a(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    paramCreator = (Parcelable)paramCreator.createFromParcel(paramParcel);
    paramParcel.setDataPosition(paramInt + i);
    return paramCreator;
  }
  
  private static void a(Parcel paramParcel, int paramInt1, int paramInt2)
  {
    paramInt1 = a(paramParcel, paramInt1);
    if (paramInt1 != paramInt2) {
      throw new a("Expected size " + paramInt2 + " got " + paramInt1 + " (0x" + Integer.toHexString(paramInt1) + ")", paramParcel);
    }
  }
  
  private static void a(Parcel paramParcel, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 != paramInt3) {
      throw new a("Expected size " + paramInt3 + " got " + paramInt2 + " (0x" + Integer.toHexString(paramInt2) + ")", paramParcel);
    }
  }
  
  public static void a(Parcel paramParcel, int paramInt, List paramList, ClassLoader paramClassLoader)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {
      return;
    }
    paramParcel.readList(paramList, paramClassLoader);
    paramParcel.setDataPosition(paramInt + i);
  }
  
  public static void b(Parcel paramParcel, int paramInt)
  {
    paramParcel.setDataPosition(a(paramParcel, paramInt) + paramParcel.dataPosition());
  }
  
  public static <T> T[] b(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    paramCreator = paramParcel.createTypedArray(paramCreator);
    paramParcel.setDataPosition(paramInt + i);
    return paramCreator;
  }
  
  public static <T> ArrayList<T> c(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    paramCreator = paramParcel.createTypedArrayList(paramCreator);
    paramParcel.setDataPosition(paramInt + i);
    return paramCreator;
  }
  
  public static boolean c(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 4);
    return paramParcel.readInt() != 0;
  }
  
  public static Boolean d(Parcel paramParcel, int paramInt)
  {
    int i = a(paramParcel, paramInt);
    if (i == 0) {
      return null;
    }
    a(paramParcel, paramInt, i, 4);
    if (paramParcel.readInt() != 0) {}
    for (boolean bool = true;; bool = false) {
      return Boolean.valueOf(bool);
    }
  }
  
  public static byte e(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 4);
    return (byte)paramParcel.readInt();
  }
  
  public static short f(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 4);
    return (short)paramParcel.readInt();
  }
  
  public static int g(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 4);
    return paramParcel.readInt();
  }
  
  public static long h(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 8);
    return paramParcel.readLong();
  }
  
  public static BigInteger i(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    byte[] arrayOfByte = paramParcel.createByteArray();
    paramParcel.setDataPosition(paramInt + i);
    return new BigInteger(arrayOfByte);
  }
  
  public static float j(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 4);
    return paramParcel.readFloat();
  }
  
  public static double k(Parcel paramParcel, int paramInt)
  {
    a(paramParcel, paramInt, 8);
    return paramParcel.readDouble();
  }
  
  public static BigDecimal l(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    byte[] arrayOfByte = paramParcel.createByteArray();
    int j = paramParcel.readInt();
    paramParcel.setDataPosition(paramInt + i);
    return new BigDecimal(new BigInteger(arrayOfByte), j);
  }
  
  public static int m(Parcel paramParcel)
  {
    return paramParcel.readInt();
  }
  
  public static String m(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    String str = paramParcel.readString();
    paramParcel.setDataPosition(paramInt + i);
    return str;
  }
  
  public static int n(Parcel paramParcel)
  {
    int j = m(paramParcel);
    int k = a(paramParcel, j);
    int i = paramParcel.dataPosition();
    if (M(j) != 20293) {
      throw new a("Expected object header. Got 0x" + Integer.toHexString(j), paramParcel);
    }
    j = i + k;
    if ((j < i) || (j > paramParcel.dataSize())) {
      throw new a("Size read is invalid start=" + i + " end=" + j, paramParcel);
    }
    return j;
  }
  
  public static IBinder n(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    IBinder localIBinder = paramParcel.readStrongBinder();
    paramParcel.setDataPosition(paramInt + i);
    return localIBinder;
  }
  
  public static Bundle o(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    Bundle localBundle = paramParcel.readBundle();
    paramParcel.setDataPosition(paramInt + i);
    return localBundle;
  }
  
  public static byte[] p(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    byte[] arrayOfByte = paramParcel.createByteArray();
    paramParcel.setDataPosition(paramInt + i);
    return arrayOfByte;
  }
  
  public static boolean[] q(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    boolean[] arrayOfBoolean = paramParcel.createBooleanArray();
    paramParcel.setDataPosition(paramInt + i);
    return arrayOfBoolean;
  }
  
  public static int[] r(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    int[] arrayOfInt = paramParcel.createIntArray();
    paramParcel.setDataPosition(paramInt + i);
    return arrayOfInt;
  }
  
  public static long[] s(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    long[] arrayOfLong = paramParcel.createLongArray();
    paramParcel.setDataPosition(paramInt + i);
    return arrayOfLong;
  }
  
  public static BigInteger[] t(Parcel paramParcel, int paramInt)
  {
    int i = a(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    if (i == 0) {
      return null;
    }
    int k = paramParcel.readInt();
    BigInteger[] arrayOfBigInteger = new BigInteger[k];
    paramInt = 0;
    while (paramInt < k)
    {
      arrayOfBigInteger[paramInt] = new BigInteger(paramParcel.createByteArray());
      paramInt += 1;
    }
    paramParcel.setDataPosition(j + i);
    return arrayOfBigInteger;
  }
  
  public static float[] u(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    float[] arrayOfFloat = paramParcel.createFloatArray();
    paramParcel.setDataPosition(paramInt + i);
    return arrayOfFloat;
  }
  
  public static double[] v(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    double[] arrayOfDouble = paramParcel.createDoubleArray();
    paramParcel.setDataPosition(paramInt + i);
    return arrayOfDouble;
  }
  
  public static BigDecimal[] w(Parcel paramParcel, int paramInt)
  {
    int i = a(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    if (i == 0) {
      return null;
    }
    int k = paramParcel.readInt();
    BigDecimal[] arrayOfBigDecimal = new BigDecimal[k];
    paramInt = 0;
    while (paramInt < k)
    {
      byte[] arrayOfByte = paramParcel.createByteArray();
      int m = paramParcel.readInt();
      arrayOfBigDecimal[paramInt] = new BigDecimal(new BigInteger(arrayOfByte), m);
      paramInt += 1;
    }
    paramParcel.setDataPosition(j + i);
    return arrayOfBigDecimal;
  }
  
  public static String[] x(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    String[] arrayOfString = paramParcel.createStringArray();
    paramParcel.setDataPosition(paramInt + i);
    return arrayOfString;
  }
  
  public static ArrayList<String> y(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    ArrayList localArrayList = paramParcel.createStringArrayList();
    paramParcel.setDataPosition(paramInt + i);
    return localArrayList;
  }
  
  public static Parcel z(Parcel paramParcel, int paramInt)
  {
    paramInt = a(paramParcel, paramInt);
    int i = paramParcel.dataPosition();
    if (paramInt == 0) {
      return null;
    }
    Parcel localParcel = Parcel.obtain();
    localParcel.appendFrom(paramParcel, i, paramInt);
    paramParcel.setDataPosition(paramInt + i);
    return localParcel;
  }
  
  public static class a
    extends RuntimeException
  {
    public a(String paramString, Parcel paramParcel)
    {
      super();
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\common\internal\safeparcel\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */