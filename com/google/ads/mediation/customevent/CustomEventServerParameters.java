package com.google.ads.mediation.customevent;

import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.MediationServerParameters.Parameter;

public final class CustomEventServerParameters
  extends MediationServerParameters
{
  @MediationServerParameters.Parameter(name="class_name", required=true)
  public String className;
  @MediationServerParameters.Parameter(name="label", required=true)
  public String label;
  @MediationServerParameters.Parameter(name="parameter", required=false)
  public String parameter = null;
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\ads\mediation\customevent\CustomEventServerParameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */