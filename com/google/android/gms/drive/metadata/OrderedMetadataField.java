package com.google.android.gms.drive.metadata;

import java.util.Collection;

public abstract class OrderedMetadataField<T extends Comparable<T>>
  extends MetadataField<T>
{
  protected OrderedMetadataField(String paramString)
  {
    super(paramString);
  }
  
  protected OrderedMetadataField(String paramString, Collection<String> paramCollection)
  {
    super(paramString, paramCollection);
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\drive\metadata\OrderedMetadataField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */