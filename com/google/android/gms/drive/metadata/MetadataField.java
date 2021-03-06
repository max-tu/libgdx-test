package com.google.android.gms.drive.metadata;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.eg;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class MetadataField<T>
{
  private final String rC;
  private final Set<String> rD;
  
  protected MetadataField(String paramString)
  {
    this.rC = ((String)eg.b(paramString, "fieldName"));
    this.rD = Collections.singleton(paramString);
  }
  
  protected MetadataField(String paramString, Collection<String> paramCollection)
  {
    this.rC = ((String)eg.b(paramString, "fieldName"));
    this.rD = Collections.unmodifiableSet(new HashSet(paramCollection));
  }
  
  protected abstract void a(Bundle paramBundle, T paramT);
  
  public final void a(DataHolder paramDataHolder, MetadataBundle paramMetadataBundle, int paramInt1, int paramInt2)
  {
    eg.b(paramDataHolder, "dataHolder");
    eg.b(paramMetadataBundle, "bundle");
    paramMetadataBundle.b(this, c(paramDataHolder, paramInt1, paramInt2));
  }
  
  public final void a(T paramT, Bundle paramBundle)
  {
    eg.b(paramBundle, "bundle");
    if (paramT == null)
    {
      paramBundle.putString(getName(), null);
      return;
    }
    a(paramBundle, paramT);
  }
  
  protected abstract T b(DataHolder paramDataHolder, int paramInt1, int paramInt2);
  
  public final T c(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    Iterator localIterator = this.rD.iterator();
    while (localIterator.hasNext()) {
      if (paramDataHolder.hasNull((String)localIterator.next(), paramInt1, paramInt2)) {
        return null;
      }
    }
    return (T)b(paramDataHolder, paramInt1, paramInt2);
  }
  
  public final Collection<String> cV()
  {
    return this.rD;
  }
  
  public final T d(Bundle paramBundle)
  {
    eg.b(paramBundle, "bundle");
    if (paramBundle.get(getName()) != null) {
      return (T)e(paramBundle);
    }
    return null;
  }
  
  protected abstract T e(Bundle paramBundle);
  
  public final String getName()
  {
    return this.rC;
  }
  
  public String toString()
  {
    return this.rC;
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\drive\metadata\MetadataField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */