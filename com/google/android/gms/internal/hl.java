package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface hl
  extends IInterface
{
  public abstract void a(hk paramhk, Uri paramUri, Bundle paramBundle, boolean paramBoolean)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements hl
  {
    public static hl as(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.panorama.internal.IPanoramaService");
      if ((localIInterface != null) && ((localIInterface instanceof hl))) {
        return (hl)localIInterface;
      }
      return new a(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.panorama.internal.IPanoramaService");
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.panorama.internal.IPanoramaService");
      hk localhk = hk.a.ar(paramParcel1.readStrongBinder());
      Bundle localBundle;
      if (paramParcel1.readInt() != 0)
      {
        paramParcel2 = (Uri)Uri.CREATOR.createFromParcel(paramParcel1);
        if (paramParcel1.readInt() == 0) {
          break label130;
        }
        localBundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        label102:
        if (paramParcel1.readInt() == 0) {
          break label136;
        }
      }
      label130:
      label136:
      for (boolean bool = true;; bool = false)
      {
        a(localhk, paramParcel2, localBundle, bool);
        return true;
        paramParcel2 = null;
        break;
        localBundle = null;
        break label102;
      }
    }
    
    private static class a
      implements hl
    {
      private IBinder dU;
      
      a(IBinder paramIBinder)
      {
        this.dU = paramIBinder;
      }
      
      public void a(hk paramhk, Uri paramUri, Bundle paramBundle, boolean paramBoolean)
        throws RemoteException
      {
        IBinder localIBinder = null;
        int i = 1;
        Parcel localParcel = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel.writeInterfaceToken("com.google.android.gms.panorama.internal.IPanoramaService");
            if (paramhk != null) {
              localIBinder = paramhk.asBinder();
            }
            localParcel.writeStrongBinder(localIBinder);
            if (paramUri != null)
            {
              localParcel.writeInt(1);
              paramUri.writeToParcel(localParcel, 0);
              if (paramBundle != null)
              {
                localParcel.writeInt(1);
                paramBundle.writeToParcel(localParcel, 0);
                break label134;
                localParcel.writeInt(i);
                this.dU.transact(1, localParcel, null, 1);
              }
            }
            else
            {
              localParcel.writeInt(0);
              continue;
            }
            localParcel.writeInt(0);
          }
          finally
          {
            localParcel.recycle();
          }
          label134:
          while (!paramBoolean)
          {
            i = 0;
            break;
          }
        }
      }
      
      public IBinder asBinder()
      {
        return this.dU;
      }
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\internal\hl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */