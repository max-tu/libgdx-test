package com.johnathongoss.libgdxtests.tests;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.johnathongoss.libgdxtests.Assets;
import com.johnathongoss.libgdxtests.MyGame;
import com.johnathongoss.libgdxtests.MyInputProcessor;
import com.johnathongoss.libgdxtests.screens.MainMenu;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Random;

public class LightTest
  implements Screen
{
  DecimalFormat DEC_FMT = new DecimalFormat("0.00000");
  final Vector3 DEFAULT_AMBIENT_COLOR = new Vector3(0.3F, 0.3F, 1.0F);
  final float DEFAULT_AMBIENT_INTENSITY = 0.2F;
  final Vector3 DEFAULT_ATTENUATION = new Vector3(0.4F, 3.0F, 20.0F);
  final Vector3 DEFAULT_LIGHT_COLOR = new Vector3(1.0F, 0.7F, 0.6F);
  final Vector3 DEFAULT_LIGHT_POS = new Vector3(0.0F, 0.0F, 0.06F);
  final float DEFAULT_STRENGTH = 1.0F;
  final Color NORMAL_VCOLOR = new Color(1.0F, 1.0F, 1.0F, 1.0F);
  final String TEXT = "Use number keys to adjust parameters:\n1: Randomize Ambient Color\n2: Randomize Ambient Intensity {0}\n3: Randomize Light Color\n4/5: Increase/decrease constant attenuation: {1}\n6/7: Increase/decrease linear attenuation: {2}\n8/9: Increase/decrease quadratic attenuation: {3}\n0: Reset parameters\nRIGHT/LEFT: Increase/decrease normal map intensity: {4}\nUP/DOWN: Increase/decrease lightDir.z: {5}\n\nS toggles attenuation, N toggles normal shading\nT to toggle textures";
  float ambientIntensity = 0.2F;
  Vector3 attenuation = new Vector3(this.DEFAULT_ATTENUATION);
  SpriteBatch batch;
  OrthographicCamera cam;
  boolean flipY;
  BitmapFont font;
  SpriteBatch fxBatch;
  MyGame game;
  MyInputProcessor input = new MyInputProcessor()
  {
    public boolean keyUp(int paramAnonymousInt)
    {
      if ((paramAnonymousInt == 4) || (paramAnonymousInt == 67) || (paramAnonymousInt == 131)) {
        LightTest.this.game.setScreen(new MainMenu(LightTest.this.game));
      }
      return false;
    }
    
    public boolean touchDown(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
    {
      LightTest.this.lightPos.x = Gdx.input.getX();
      LightTest.this.lightPos.y = (Gdx.graphics.getHeight() - Gdx.input.getY());
      return false;
    }
    
    public boolean touchDragged(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      LightTest.this.lightPos.x = Gdx.input.getX();
      LightTest.this.lightPos.y = (Gdx.graphics.getHeight() - Gdx.input.getY());
      return false;
    }
  };
  Vector3 lightPos = new Vector3(this.DEFAULT_LIGHT_POS);
  Texture normalBase;
  ShaderProgram program;
  Vector2 resolution = new Vector2();
  Random rnd = new Random();
  private Texture rock;
  private Texture rock_n;
  Stage stageui;
  float strength = 1.0F;
  private Texture teapot;
  private Texture teapot_n;
  private int texHeight;
  private int texWidth;
  Texture texture;
  Texture texture_n;
  Matrix4 transform = new Matrix4();
  boolean useNormals = true;
  boolean useShadow = true;
  
  public LightTest(MyGame paramMyGame)
  {
    this.game = paramMyGame;
    this.stageui = new Stage();
  }
  
  private ShaderProgram createShader()
  {
    int j = 1;
    Gdx.graphics.getGL20();
    System.out.println("VERTEX PROGRAM:\n------------\n\n" + "attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_proj;\nuniform mat4 u_trans;\nuniform mat4 u_projTrans;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_color = a_color;\n   v_texCoords = a_texCoord0;\n   gl_Position =  u_projTrans * a_position;\n}\n");
    System.out.println("FRAGMENT PROGRAM:\n------------\n\n" + "#ifdef GL_ES\nprecision mediump float;\n#endif\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nuniform sampler2D u_normals;\nuniform vec3 light;\nuniform vec3 ambientColor;\nuniform float ambientIntensity; \nuniform vec2 resolution;\nuniform vec3 lightColor;\nuniform bool useNormals;\nuniform bool useShadow;\nuniform vec3 attenuation;\nuniform float strength;\nuniform bool yInvert;\n\nvoid main() {\n       //sample color & normals from our textures\n       vec4 color = texture2D(u_texture, v_texCoords.st);\n       vec3 nColor = texture2D(u_normals, v_texCoords.st).rgb;\n\n       //some bump map programs will need the Y value flipped..\n       nColor.g = yInvert ? 1.0 - nColor.g : nColor.g;\n\n       //this is for debugging purposes, allowing us to lower the intensity of our bump map\n       vec3 nBase = vec3(0.5, 0.5, 1.0);\n       nColor = mix(nBase, nColor, strength);\n\n       //normals need to be converted to [-1.0, 1.0] range and normalized\n       vec3 normal = normalize(nColor * 2.0 - 1.0);\n\n       //here we do a simple distance calculation\n       vec3 deltaPos = vec3( (light.xy - gl_FragCoord.xy) / resolution.xy, light.z );\n\n       vec3 lightDir = normalize(deltaPos);\n       float lambert = useNormals ? clamp(dot(normal, lightDir), 0.0, 1.0) : 1.0;\n       \n       //now let's get a nice little falloff\n       float d = sqrt(dot(deltaPos, deltaPos));       \n       float att = useShadow ? 1.0 / ( attenuation.x + (attenuation.y*d) + (attenuation.z*d*d) ) : 1.0;\n       \n       vec3 result = (ambientColor * ambientIntensity) + (lightColor.rgb * lambert) * att;\n       result *= color.rgb;\n       \n       gl_FragColor = v_color * vec4(result, color.a);\n}");
    ShaderProgram localShaderProgram = new ShaderProgram("attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_proj;\nuniform mat4 u_trans;\nuniform mat4 u_projTrans;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_color = a_color;\n   v_texCoords = a_texCoord0;\n   gl_Position =  u_projTrans * a_position;\n}\n", "#ifdef GL_ES\nprecision mediump float;\n#endif\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nuniform sampler2D u_normals;\nuniform vec3 light;\nuniform vec3 ambientColor;\nuniform float ambientIntensity; \nuniform vec2 resolution;\nuniform vec3 lightColor;\nuniform bool useNormals;\nuniform bool useShadow;\nuniform vec3 attenuation;\nuniform float strength;\nuniform bool yInvert;\n\nvoid main() {\n       //sample color & normals from our textures\n       vec4 color = texture2D(u_texture, v_texCoords.st);\n       vec3 nColor = texture2D(u_normals, v_texCoords.st).rgb;\n\n       //some bump map programs will need the Y value flipped..\n       nColor.g = yInvert ? 1.0 - nColor.g : nColor.g;\n\n       //this is for debugging purposes, allowing us to lower the intensity of our bump map\n       vec3 nBase = vec3(0.5, 0.5, 1.0);\n       nColor = mix(nBase, nColor, strength);\n\n       //normals need to be converted to [-1.0, 1.0] range and normalized\n       vec3 normal = normalize(nColor * 2.0 - 1.0);\n\n       //here we do a simple distance calculation\n       vec3 deltaPos = vec3( (light.xy - gl_FragCoord.xy) / resolution.xy, light.z );\n\n       vec3 lightDir = normalize(deltaPos);\n       float lambert = useNormals ? clamp(dot(normal, lightDir), 0.0, 1.0) : 1.0;\n       \n       //now let's get a nice little falloff\n       float d = sqrt(dot(deltaPos, deltaPos));       \n       float att = useShadow ? 1.0 / ( attenuation.x + (attenuation.y*d) + (attenuation.z*d*d) ) : 1.0;\n       \n       vec3 result = (ambientColor * ambientIntensity) + (lightColor.rgb * lambert) * att;\n       result *= color.rgb;\n       \n       gl_FragColor = v_color * vec4(result, color.a);\n}");
    ShaderProgram.pedantic = false;
    if (!localShaderProgram.isCompiled()) {
      throw new IllegalArgumentException("couldn't compile shader: " + localShaderProgram.getLog());
    }
    this.resolution.set(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    localShaderProgram.begin();
    localShaderProgram.setUniformi("u_texture", 0);
    localShaderProgram.setUniformi("u_normals", 1);
    localShaderProgram.setUniformf("light", this.lightPos);
    localShaderProgram.setUniformf("strength", this.strength);
    localShaderProgram.setUniformf("ambientIntensity", this.ambientIntensity);
    localShaderProgram.setUniformf("ambientColor", this.DEFAULT_AMBIENT_COLOR);
    localShaderProgram.setUniformf("resolution", this.resolution);
    localShaderProgram.setUniformf("lightColor", this.DEFAULT_LIGHT_COLOR);
    localShaderProgram.setUniformf("attenuation", this.attenuation);
    if (this.useShadow)
    {
      i = 1;
      localShaderProgram.setUniformi("useShadow", i);
      if (!this.useNormals) {
        break label293;
      }
      i = 1;
      label257:
      localShaderProgram.setUniformi("useNormals", i);
      if (!this.flipY) {
        break label298;
      }
    }
    label293:
    label298:
    for (int i = j;; i = 0)
    {
      localShaderProgram.setUniformi("yInvert", i);
      localShaderProgram.end();
      return localShaderProgram;
      i = 0;
      break;
      i = 0;
      break label257;
    }
  }
  
  private Vector3 rndColor()
  {
    return new Vector3(this.rnd.nextFloat(), this.rnd.nextFloat(), this.rnd.nextFloat());
  }
  
  public void dispose()
  {
    this.fxBatch.dispose();
    this.batch.dispose();
    this.texture.dispose();
    this.texture_n.dispose();
  }
  
  public void hide() {}
  
  public void pause() {}
  
  public void render(float paramFloat)
  {
    Gdx.gl.glClear(16384);
    this.batch.begin();
    this.NORMAL_VCOLOR.a = (1.0F - this.strength);
    this.batch.draw(this.texture_n, 10.0F, 30.0F);
    this.batch.setColor(this.NORMAL_VCOLOR);
    this.batch.draw(this.normalBase, 10.0F, 30.0F, this.texWidth, this.texHeight);
    this.batch.setColor(Color.WHITE);
    this.batch.draw(this.texture, this.texWidth * 2 + 30, 30.0F);
    Assets.font24.draw(this.batch, "Diffuse Color", this.texWidth * 2 + 30, this.texHeight + 30 + 30);
    Assets.font24.draw(this.batch, "Normal Map", 10.0F, this.texHeight + 30 + 30);
    Assets.font24.draw(this.batch, "Final Color", this.texWidth + 20, this.texHeight + 30 + 30);
    Assets.font24.drawMultiLine(this.batch, "Light using Normals Test |", 0.0F, Assets.font24.getLineHeight(), this.game.getWidth(), BitmapFont.HAlignment.RIGHT);
    Assets.font24.drawMultiLine(this.batch, String.format("%.1f", new Object[] { Float.valueOf(this.strength) }) + " |", 0.0F, this.game.getHeight() - this.game.getButtonHeight() / 2.0F - this.game.getButtonHeight(), this.game.getWidth() - this.game.getButtonWidth() * 2.0F, BitmapFont.HAlignment.RIGHT);
    Assets.font24.drawMultiLine(this.batch, String.format("%.2f", new Object[] { Float.valueOf(this.lightPos.z) }) + " |", 0.0F, this.game.getHeight() - this.game.getButtonHeight() / 2.0F - this.game.getButtonHeight() * 2.0F, this.game.getWidth() - this.game.getButtonWidth() * 2.0F, BitmapFont.HAlignment.RIGHT);
    Assets.font24.drawMultiLine(this.batch, String.format("%.2f", new Object[] { Float.valueOf(this.ambientIntensity) }) + " |", 0.0F, this.game.getHeight() - this.game.getButtonHeight() / 2.0F - this.game.getButtonHeight() * 3.0F, this.game.getWidth() - this.game.getButtonWidth() * 2.0F, BitmapFont.HAlignment.RIGHT);
    this.batch.end();
    this.fxBatch.begin();
    Object localObject;
    if (Gdx.input.isKeyPressed(11))
    {
      localObject = this.attenuation;
      ((Vector3)localObject).x += 0.025F;
      this.program.setUniformf("ambientIntensity", this.ambientIntensity);
      this.program.setUniformf("attenuation", this.attenuation);
      this.program.setUniformf("light", this.lightPos);
      localObject = this.program;
      if (!this.useNormals) {
        break label1123;
      }
      i = 1;
      label643:
      ((ShaderProgram)localObject).setUniformi("useNormals", i);
      localObject = this.program;
      if (!this.useShadow) {
        break label1128;
      }
    }
    label1123:
    label1128:
    for (int i = 1;; i = 0)
    {
      ((ShaderProgram)localObject).setUniformi("useShadow", i);
      this.program.setUniformf("strength", this.strength);
      this.texture_n.bind(1);
      this.texture.bind(0);
      this.fxBatch.draw(this.texture, this.texWidth + 20, 30.0F);
      this.fxBatch.end();
      this.stageui.act(paramFloat);
      this.stageui.draw();
      return;
      if (Gdx.input.isKeyPressed(12))
      {
        localObject = this.attenuation;
        ((Vector3)localObject).x -= 0.025F;
        if (this.attenuation.x >= 0.0F) {
          break;
        }
        this.attenuation.x = 0.0F;
        break;
      }
      if (Gdx.input.isKeyPressed(13))
      {
        localObject = this.attenuation;
        ((Vector3)localObject).y += 0.25F;
        break;
      }
      if (Gdx.input.isKeyPressed(14))
      {
        localObject = this.attenuation;
        ((Vector3)localObject).y -= 0.25F;
        if (this.attenuation.y >= 0.0F) {
          break;
        }
        this.attenuation.y = 0.0F;
        break;
      }
      if (Gdx.input.isKeyPressed(15))
      {
        localObject = this.attenuation;
        ((Vector3)localObject).z += 0.25F;
        break;
      }
      if (Gdx.input.isKeyPressed(16))
      {
        localObject = this.attenuation;
        ((Vector3)localObject).z -= 0.25F;
        if (this.attenuation.z >= 0.0F) {
          break;
        }
        this.attenuation.z = 0.0F;
        break;
      }
      if (Gdx.input.isKeyPressed(22))
      {
        this.strength += 0.025F;
        if (this.strength <= 1.0F) {
          break;
        }
        this.strength = 1.0F;
        break;
      }
      if (Gdx.input.isKeyPressed(21))
      {
        this.strength -= 0.025F;
        if (this.strength >= 0.0F) {
          break;
        }
        this.strength = 0.0F;
        break;
      }
      if (Gdx.input.isKeyPressed(19))
      {
        localObject = this.lightPos;
        ((Vector3)localObject).z += 0.0025F;
        break;
      }
      if (!Gdx.input.isKeyPressed(20)) {
        break;
      }
      localObject = this.lightPos;
      ((Vector3)localObject).z -= 0.0025F;
      break;
      i = 0;
      break label643;
    }
  }
  
  public void resize(int paramInt1, int paramInt2)
  {
    this.cam.setToOrtho(false, paramInt1, paramInt2);
    this.resolution.set(paramInt1, paramInt2);
    this.program.setUniformf("resolution", this.resolution);
  }
  
  public void resume() {}
  
  public void show()
  {
    this.game.showAds(true);
    this.rock = new Texture(Gdx.files.internal("data/teapot.png"));
    this.rock_n = new Texture(Gdx.files.internal("data/teapot_n.png"));
    this.teapot = new Texture(Gdx.files.internal("data/rock.png"));
    this.teapot_n = new Texture(Gdx.files.internal("data/rock_n.png"));
    this.texture = this.teapot;
    this.texture_n = this.teapot_n;
    if (this.texture == this.rock) {}
    for (boolean bool = true;; bool = false)
    {
      this.flipY = bool;
      Object localObject = new Pixmap(1, 1, Pixmap.Format.RGB565);
      ((Pixmap)localObject).setColor(0.5F, 0.5F, 1.0F, 1.0F);
      ((Pixmap)localObject).fill();
      this.normalBase = new Texture((Pixmap)localObject);
      this.texWidth = this.texture.getWidth();
      this.texHeight = this.texture.getHeight();
      this.cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
      this.cam.setToOrtho(false);
      this.program = createShader();
      this.fxBatch = new SpriteBatch(100, this.program);
      this.fxBatch.setShader(this.program);
      this.fxBatch.setProjectionMatrix(this.cam.combined);
      this.fxBatch.setTransformMatrix(this.transform);
      this.batch = new SpriteBatch(100);
      this.batch.setProjectionMatrix(this.cam.combined);
      this.batch.setTransformMatrix(this.transform);
      Gdx.input.setInputProcessor(new InputAdapter()
      {
        public boolean keyDown(int paramAnonymousInt)
        {
          boolean bool2 = false;
          boolean bool1 = false;
          int i = 1;
          Object localObject;
          if (paramAnonymousInt == 47)
          {
            localObject = LightTest.this;
            if (LightTest.this.useShadow) {}
            for (;;)
            {
              ((LightTest)localObject).useShadow = bool1;
              return true;
              bool1 = true;
            }
          }
          if (paramAnonymousInt == 42)
          {
            localObject = LightTest.this;
            if (LightTest.this.useNormals) {}
            for (bool1 = bool2;; bool1 = true)
            {
              ((LightTest)localObject).useNormals = bool1;
              return true;
            }
          }
          if (paramAnonymousInt == 8)
          {
            LightTest.this.program.begin();
            LightTest.this.program.setUniformf("ambientColor", LightTest.this.rndColor());
            LightTest.this.program.end();
            return true;
          }
          if (paramAnonymousInt == 9)
          {
            LightTest.this.ambientIntensity = LightTest.this.rnd.nextFloat();
            return true;
          }
          if (paramAnonymousInt == 10)
          {
            LightTest.this.program.begin();
            LightTest.this.program.setUniformf("lightColor", LightTest.this.rndColor());
            LightTest.this.program.end();
            return true;
          }
          if (paramAnonymousInt == 7)
          {
            LightTest.this.attenuation.set(LightTest.this.DEFAULT_ATTENUATION);
            LightTest.this.ambientIntensity = 0.2F;
            LightTest.this.lightPos.set(LightTest.this.DEFAULT_LIGHT_POS);
            LightTest.this.strength = 1.0F;
            LightTest.this.program.begin();
            LightTest.this.program.setUniformf("lightColor", LightTest.this.DEFAULT_LIGHT_COLOR);
            LightTest.this.program.setUniformf("ambientColor", LightTest.this.DEFAULT_AMBIENT_COLOR);
            LightTest.this.program.setUniformf("ambientIntensity", LightTest.this.ambientIntensity);
            LightTest.this.program.setUniformf("attenuation", LightTest.this.attenuation);
            LightTest.this.program.setUniformf("lightPos", LightTest.this.lightPos);
            LightTest.this.program.setUniformf("strength", LightTest.this.strength);
            LightTest.this.program.end();
          }
          while (paramAnonymousInt != 48) {
            return false;
          }
          LightTest localLightTest = LightTest.this;
          if (LightTest.this.texture == LightTest.this.teapot)
          {
            localObject = LightTest.this.rock;
            label433:
            localLightTest.texture = ((Texture)localObject);
            localLightTest = LightTest.this;
            if (LightTest.this.texture_n != LightTest.this.teapot_n) {
              break label600;
            }
            localObject = LightTest.this.rock_n;
            label468:
            localLightTest.texture_n = ((Texture)localObject);
            localObject = LightTest.this;
            if (LightTest.this.texture != LightTest.this.rock) {
              break label611;
            }
            bool1 = true;
            label498:
            ((LightTest)localObject).flipY = bool1;
            LightTest.this.texWidth = LightTest.this.texture.getWidth();
            LightTest.this.texHeight = LightTest.this.texture.getHeight();
            LightTest.this.program.begin();
            localObject = LightTest.this.program;
            if (!LightTest.this.flipY) {
              break label617;
            }
          }
          label600:
          label611:
          label617:
          for (paramAnonymousInt = i;; paramAnonymousInt = 0)
          {
            ((ShaderProgram)localObject).setUniformi("yInvert", paramAnonymousInt);
            LightTest.this.program.end();
            break;
            localObject = LightTest.this.teapot;
            break label433;
            localObject = LightTest.this.teapot_n;
            break label468;
            bool1 = false;
            break label498;
          }
        }
      });
      localObject = new InputMultiplexer(new InputProcessor[] { this.stageui, this.input });
      Gdx.input.setInputProcessor((InputProcessor)localObject);
      Gdx.input.setCatchBackKey(true);
      this.font = new BitmapFont();
      localObject = new TextButton("Normal [-]", Assets.skin);
      ((TextButton)localObject).setBounds(this.game.getWidth() - this.game.getButtonWidth(), this.game.getHeight() - this.game.getButtonHeight() * 2.0F, this.game.getButtonWidth(), this.game.getButtonHeight());
      ((TextButton)localObject).addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          paramAnonymousInputEvent = LightTest.this;
          paramAnonymousInputEvent.strength -= 0.1F;
          if (LightTest.this.strength < 0.0F) {
            LightTest.this.strength = 0.0F;
          }
        }
      });
      this.stageui.addActor((Actor)localObject);
      localObject = new TextButton("Normal [+]", Assets.skin);
      ((TextButton)localObject).setBounds(this.game.getWidth() - this.game.getButtonWidth() * 2.0F, this.game.getHeight() - this.game.getButtonHeight() * 2.0F, this.game.getButtonWidth(), this.game.getButtonHeight());
      ((TextButton)localObject).addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          paramAnonymousInputEvent = LightTest.this;
          paramAnonymousInputEvent.strength += 0.1F;
          if (LightTest.this.strength > 1.0F) {
            LightTest.this.strength = 1.0F;
          }
        }
      });
      this.stageui.addActor((Actor)localObject);
      localObject = new TextButton("Distance [+]", Assets.skin);
      ((TextButton)localObject).setBounds(this.game.getWidth() - this.game.getButtonWidth() * 2.0F, this.game.getHeight() - this.game.getButtonHeight() * 3.0F, this.game.getButtonWidth(), this.game.getButtonHeight());
      ((TextButton)localObject).addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          paramAnonymousInputEvent = LightTest.this.lightPos;
          paramAnonymousInputEvent.z -= 0.02F;
          if (LightTest.this.lightPos.z < 0.02F) {
            LightTest.this.lightPos.z = 0.02F;
          }
        }
      });
      this.stageui.addActor((Actor)localObject);
      localObject = new TextButton("Distance [-]", Assets.skin);
      ((TextButton)localObject).setBounds(this.game.getWidth() - this.game.getButtonWidth(), this.game.getHeight() - this.game.getButtonHeight() * 3.0F, this.game.getButtonWidth(), this.game.getButtonHeight());
      ((TextButton)localObject).addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          paramAnonymousInputEvent = LightTest.this.lightPos;
          paramAnonymousInputEvent.z += 0.02F;
          if (LightTest.this.lightPos.z > 0.2F) {
            LightTest.this.lightPos.z = 0.2F;
          }
        }
      });
      this.stageui.addActor((Actor)localObject);
      localObject = new TextButton("Ambience [-]", Assets.skin);
      ((TextButton)localObject).setBounds(this.game.getWidth() - this.game.getButtonWidth(), this.game.getHeight() - this.game.getButtonHeight() * 4.0F, this.game.getButtonWidth(), this.game.getButtonHeight());
      ((TextButton)localObject).addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          paramAnonymousInputEvent = LightTest.this;
          paramAnonymousInputEvent.ambientIntensity -= 0.1F;
          if (LightTest.this.ambientIntensity < -0.8F) {
            LightTest.this.ambientIntensity = -0.8F;
          }
        }
      });
      this.stageui.addActor((Actor)localObject);
      localObject = new TextButton("Ambience [+]", Assets.skin);
      ((TextButton)localObject).setBounds(this.game.getWidth() - this.game.getButtonWidth() * 2.0F, this.game.getHeight() - this.game.getButtonHeight() * 4.0F, this.game.getButtonWidth(), this.game.getButtonHeight());
      ((TextButton)localObject).addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          paramAnonymousInputEvent = LightTest.this;
          paramAnonymousInputEvent.ambientIntensity += 0.1F;
          if (LightTest.this.ambientIntensity > 1.2F) {
            LightTest.this.ambientIntensity = 1.2F;
          }
        }
      });
      this.stageui.addActor((Actor)localObject);
      localObject = new TextButton("Amb. Colour", Assets.skin);
      ((TextButton)localObject).setBounds(this.game.getWidth() - this.game.getButtonWidth(), this.game.getHeight() - this.game.getButtonHeight() * 5.0F, this.game.getButtonWidth(), this.game.getButtonHeight());
      ((TextButton)localObject).addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          LightTest.this.program.begin();
          LightTest.this.program.setUniformf("ambientColor", LightTest.this.rndColor());
          LightTest.this.program.end();
        }
      });
      this.stageui.addActor((Actor)localObject);
      localObject = new TextButton("Light Colour", Assets.skin);
      ((TextButton)localObject).setBounds(this.game.getWidth() - this.game.getButtonWidth() * 2.0F, this.game.getHeight() - this.game.getButtonHeight() * 5.0F, this.game.getButtonWidth(), this.game.getButtonHeight());
      ((TextButton)localObject).addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          LightTest.this.program.begin();
          LightTest.this.program.setUniformf("lightColor", LightTest.this.rndColor());
          LightTest.this.program.end();
        }
      });
      this.stageui.addActor((Actor)localObject);
      localObject = new TextButton("Back", Assets.skin);
      ((TextButton)localObject).setBounds(0.0F, this.game.getHeight() - this.game.getButtonHeight() * 2.0F, this.game.getButtonWidth(), this.game.getButtonHeight());
      ((TextButton)localObject).addListener(new ClickListener()
      {
        public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
        {
          LightTest.this.game.setScreen(new MainMenu(LightTest.this.game));
        }
      });
      this.stageui.addActor((Actor)localObject);
      this.lightPos.x = 400.0F;
      this.lightPos.y = 200.0F;
      return;
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\tests\LightTest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */