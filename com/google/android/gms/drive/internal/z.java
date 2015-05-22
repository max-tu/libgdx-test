package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.c;

public class z
  extends a
{
  private final a.c<Status> jW;
  
  public z(a.c<Status> paramc)
  {
    this.jW = paramc;
  }
  
  public void m(Status paramStatus)
    throws RemoteException
  {
    this.jW.a(paramStatus);
  }
  
  public void onSuccess()
    throws RemoteException
  {
    this.jW.a(Status.nA);
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\drive\internal\z.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */