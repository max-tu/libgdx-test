package com.johnathongoss.libgdxtests;

import com.johnathongoss.libgdxtests.screens.Loading;

public class Testing
  extends MyGame
{
  public Testing(IActivityRequestHandler paramIActivityRequestHandler)
  {
    super(paramIActivityRequestHandler);
  }
  
  public void create()
  {
    setScreen(new Loading(this));
  }
  
  public void dispose()
  {
    super.dispose();
  }
  
  public void pause()
  {
    super.pause();
  }
  
  public void render()
  {
    super.render();
  }
  
  public void resize(int paramInt1, int paramInt2)
  {
    super.resize(paramInt1, paramInt2);
  }
  
  public void resume()
  {
    super.resume();
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\Testing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */