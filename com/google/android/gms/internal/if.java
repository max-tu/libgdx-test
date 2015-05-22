package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.b;
import com.google.android.gms.plus.model.moments.ItemScope;
import com.google.android.gms.plus.model.moments.Moment;

public final class if
  extends b
  implements Moment
{
  private id Fo;
  
  public if(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  private id fu()
  {
    try
    {
      if (this.Fo == null)
      {
        byte[] arrayOfByte = getByteArray("momentImpl");
        Parcel localParcel = Parcel.obtain();
        localParcel.unmarshall(arrayOfByte, 0, arrayOfByte.length);
        localParcel.setDataPosition(0);
        this.Fo = id.CREATOR.at(localParcel);
        localParcel.recycle();
      }
      return this.Fo;
    }
    finally {}
  }
  
  public id ft()
  {
    return fu();
  }
  
  public String getId()
  {
    return fu().getId();
  }
  
  public ItemScope getResult()
  {
    return fu().getResult();
  }
  
  public String getStartDate()
  {
    return fu().getStartDate();
  }
  
  public ItemScope getTarget()
  {
    return fu().getTarget();
  }
  
  public String getType()
  {
    return fu().getType();
  }
  
  public boolean hasId()
  {
    return fu().hasId();
  }
  
  public boolean hasResult()
  {
    return fu().hasId();
  }
  
  public boolean hasStartDate()
  {
    return fu().hasStartDate();
  }
  
  public boolean hasTarget()
  {
    return fu().hasTarget();
  }
  
  public boolean hasType()
  {
    return fu().hasType();
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\internal\if.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */