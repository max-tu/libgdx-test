package com.google.ads.mediation;

import com.google.ads.AdRequest.ErrorCode;

@Deprecated
public abstract interface MediationBannerListener
{
  public abstract void onClick(MediationBannerAdapter<?, ?> paramMediationBannerAdapter);
  
  public abstract void onDismissScreen(MediationBannerAdapter<?, ?> paramMediationBannerAdapter);
  
  public abstract void onFailedToReceiveAd(MediationBannerAdapter<?, ?> paramMediationBannerAdapter, AdRequest.ErrorCode paramErrorCode);
  
  public abstract void onLeaveApplication(MediationBannerAdapter<?, ?> paramMediationBannerAdapter);
  
  public abstract void onPresentScreen(MediationBannerAdapter<?, ?> paramMediationBannerAdapter);
  
  public abstract void onReceivedAd(MediationBannerAdapter<?, ?> paramMediationBannerAdapter);
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\ads\mediation\MediationBannerListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */