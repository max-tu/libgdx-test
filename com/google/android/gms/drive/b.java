package com.google.android.gms.drive;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class b
  extends Metadata
{
  private final MetadataBundle qN;
  
  public b(MetadataBundle paramMetadataBundle)
  {
    this.qN = paramMetadataBundle;
  }
  
  protected <T> T a(MetadataField<T> paramMetadataField)
  {
    return (T)this.qN.a(paramMetadataField);
  }
  
  public Metadata cK()
  {
    return new b(MetadataBundle.a(this.qN));
  }
  
  public boolean isDataValid()
  {
    return this.qN != null;
  }
  
  public String toString()
  {
    return "Metadata [mImpl=" + this.qN + "]";
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\drive\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */