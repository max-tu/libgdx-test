package com.google.android.gms.drive.metadata;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;

public final class StringMetadataField
  extends MetadataField<String>
{
  public StringMetadataField(String paramString)
  {
    super(paramString);
  }
  
  protected void a(Bundle paramBundle, String paramString)
  {
    paramBundle.putString(getName(), paramString);
  }
  
  protected String d(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    return paramDataHolder.getString(getName(), paramInt1, paramInt2);
  }
  
  protected String f(Bundle paramBundle)
  {
    return paramBundle.getString(getName());
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\drive\metadata\StringMetadataField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */