package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract interface LifecycleDelegate
{
  public abstract void onCreate(Bundle paramBundle);
  
  public abstract View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle);
  
  public abstract void onDestroy();
  
  public abstract void onDestroyView();
  
  public abstract void onInflate(Activity paramActivity, Bundle paramBundle1, Bundle paramBundle2);
  
  public abstract void onLowMemory();
  
  public abstract void onPause();
  
  public abstract void onResume();
  
  public abstract void onSaveInstanceState(Bundle paramBundle);
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\dynamic\LifecycleDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */