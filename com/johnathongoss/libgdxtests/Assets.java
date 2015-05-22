package com.johnathongoss.libgdxtests;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets
{
  public static BitmapFont font24;
  public static BitmapFont font32;
  public static Skin skin;
  public static TextureAtlas spriteSheet;
  
  public static void Load()
  {
    Texture.setEnforcePotImages(false);
    font32 = new BitmapFont(Gdx.files.internal("data/font_32.fnt"), Gdx.files.internal("data/font_32.png"), false);
    font24 = new BitmapFont(Gdx.files.internal("data/font_24.fnt"), Gdx.files.internal("data/font_24.png"), false);
    skin = new Skin(Gdx.files.internal("uiskin.json"));
    spriteSheet = new TextureAtlas(Gdx.files.internal("textures/animations.atlas"), Gdx.files.internal("textures"));
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\Assets.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */