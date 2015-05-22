package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface di
  extends IInterface
{
  public abstract void D(String paramString)
    throws RemoteException;
  
  public abstract void E(String paramString)
    throws RemoteException;
  
  public abstract void F(String paramString)
    throws RemoteException;
  
  public abstract void a(double paramDouble1, double paramDouble2, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void a(String paramString1, String paramString2, long paramLong)
    throws RemoteException;
  
  public abstract void a(String paramString, byte[] paramArrayOfByte, long paramLong)
    throws RemoteException;
  
  public abstract void a(boolean paramBoolean1, double paramDouble, boolean paramBoolean2)
    throws RemoteException;
  
  public abstract void aV()
    throws RemoteException;
  
  public abstract void b(String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract void bb()
    throws RemoteException;
  
  public abstract void c(String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void disconnect()
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements di
  {
    public static di v(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.cast.internal.ICastDeviceController");
      if ((localIInterface != null) && ((localIInterface instanceof di))) {
        return (di)localIInterface;
      }
      return new a(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool2 = false;
      boolean bool1 = false;
      double d1;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.cast.internal.ICastDeviceController");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        disconnect();
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        paramParcel2 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {
          bool1 = true;
        }
        c(paramParcel2, bool1);
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        b(paramParcel1.readString(), paramParcel1.readString());
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        bb();
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        D(paramParcel1.readString());
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        aV();
        return true;
      case 7: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        d1 = paramParcel1.readDouble();
        double d2 = paramParcel1.readDouble();
        if (paramParcel1.readInt() != 0) {}
        for (bool1 = true;; bool1 = false)
        {
          a(d1, d2, bool1);
          return true;
        }
      case 8: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        if (paramParcel1.readInt() != 0) {}
        for (bool1 = true;; bool1 = false)
        {
          d1 = paramParcel1.readDouble();
          if (paramParcel1.readInt() != 0) {
            bool2 = true;
          }
          a(bool1, d1, bool2);
          return true;
        }
      case 9: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        a(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readLong());
        return true;
      case 10: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        a(paramParcel1.readString(), paramParcel1.createByteArray(), paramParcel1.readLong());
        return true;
      case 11: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        E(paramParcel1.readString());
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.cast.internal.ICastDeviceController");
      F(paramParcel1.readString());
      return true;
    }
    
    private static class a
      implements di
    {
      private IBinder dU;
      
      a(IBinder paramIBinder)
      {
        this.dU = paramIBinder;
      }
      
      public void D(String paramString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
          localParcel.writeString(paramString);
          this.dU.transact(5, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void E(String paramString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
          localParcel.writeString(paramString);
          this.dU.transact(11, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void F(String paramString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
          localParcel.writeString(paramString);
          this.dU.transact(12, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      /* Error */
      public void a(double paramDouble1, double paramDouble2, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 8
        //   3: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: aload 6
        //   10: ldc 31
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload 6
        //   17: dload_1
        //   18: invokevirtual 54	android/os/Parcel:writeDouble	(D)V
        //   21: aload 6
        //   23: dload_3
        //   24: invokevirtual 54	android/os/Parcel:writeDouble	(D)V
        //   27: iload 5
        //   29: ifeq +32 -> 61
        //   32: aload 6
        //   34: iload 8
        //   36: invokevirtual 58	android/os/Parcel:writeInt	(I)V
        //   39: aload_0
        //   40: getfield 18	com/google/android/gms/internal/di$a$a:dU	Landroid/os/IBinder;
        //   43: bipush 7
        //   45: aload 6
        //   47: aconst_null
        //   48: iconst_1
        //   49: invokeinterface 43 5 0
        //   54: pop
        //   55: aload 6
        //   57: invokevirtual 46	android/os/Parcel:recycle	()V
        //   60: return
        //   61: iconst_0
        //   62: istore 8
        //   64: goto -32 -> 32
        //   67: astore 7
        //   69: aload 6
        //   71: invokevirtual 46	android/os/Parcel:recycle	()V
        //   74: aload 7
        //   76: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	77	0	this	a
        //   0	77	1	paramDouble1	double
        //   0	77	3	paramDouble2	double
        //   0	77	5	paramBoolean	boolean
        //   6	64	6	localParcel	Parcel
        //   67	8	7	localObject	Object
        //   1	62	8	i	int
        // Exception table:
        //   from	to	target	type
        //   8	27	67	finally
        //   32	55	67	finally
      }
      
      public void a(String paramString1, String paramString2, long paramLong)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
          localParcel.writeString(paramString1);
          localParcel.writeString(paramString2);
          localParcel.writeLong(paramLong);
          this.dU.transact(9, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void a(String paramString, byte[] paramArrayOfByte, long paramLong)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
          localParcel.writeString(paramString);
          localParcel.writeByteArray(paramArrayOfByte);
          localParcel.writeLong(paramLong);
          this.dU.transact(10, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      /* Error */
      public void a(boolean paramBoolean1, double paramDouble, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 8
        //   3: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: aload 5
        //   10: ldc 31
        //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: iload_1
        //   16: ifeq +57 -> 73
        //   19: iconst_1
        //   20: istore 7
        //   22: aload 5
        //   24: iload 7
        //   26: invokevirtual 58	android/os/Parcel:writeInt	(I)V
        //   29: aload 5
        //   31: dload_2
        //   32: invokevirtual 54	android/os/Parcel:writeDouble	(D)V
        //   35: iload 4
        //   37: ifeq +42 -> 79
        //   40: iload 8
        //   42: istore 7
        //   44: aload 5
        //   46: iload 7
        //   48: invokevirtual 58	android/os/Parcel:writeInt	(I)V
        //   51: aload_0
        //   52: getfield 18	com/google/android/gms/internal/di$a$a:dU	Landroid/os/IBinder;
        //   55: bipush 8
        //   57: aload 5
        //   59: aconst_null
        //   60: iconst_1
        //   61: invokeinterface 43 5 0
        //   66: pop
        //   67: aload 5
        //   69: invokevirtual 46	android/os/Parcel:recycle	()V
        //   72: return
        //   73: iconst_0
        //   74: istore 7
        //   76: goto -54 -> 22
        //   79: iconst_0
        //   80: istore 7
        //   82: goto -38 -> 44
        //   85: astore 6
        //   87: aload 5
        //   89: invokevirtual 46	android/os/Parcel:recycle	()V
        //   92: aload 6
        //   94: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	95	0	this	a
        //   0	95	1	paramBoolean1	boolean
        //   0	95	2	paramDouble	double
        //   0	95	4	paramBoolean2	boolean
        //   6	82	5	localParcel	Parcel
        //   85	8	6	localObject	Object
        //   20	61	7	i	int
        //   1	40	8	j	int
        // Exception table:
        //   from	to	target	type
        //   8	15	85	finally
        //   22	35	85	finally
        //   44	67	85	finally
      }
      
      public void aV()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
          this.dU.transact(6, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public IBinder asBinder()
      {
        return this.dU;
      }
      
      public void b(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
          localParcel.writeString(paramString1);
          localParcel.writeString(paramString2);
          this.dU.transact(3, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void bb()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
          this.dU.transact(4, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      /* Error */
      public void c(String paramString, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 4
        //   3: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore_3
        //   7: aload_3
        //   8: ldc 31
        //   10: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   13: aload_3
        //   14: aload_1
        //   15: invokevirtual 37	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   18: iload_2
        //   19: ifeq +28 -> 47
        //   22: aload_3
        //   23: iload 4
        //   25: invokevirtual 58	android/os/Parcel:writeInt	(I)V
        //   28: aload_0
        //   29: getfield 18	com/google/android/gms/internal/di$a$a:dU	Landroid/os/IBinder;
        //   32: iconst_2
        //   33: aload_3
        //   34: aconst_null
        //   35: iconst_1
        //   36: invokeinterface 43 5 0
        //   41: pop
        //   42: aload_3
        //   43: invokevirtual 46	android/os/Parcel:recycle	()V
        //   46: return
        //   47: iconst_0
        //   48: istore 4
        //   50: goto -28 -> 22
        //   53: astore_1
        //   54: aload_3
        //   55: invokevirtual 46	android/os/Parcel:recycle	()V
        //   58: aload_1
        //   59: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	60	0	this	a
        //   0	60	1	paramString	String
        //   0	60	2	paramBoolean	boolean
        //   6	49	3	localParcel	Parcel
        //   1	48	4	i	int
        // Exception table:
        //   from	to	target	type
        //   7	18	53	finally
        //   22	42	53	finally
      }
      
      public void disconnect()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.internal.ICastDeviceController");
          this.dU.transact(1, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\internal\di.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */