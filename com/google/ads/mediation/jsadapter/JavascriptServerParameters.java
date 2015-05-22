package com.google.ads.mediation.jsadapter;

import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.MediationServerParameters.Parameter;

public class JavascriptServerParameters
  extends MediationServerParameters
{
  @MediationServerParameters.Parameter(name="adxtym_height", required=false)
  public Integer height;
  @MediationServerParameters.Parameter(name="adxtym_html", required=true)
  public String htmlScript;
  @MediationServerParameters.Parameter(name="adxtym_passback_url", required=false)
  public String passBackUrl;
  @MediationServerParameters.Parameter(name="adxtym_width", required=false)
  public Integer width;
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\ads\mediation\jsadapter\JavascriptServerParameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */