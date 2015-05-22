package com.google.android.gms.plus.model.people;

import android.os.Bundle;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.c;
import com.google.android.gms.internal.ig;
import com.google.android.gms.internal.ir;

public final class PersonBuffer
  extends DataBuffer<Person>
{
  private final c<ig> FZ;
  
  public PersonBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
    if ((paramDataHolder.getMetadata() != null) && (paramDataHolder.getMetadata().getBoolean("com.google.android.gms.plus.IsSafeParcelable", false)))
    {
      this.FZ = new c(paramDataHolder, ig.CREATOR);
      return;
    }
    this.FZ = null;
  }
  
  public Person get(int paramInt)
  {
    if (this.FZ != null) {
      return (Person)this.FZ.B(paramInt);
    }
    return new ir(this.nE, paramInt);
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\plus\model\people\PersonBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */