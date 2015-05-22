package com.johnathongoss.libgdxtests;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class AppData
{
  public static boolean DISABLE_ADS = false;
  
  public static class Prefs
  {
    private static String backgroundB = "b_b";
    private static String backgroundG;
    private static String backgroundR;
    private static String enableSound;
    private static String limitParticles = "limPart";
    private static Preferences prefs;
    
    static
    {
      enableSound = "enSound";
      backgroundR = "b_r";
      backgroundG = "b_g";
    }
    
    public static void Load()
    {
      prefs = Gdx.app.getPreferences("AppPreferences");
    }
    
    public static void Save()
    {
      prefs.flush();
    }
    
    public static float getBackgroundB()
    {
      return prefs.getFloat(backgroundB, 0.0F);
    }
    
    public static float getBackgroundG()
    {
      return prefs.getFloat(backgroundG, 0.0F);
    }
    
    public static float getBackgroundR()
    {
      return prefs.getFloat(backgroundR, 0.0F);
    }
    
    public static boolean isEnableSound()
    {
      return prefs.getBoolean(enableSound, true);
    }
    
    public static boolean isLimitParticles()
    {
      return prefs.getBoolean(limitParticles, true);
    }
    
    public static void setBackgroundB(float paramFloat)
    {
      prefs.putFloat(backgroundB, paramFloat);
    }
    
    public static void setBackgroundG(float paramFloat)
    {
      prefs.putFloat(backgroundG, paramFloat);
    }
    
    public static void setBackgroundR(float paramFloat)
    {
      prefs.putFloat(backgroundR, paramFloat);
    }
    
    public static void setEnableSound(boolean paramBoolean)
    {
      prefs.putBoolean(enableSound, paramBoolean);
    }
    
    public static void setLimitParticles(boolean paramBoolean)
    {
      prefs.putBoolean(limitParticles, paramBoolean);
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\AppData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */