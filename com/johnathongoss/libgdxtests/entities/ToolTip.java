package com.johnathongoss.libgdxtests.entities;

import com.johnathongoss.libgdxtests.ImageCache;

public class ToolTip
  extends TextBox
{
  public ToolTip()
  {
    this.background = ImageCache.CreatePatch("tooltip");
  }
  
  void animate() {}
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\entities\ToolTip.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */