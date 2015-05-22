package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.internal.d;
import com.google.android.gms.maps.model.internal.d.a;

public abstract interface k
  extends IInterface
{
  public abstract boolean a(d paramd)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements k
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.maps.internal.IOnMarkerClickListener");
    }
    
    public static k ac(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMarkerClickListener");
      if ((localIInterface != null) && ((localIInterface instanceof k))) {
        return (k)localIInterface;
      }
      return new a(paramIBinder);
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.maps.internal.IOnMarkerClickListener");
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IOnMarkerClickListener");
      boolean bool = a(d.a.am(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      if (bool) {}
      for (paramInt1 = 1;; paramInt1 = 0)
      {
        paramParcel2.writeInt(paramInt1);
        return true;
      }
    }
    
    private static class a
      implements k
    {
      private IBinder dU;
      
      a(IBinder paramIBinder)
      {
        this.dU = paramIBinder;
      }
      
      /* Error */
      public boolean a(d paramd)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 5
        //   3: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore_2
        //   7: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore_3
        //   11: aload_2
        //   12: ldc 30
        //   14: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +55 -> 73
        //   21: aload_1
        //   22: invokeinterface 40 1 0
        //   27: astore_1
        //   28: aload_2
        //   29: aload_1
        //   30: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_0
        //   34: getfield 18	com/google/android/gms/maps/internal/k$a$a:dU	Landroid/os/IBinder;
        //   37: iconst_1
        //   38: aload_2
        //   39: aload_3
        //   40: iconst_0
        //   41: invokeinterface 49 5 0
        //   46: pop
        //   47: aload_3
        //   48: invokevirtual 52	android/os/Parcel:readException	()V
        //   51: aload_3
        //   52: invokevirtual 56	android/os/Parcel:readInt	()I
        //   55: istore 4
        //   57: iload 4
        //   59: ifeq +19 -> 78
        //   62: aload_3
        //   63: invokevirtual 59	android/os/Parcel:recycle	()V
        //   66: aload_2
        //   67: invokevirtual 59	android/os/Parcel:recycle	()V
        //   70: iload 5
        //   72: ireturn
        //   73: aconst_null
        //   74: astore_1
        //   75: goto -47 -> 28
        //   78: iconst_0
        //   79: istore 5
        //   81: goto -19 -> 62
        //   84: astore_1
        //   85: aload_3
        //   86: invokevirtual 59	android/os/Parcel:recycle	()V
        //   89: aload_2
        //   90: invokevirtual 59	android/os/Parcel:recycle	()V
        //   93: aload_1
        //   94: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	95	0	this	a
        //   0	95	1	paramd	d
        //   6	84	2	localParcel1	Parcel
        //   10	76	3	localParcel2	Parcel
        //   55	3	4	i	int
        //   1	79	5	bool	boolean
        // Exception table:
        //   from	to	target	type
        //   11	17	84	finally
        //   21	28	84	finally
        //   28	57	84	finally
      }
      
      public IBinder asBinder()
      {
        return this.dU;
      }
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\maps\internal\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */