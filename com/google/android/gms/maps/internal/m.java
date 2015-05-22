package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface m
  extends IInterface
{
  public abstract boolean onMyLocationButtonClick()
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements m
  {
    public a()
    {
      attachInterface(this, "com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
    }
    
    public static m ae(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
      if ((localIInterface != null) && ((localIInterface instanceof m))) {
        return (m)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
      boolean bool = onMyLocationButtonClick();
      paramParcel2.writeNoException();
      if (bool) {}
      for (paramInt1 = 1;; paramInt1 = 0)
      {
        paramParcel2.writeInt(paramInt1);
        return true;
      }
    }
    
    private static class a
      implements m
    {
      private IBinder dU;
      
      a(IBinder paramIBinder)
      {
        this.dU = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return this.dU;
      }
      
      /* Error */
      public boolean onMyLocationButtonClick()
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 5
        //   3: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore_1
        //   7: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   10: astore_2
        //   11: aload_1
        //   12: ldc 33
        //   14: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_0
        //   18: getfield 18	com/google/android/gms/maps/internal/m$a$a:dU	Landroid/os/IBinder;
        //   21: iconst_1
        //   22: aload_1
        //   23: aload_2
        //   24: iconst_0
        //   25: invokeinterface 43 5 0
        //   30: pop
        //   31: aload_2
        //   32: invokevirtual 46	android/os/Parcel:readException	()V
        //   35: aload_2
        //   36: invokevirtual 50	android/os/Parcel:readInt	()I
        //   39: istore 4
        //   41: iload 4
        //   43: ifeq +14 -> 57
        //   46: aload_2
        //   47: invokevirtual 53	android/os/Parcel:recycle	()V
        //   50: aload_1
        //   51: invokevirtual 53	android/os/Parcel:recycle	()V
        //   54: iload 5
        //   56: ireturn
        //   57: iconst_0
        //   58: istore 5
        //   60: goto -14 -> 46
        //   63: astore_3
        //   64: aload_2
        //   65: invokevirtual 53	android/os/Parcel:recycle	()V
        //   68: aload_1
        //   69: invokevirtual 53	android/os/Parcel:recycle	()V
        //   72: aload_3
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   6	63	1	localParcel1	Parcel
        //   10	55	2	localParcel2	Parcel
        //   63	10	3	localObject	Object
        //   39	3	4	i	int
        //   1	58	5	bool	boolean
        // Exception table:
        //   from	to	target	type
        //   11	41	63	finally
      }
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\maps\internal\m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */