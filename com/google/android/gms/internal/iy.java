package com.google.android.gms.internal;

import java.io.IOException;

public class iy
  extends IOException
{
  public iy(String paramString)
  {
    super(paramString);
  }
  
  static iy gg()
  {
    return new iy("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
  }
  
  static iy gh()
  {
    return new iy("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
  }
  
  static iy gi()
  {
    return new iy("CodedInputStream encountered a malformed varint.");
  }
  
  static iy gj()
  {
    return new iy("Protocol message contained an invalid tag (zero).");
  }
  
  static iy gk()
  {
    return new iy("Protocol message end-group tag did not match expected tag.");
  }
  
  static iy gl()
  {
    return new iy("Protocol message tag had invalid wire type.");
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\internal\iy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */