package com.johnathongoss.libgdxtests.examples;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.johnathongoss.libgdxtests.Assets;
import com.johnathongoss.libgdxtests.ImageCache;
import com.johnathongoss.libgdxtests.MyGame;
import com.johnathongoss.libgdxtests.MyInputProcessor;
import com.johnathongoss.libgdxtests.entities.MyTimer;
import com.johnathongoss.libgdxtests.entities.SpeechBubble;
import com.johnathongoss.libgdxtests.screens.Examples;
import java.util.Iterator;

public class Talking
  implements Screen
{
  private Talker TBrian;
  private Talker TBrick;
  private Talker TRon;
  private Talker TVeronica;
  private Talker TWorker;
  private TextButton backButton;
  private SpriteBatch batch;
  private String[] brickLines = { "That's a good one.", "Brian Fantana.", "Brian.", "Veronica.", "I don't know what we're yelling about!", "Loud noises!", "Yeah, I stabbed a man in the heart.", "I killed a guy with a trident.", "I don't know.", "I ate a big, red candle.", "Cough. Look over here.", "I would like to extend to you an invitation to the pants party.", "Party with pants?", "All right. Let's go.", "Fantastic.", "I'm riding a furry tractor.", "I love... carpet.", "I love lamp.", "I love... desk.", "Bears can smell the menstruation.", "High pressure systems..." };
  MyTimer brickTimer;
  private Array<Speech> conversation;
  private MyGame game;
  MyInputProcessor input = new MyInputProcessor()
  {
    public boolean keyUp(int paramAnonymousInt)
    {
      if ((paramAnonymousInt == 4) || (paramAnonymousInt == 67) || (paramAnonymousInt == 131)) {
        Talking.this.game.setScreen(new Examples(Talking.this.game));
      }
      return false;
    }
    
    public boolean touchDown(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
    {
      Talking.this.stageBrick.clear();
      SpeechBubble localSpeechBubble = (SpeechBubble)Talking.this.speechBubblePool.obtain();
      localSpeechBubble.init(Talking.this.brickLines[MathUtils.random(0, Talking.this.brickLines.length - 1)], Talking.this.TBrick.getX(), Talking.this.TBrick.getY() + Talking.this.TBrick.getHeight(), Talking.this.TBrick.getPic());
      localSpeechBubble.setColor(Talking.this.TBrick.getColor());
      localSpeechBubble.setFollow(Talking.this.TBrick);
      Talking.this.stageBrick.addActor(localSpeechBubble);
      return false;
    }
  };
  private int lineIndex = 0;
  private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
  private final Pool<SpeechBubble> speechBubblePool = new Pool()
  {
    protected SpeechBubble newObject()
    {
      return new SpeechBubble();
    }
  };
  private Stage stage;
  private Stage stageBrick;
  private Stage stageui;
  
  public Talking(MyGame paramMyGame)
  {
    this.game = paramMyGame;
    this.stage = new Stage();
    this.stageui = new Stage();
    this.stageBrick = new Stage();
  }
  
  private void cAdd(Talker paramTalker, String paramString)
  {
    this.conversation.add(new Speech(paramTalker, paramString));
  }
  
  private void checkSpeechBubbles()
  {
    Iterator localIterator = this.stage.getActors().iterator();
    if (!localIterator.hasNext()) {
      localIterator = this.stageBrick.getActors().iterator();
    }
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        return;
        localActor = (Actor)localIterator.next();
        if ((!(localActor instanceof SpeechBubble)) || (((SpeechBubble)localActor).isAlive())) {
          break;
        }
        this.stage.getActors().removeValue(localActor, true);
        this.speechBubblePool.free((SpeechBubble)localActor);
        nextLine();
        break;
      }
      Actor localActor = (Actor)localIterator.next();
      if (((localActor instanceof SpeechBubble)) && (!((SpeechBubble)localActor).isAlive()))
      {
        this.stageBrick.getActors().removeValue(localActor, true);
        this.speechBubblePool.free((SpeechBubble)localActor);
      }
    }
  }
  
  private void nextLine()
  {
    ((Speech)this.conversation.get(this.lineIndex)).Say(this.stage);
    this.lineIndex += 1;
    if (this.lineIndex == this.conversation.size) {
      this.lineIndex = 0;
    }
  }
  
  public void dispose()
  {
    this.batch.dispose();
    this.stage.dispose();
    this.stageui.dispose();
    this.stageBrick.dispose();
    this.skin.dispose();
  }
  
  public void hide()
  {
    dispose();
  }
  
  public void pause() {}
  
  public void render(float paramFloat)
  {
    Gdx.gl.glClear(16384);
    this.batch.begin();
    Assets.font24.drawMultiLine(this.batch, "Talking Example |", 0.0F, 24.0F, this.game.getWidth(), BitmapFont.HAlignment.RIGHT);
    this.batch.end();
    this.stage.act(paramFloat);
    checkSpeechBubbles();
    this.stage.draw();
    this.stageBrick.act(paramFloat);
    this.stageBrick.draw();
    this.stageui.act(paramFloat);
    this.stageui.draw();
    this.brickTimer.update(paramFloat);
  }
  
  public void resize(int paramInt1, int paramInt2)
  {
    this.backButton.setBounds(0.0F, paramInt2 - this.game.getButtonHeight() * 2.0F, this.game.getButtonWidth(), this.game.getButtonHeight());
  }
  
  public void resume() {}
  
  public void show()
  {
    this.game.showAds(true);
    InputMultiplexer localInputMultiplexer = new InputMultiplexer(new InputProcessor[] { this.stageui, this.stage, this.input });
    Gdx.input.setInputProcessor(localInputMultiplexer);
    Gdx.input.setCatchBackKey(true);
    this.backButton = new TextButton("Back", this.skin);
    this.backButton.setBounds(0.0F, this.game.getHeight() - this.game.getButtonHeight() * 2.0F, this.game.getButtonWidth(), this.game.getButtonHeight());
    this.backButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        Talking.this.game.setScreen(new Examples(Talking.this.game));
      }
    });
    this.stageui.addActor(this.backButton);
    this.conversation = new Array();
    this.TBrian = new Talker("brick", ImageCache.getTexture("brian"), new Color(0.1F, 0.45F, 1.0F, 0.8F), this.game.getWidth() / 4.0F, this.game.getHeight() / 2.0F);
    this.TRon = new Talker("brick", ImageCache.getTexture("ron"), new Color(0.9F, 0.65F, 0.1F, 0.8F), 0.75F * this.game.getWidth(), this.game.getHeight() / 2.0F);
    this.TVeronica = new Talker("brick", ImageCache.getTexture("veronica"), new Color(1.0F, 0.1F, 0.8F, 0.8F), this.game.getWidth() / 2.0F, this.game.getHeight() / 4.0F);
    this.TWorker = new Talker("brick", ImageCache.getTexture("worker"), new Color(0.5F, 0.35F, 0.3F, 0.8F), this.game.getWidth() / 2.0F, 0.75F * this.game.getHeight());
    this.TBrick = new Talker("brick", ImageCache.getTexture("brick"), new Color(0.3F, 0.3F, 0.3F, 0.8F), MathUtils.random(0.0F, this.game.getWidth()), MathUtils.random(0.0F, this.game.getHeight()));
    this.brickTimer = new MyTimer(MathUtils.random(0.0F, 0.0F))
    {
      protected void perform()
      {
        Talking.this.TBrick.setTarget(new Vector2(MathUtils.random(0.0F, Talking.this.game.getWidth()), MathUtils.random(0.0F, Talking.this.game.getHeight())));
        Talking.this.brickTimer.setCap(MathUtils.random(4.0F, 10.0F));
      }
    };
    this.brickTimer.setRepeating(true);
    this.brickTimer.start();
    this.stage.addActor(this.TBrick);
    this.stage.addActor(this.TBrian);
    this.stage.addActor(this.TRon);
    this.stage.addActor(this.TVeronica);
    this.stage.addActor(this.TWorker);
    cAdd(this.TBrian, "Well,");
    cAdd(this.TBrian, "I'll give this little cookie an hour before we're doing the no-pants dance.");
    cAdd(this.TBrian, "Time to musk up.");
    cAdd(this.TRon, "Wow.");
    cAdd(this.TRon, "Never ceases to amaze me.");
    cAdd(this.TRon, "What cologne are you gonna go with?");
    cAdd(this.TRon, "London Gentleman or...");
    cAdd(this.TRon, "Wait.");
    cAdd(this.TRon, "No, no, no.");
    cAdd(this.TRon, "Hold on.");
    cAdd(this.TRon, "Blackbeard's Delight?");
    cAdd(this.TBrian, "No.");
    cAdd(this.TBrian, "She gets a special cologne.");
    cAdd(this.TBrian, "It's called Sex Panther by Odeon.");
    cAdd(this.TBrian, "It's illegal in nine countries.");
    cAdd(this.TBrian, "Yep.");
    cAdd(this.TBrian, "It's made with bits of real panther.");
    cAdd(this.TBrian, "So you know it's good.");
    cAdd(this.TRon, "It's quite pungent.");
    cAdd(this.TBrian, "Oh yeah.");
    cAdd(this.TRon, "It's a formidable scent.");
    cAdd(this.TRon, "It stings the nostrils.");
    cAdd(this.TRon, "In a good way.");
    cAdd(this.TBrian, "Yeah.");
    cAdd(this.TRon, "Brian,");
    cAdd(this.TRon, "I'm gonna be honest with you.");
    cAdd(this.TRon, "That smells like pure gasoline.");
    cAdd(this.TBrian, "They've done studies, you know?");
    cAdd(this.TBrian, "60% of the time, it works every time.");
    cAdd(this.TRon, "That doesn't make any sense.");
    cAdd(this.TBrian, "Well,");
    cAdd(this.TBrian, "let's go see if we can make this little kitty purr.");
    cAdd(this.TBrian, "Hey, sweet cheeks.");
    cAdd(this.TBrian, "Got an invite I'd like to extend your way.");
    cAdd(this.TVeronica, "My God.");
    cAdd(this.TVeronica, "What is that smell?");
    cAdd(this.TVeronica, "Oh!");
    cAdd(this.TBrian, "That's the smell of desire, my lady.");
    cAdd(this.TVeronica, "God no.");
    cAdd(this.TVeronica, "It smells like -");
    cAdd(this.TVeronica, "- like a used diaper filled with Indian food.");
    cAdd(this.TVeronica, "Oh!");
    cAdd(this.TVeronica, "Excuse me.");
    cAdd(this.TBrian, "You know,");
    cAdd(this.TBrian, "desire smells like that to some people.");
    cAdd(this.TWorker, "What is that?");
    cAdd(this.TWorker, "It smells like a turd covered in burnt hair!");
    cAdd(this.TBrian, "Oh.");
    this.batch = new SpriteBatch();
    this.lineIndex = 0;
    nextLine();
  }
  
  class Speech
  {
    Talking.Talker talker;
    String text;
    
    public Speech(Talking.Talker paramTalker, String paramString)
    {
      this.talker = paramTalker;
      this.text = paramString;
    }
    
    public void Say(Stage paramStage)
    {
      SpeechBubble localSpeechBubble = (SpeechBubble)Talking.this.speechBubblePool.obtain();
      localSpeechBubble.init(this.text, this.talker.getX(), this.talker.getY(), this.talker.getPic());
      localSpeechBubble.setFollow(this.talker);
      localSpeechBubble.setColor(this.talker.getColor());
      paramStage.addActor(localSpeechBubble);
    }
  }
  
  class Talker
    extends Actor
  {
    Animation aWalk_d;
    Animation aWalk_l;
    Animation aWalk_r;
    Animation aWalk_u;
    Color color;
    private TextureRegion currentFrame;
    private boolean moving = false;
    private String name;
    TextureRegion pic;
    Vector2 position;
    private Array<Sprite> sprites;
    Vector2 target;
    private float time = 0.0F;
    Vector2 velocity;
    private float walkSpeed = 1.5F;
    
    Talker(String paramString, TextureRegion paramTextureRegion, Color paramColor, float paramFloat1, float paramFloat2)
    {
      this.pic = paramTextureRegion;
      this.name = paramString;
      this.sprites = Assets.spriteSheet.createSprites(paramString + "_d");
      this.aWalk_d = new Animation(0.1F / this.walkSpeed, this.sprites, 2);
      this.sprites = Assets.spriteSheet.createSprites(paramString + "_u");
      this.aWalk_u = new Animation(0.1F / this.walkSpeed, this.sprites, 2);
      this.sprites = Assets.spriteSheet.createSprites(paramString + "_l");
      this.aWalk_l = new Animation(0.1F / this.walkSpeed, this.sprites, 2);
      this.sprites = Assets.spriteSheet.createSprites(paramString + "_r");
      this.aWalk_r = new Animation(0.1F / this.walkSpeed, this.sprites, 2);
      this.position = new Vector2();
      this.target = new Vector2();
      this.velocity = new Vector2();
      setPosition(new Vector2(paramFloat1, paramFloat2));
      setHeight(64.0F);
      this.color = paramColor;
    }
    
    public void act(float paramFloat)
    {
      super.act(paramFloat);
      this.time += paramFloat;
      if ((this.velocity.y > 0.0F) && (Math.abs(this.velocity.y) >= Math.abs(this.velocity.x)))
      {
        this.currentFrame = this.aWalk_u.getKeyFrame(this.time);
        if (!this.moving) {
          this.currentFrame = this.aWalk_d.getKeyFrame(0.0F);
        }
        if ((this.velocity.x > 0.0F) && (this.position.x > this.target.x)) {
          this.velocity.x = 0.0F;
        }
        if ((this.velocity.x < 0.0F) && (this.position.x < this.target.x)) {
          this.velocity.x = 0.0F;
        }
        if ((this.velocity.y > 0.0F) && (this.position.y > this.target.y)) {
          this.velocity.y = 0.0F;
        }
        if ((this.velocity.y < 0.0F) && (this.position.y < this.target.y)) {
          this.velocity.y = 0.0F;
        }
        if (this.velocity.len() != 0.0F) {
          break label411;
        }
        this.moving = false;
        this.time = 0.0F;
      }
      for (;;)
      {
        if (this.moving) {
          this.position.add(this.velocity);
        }
        return;
        if ((this.velocity.y < 0.0F) && (Math.abs(this.velocity.y) >= Math.abs(this.velocity.x)))
        {
          this.currentFrame = this.aWalk_d.getKeyFrame(this.time);
          break;
        }
        if (this.velocity.x > 0.0F)
        {
          this.currentFrame = this.aWalk_r.getKeyFrame(this.time);
          break;
        }
        if (this.velocity.x < 0.0F)
        {
          this.currentFrame = this.aWalk_l.getKeyFrame(this.time);
          break;
        }
        this.currentFrame = this.aWalk_d.getKeyFrame(this.time);
        break;
        label411:
        this.moving = true;
      }
    }
    
    public void draw(Batch paramBatch, float paramFloat)
    {
      paramBatch.draw(this.currentFrame, this.position.x - 32.0F, this.position.y);
    }
    
    public Color getColor()
    {
      return this.color;
    }
    
    public String getName()
    {
      return this.name;
    }
    
    public TextureRegion getPic()
    {
      return this.pic;
    }
    
    public float getX()
    {
      return this.position.x;
    }
    
    public float getY()
    {
      return this.position.y;
    }
    
    public void setColor(Color paramColor)
    {
      this.color = paramColor;
    }
    
    public void setPosition(Vector2 paramVector2)
    {
      this.position = paramVector2;
    }
    
    public void setTarget(Vector2 paramVector2)
    {
      this.target = paramVector2;
      paramVector2 = new Vector2(paramVector2);
      paramVector2.scl(1.0F / paramVector2.sub(this.position).len());
      this.velocity = paramVector2.scl(this.walkSpeed);
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\examples\Talking.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */