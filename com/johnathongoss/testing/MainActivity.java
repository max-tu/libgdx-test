package com.johnathongoss.testing;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.johnathongoss.libgdxtests.IActivityRequestHandler;
import com.johnathongoss.libgdxtests.MyGame;

public class MainActivity
  extends AndroidApplication
  implements IActivityRequestHandler
{
  private final int HIDE_ADS = 0;
  private final int SHOW_ADS = 1;
  protected AdView adView;
  protected Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default: 
        return;
      case 1: 
        MainActivity.this.adView.setVisibility(0);
        return;
      }
      MainActivity.this.adView.setVisibility(8);
    }
  };
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Object localObject = new AndroidApplicationConfiguration();
    ((AndroidApplicationConfiguration)localObject).useGL20 = true;
    paramBundle = new RelativeLayout(this);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    getWindow().clearFlags(2048);
    localObject = initializeForView(new MyGame(this), (AndroidApplicationConfiguration)localObject);
    this.adView = new AdView(this);
    this.adView.setAdSize(AdSize.SMART_BANNER);
    this.adView.setAdUnitId("ca-app-pub-3950981149902266/9264224631");
    AdRequest localAdRequest = new AdRequest.Builder().build();
    this.adView.loadAd(localAdRequest);
    paramBundle.addView((View)localObject);
    localObject = new RelativeLayout.LayoutParams(-2, -2);
    ((RelativeLayout.LayoutParams)localObject).addRule(10);
    ((RelativeLayout.LayoutParams)localObject).addRule(13);
    paramBundle.addView(this.adView, (ViewGroup.LayoutParams)localObject);
    setContentView(paramBundle);
  }
  
  public void showAds(boolean paramBoolean)
  {
    Handler localHandler = this.handler;
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      localHandler.sendEmptyMessage(i);
      return;
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\testing\MainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */