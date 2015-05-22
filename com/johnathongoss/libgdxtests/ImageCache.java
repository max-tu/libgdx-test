package com.johnathongoss.libgdxtests;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ImageCache
{
  public static TextureAtlas atlas;
  public static Texture sheet;
  
  public static NinePatch CreatePatch(String paramString)
  {
    return atlas.createPatch(paramString);
  }
  
  public static TextureRegion getFrame(String paramString, int paramInt)
  {
    return atlas.findRegion(paramString, paramInt);
  }
  
  public static TextureRegion getTexture(String paramString)
  {
    return atlas.findRegion(paramString);
  }
  
  public static void load()
  {
    atlas = new TextureAtlas(Gdx.files.internal("textures/myTextures.atlas"), Gdx.files.internal("textures"));
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\ImageCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */