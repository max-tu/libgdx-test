package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.fh;
import com.google.android.gms.internal.fi;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class c
{
  private static Map<String, MetadataField<?>> rE = new HashMap();
  
  static
  {
    b(fh.rG);
    b(fh.TITLE);
    b(fh.MIME_TYPE);
    b(fh.STARRED);
    b(fh.TRASHED);
    b(fh.rH);
    b(fh.rI);
    b(fh.PARENTS);
    b(fi.rL);
    b(fi.rJ);
    b(fi.rK);
    b(fi.rM);
  }
  
  public static MetadataField<?> ac(String paramString)
  {
    return (MetadataField)rE.get(paramString);
  }
  
  private static void b(MetadataField<?> paramMetadataField)
  {
    if (rE.containsKey(paramMetadataField.getName())) {
      throw new IllegalArgumentException("Duplicate field name registered: " + paramMetadataField.getName());
    }
    rE.put(paramMetadataField.getName(), paramMetadataField);
  }
  
  public static Collection<MetadataField<?>> cW()
  {
    return Collections.unmodifiableCollection(rE.values());
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\drive\metadata\internal\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */