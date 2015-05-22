package com.johnathongoss.libgdxtests.tests;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.johnathongoss.libgdxtests.ImageCache;
import com.johnathongoss.libgdxtests.MyGame;
import com.johnathongoss.libgdxtests.MyInputProcessor;
import com.johnathongoss.libgdxtests.screens.MainMenu;
import java.util.Iterator;

public class Box2D
  extends BlankTestScreen
  implements GestureDetector.GestureListener
{
  static final int BOX_POSITION_ITERATIONS = 2;
  static final float BOX_STEP = 0.016666668F;
  static final int BOX_VELOCITY_ITERATIONS = 6;
  static final float BOX_WORLD_TO = 50.0F;
  static final float WORLD_TO_BOX = 0.02F;
  Array<Body> bodies;
  QueryCallback callback = new QueryCallback()
  {
    public boolean reportFixture(Fixture paramAnonymousFixture)
    {
      if (paramAnonymousFixture.testPoint(Box2D.this.testPoint.x, Box2D.this.testPoint.y))
      {
        Box2D.this.hitBody = paramAnonymousFixture.getBody();
        return false;
      }
      return true;
    }
  };
  protected TextureRegion circle = ImageCache.getTexture("circle");
  Box2DDebugRenderer debugRenderer;
  protected Body groundBody;
  protected Body hitBody = null;
  MyInputProcessor input = new MyInputProcessor()
  {
    public boolean keyUp(int paramAnonymousInt)
    {
      if ((paramAnonymousInt == 4) || (paramAnonymousInt == 67) || (paramAnonymousInt == 131)) {
        Box2D.this.game.setScreen(new MainMenu(Box2D.this.game));
      }
      return false;
    }
    
    public boolean touchDown(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
    {
      return false;
    }
    
    public boolean touchDragged(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      return false;
    }
    
    public boolean touchUp(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
    {
      Gdx.app.log("", "up");
      if (Box2D.this.mouseJoint != null)
      {
        Box2D.this.world.destroyJoint(Box2D.this.mouseJoint);
        Box2D.this.mouseJoint = null;
      }
      return false;
    }
  };
  protected MouseJoint mouseJoint = null;
  private boolean showDebug = false;
  protected Sprite sprite;
  Vector2 target = new Vector2();
  Vector3 testPoint = new Vector3();
  protected Vector2 tmp = new Vector2();
  World world;
  
  public Box2D(MyGame paramMyGame)
  {
    super(paramMyGame);
    this.testName = "Box2D Test |";
  }
  
  private void creatBox(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, BodyDef paramBodyDef, FixtureDef paramFixtureDef)
  {
    paramBodyDef.position.set(0.02F * paramFloat1, 0.02F * paramFloat2);
    paramBodyDef.angularVelocity = 0.0F;
    PolygonShape localPolygonShape = new PolygonShape();
    localPolygonShape.setAsBox(0.02F * paramFloat3 / 2.0F, 0.02F * paramFloat4 / 2.0F);
    paramFixtureDef.shape = localPolygonShape;
    paramFixtureDef.density = paramFloat5;
    paramFixtureDef.friction = paramFloat6;
    paramFixtureDef.restitution = paramFloat7;
    Sprite localSprite = new Sprite(ImageCache.getTexture("background"));
    localSprite.setSize(0.02F * paramFloat3, 0.02F * paramFloat4);
    localSprite.setOrigin(localSprite.getWidth() / 2.0F, localSprite.getHeight() / 2.0F);
    paramBodyDef = this.world.createBody(paramBodyDef);
    paramBodyDef.setUserData(localSprite);
    paramBodyDef.createFixture(paramFixtureDef);
    localPolygonShape.dispose();
  }
  
  private void createBall(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, BodyDef paramBodyDef, FixtureDef paramFixtureDef)
  {
    paramBodyDef.type = BodyDef.BodyType.DynamicBody;
    paramBodyDef.position.x = (paramFloat1 * 0.02F);
    paramBodyDef.position.y = (paramFloat2 * 0.02F);
    paramBodyDef.angularVelocity = 0.0F;
    CircleShape localCircleShape = new CircleShape();
    localCircleShape.setRadius(paramFloat3 * 0.02F);
    paramFixtureDef.shape = localCircleShape;
    paramFixtureDef.density = 2.5F;
    paramFixtureDef.restitution = 0.8F;
    paramFixtureDef.friction = 0.25F;
    Sprite localSprite = new Sprite(ImageCache.getTexture("circle"));
    localSprite.setSize(paramFloat3 * 2.0F * 0.02F, paramFloat3 * 2.0F * 0.02F);
    localSprite.setOrigin(localSprite.getWidth() / 2.0F, localSprite.getHeight() / 2.0F);
    paramBodyDef = this.world.createBody(paramBodyDef);
    paramBodyDef.setUserData(localSprite);
    paramBodyDef.createFixture(paramFixtureDef);
    localCircleShape.dispose();
  }
  
  private void createScene(BodyDef paramBodyDef, FixtureDef paramFixtureDef)
  {
    int i = 0;
    for (;;)
    {
      if (i >= 25) {
        return;
      }
      createBall(MathUtils.random(0, 3), MathUtils.random(0, 3), MathUtils.random(0.2F, 1.0F), 0.8F, 0.1F, 0.82F, paramBodyDef, paramFixtureDef);
      i += 1;
    }
  }
  
  private void createWall(Vector2 paramVector21, Vector2 paramVector22, float paramFloat1, float paramFloat2, float paramFloat3, BodyDef paramBodyDef, FixtureDef paramFixtureDef)
  {
    paramBodyDef.type = BodyDef.BodyType.StaticBody;
    EdgeShape localEdgeShape = new EdgeShape();
    localEdgeShape.set(paramVector21.x * 0.02F, paramVector21.y * 0.02F, paramVector22.x * 0.02F, paramVector22.y * 0.02F);
    paramFixtureDef.shape = localEdgeShape;
    paramFixtureDef.density = paramFloat1;
    paramFixtureDef.friction = paramFloat2;
    paramFixtureDef.restitution = paramFloat3;
    this.world.createBody(paramBodyDef).createFixture(paramFixtureDef);
    localEdgeShape.dispose();
  }
  
  public void dispose()
  {
    super.dispose();
    this.debugRenderer.dispose();
    this.world.dispose();
  }
  
  public boolean fling(float paramFloat1, float paramFloat2, int paramInt)
  {
    return false;
  }
  
  public boolean keyDown(int paramInt)
  {
    return false;
  }
  
  public boolean keyTyped(char paramChar)
  {
    return false;
  }
  
  public boolean keyUp(int paramInt)
  {
    if (paramInt == 4) {
      this.game.setScreen(new MainMenu(this.game));
    }
    return false;
  }
  
  public boolean longPress(float paramFloat1, float paramFloat2)
  {
    return false;
  }
  
  public boolean mouseMoved(int paramInt1, int paramInt2)
  {
    return false;
  }
  
  public boolean pan(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    if (this.mouseJoint != null)
    {
      this.cam.unproject(this.testPoint.set(paramFloat1, paramFloat2, 0.0F));
      this.mouseJoint.setTarget(this.target.set(this.testPoint.x, this.testPoint.y));
    }
    return false;
  }
  
  public boolean panStop(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    return false;
  }
  
  public boolean pinch(Vector2 paramVector21, Vector2 paramVector22, Vector2 paramVector23, Vector2 paramVector24)
  {
    return false;
  }
  
  public void render(float paramFloat)
  {
    Gdx.gl.glClear(16384);
    this.world.getBodies(this.bodies);
    this.batch.begin();
    Iterator localIterator = this.bodies.iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        this.batch.end();
        if (this.showDebug) {
          this.debugRenderer.render(this.world, this.cam.combined);
        }
        this.stageui.act(paramFloat);
        this.stageui.draw();
        this.batchui.begin();
        renderTestName(this.batchui);
        this.batchui.end();
        this.world.step(paramFloat, 6, 2);
        return;
      }
      Body localBody = (Body)localIterator.next();
      if ((localBody.getUserData() != null) && ((localBody.getUserData() instanceof Sprite)))
      {
        Sprite localSprite = (Sprite)localBody.getUserData();
        localSprite.setPosition(localBody.getPosition().x - localSprite.getWidth() / 2.0F, localBody.getPosition().y - localSprite.getHeight() / 2.0F);
        localSprite.setRotation(localBody.getAngle() * 57.295776F);
        localSprite.draw(this.batch);
      }
    }
  }
  
  protected void renderText() {}
  
  public void resize(int paramInt1, int paramInt2)
  {
    super.resize(paramInt1, paramInt2);
    this.cam.viewportWidth = paramInt1;
    this.cam.viewportHeight = paramInt2;
    this.cam.position.x = (paramInt1 * 0.02F / 2.0F);
    this.cam.position.y = (paramInt2 * 0.02F / 2.0F);
    this.cam.zoom = 0.02F;
    this.cam.update();
    this.batch.setProjectionMatrix(this.cam.combined);
  }
  
  public boolean scrolled(int paramInt)
  {
    return false;
  }
  
  public void show()
  {
    this.game.showAds(true);
    this.debugButton = new TextButton("Debug", this.skin);
    this.debugButton.setBounds(this.width - this.BUTTON_WIDTH, this.height - this.BUTTON_HEIGHT * 2.0F, this.BUTTON_WIDTH, this.BUTTON_HEIGHT);
    this.debugButton.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        paramAnonymousInputEvent = Box2D.this;
        if (Box2D.this.showDebug) {}
        for (boolean bool = false;; bool = true)
        {
          paramAnonymousInputEvent.showDebug = bool;
          return;
        }
      }
    });
    this.stageui.addActor(this.debugButton);
    this.bodies = new Array();
    addBackButton();
    Object localObject = new InputMultiplexer(new InputProcessor[] { this.stageui, this.stage, new GestureDetector(this), this.input });
    Gdx.input.setInputProcessor((InputProcessor)localObject);
    Gdx.input.setCatchBackKey(true);
    this.sprite = new Sprite(this.circle);
    this.batch = new SpriteBatch();
    this.debugRenderer = new Box2DDebugRenderer();
    this.cam = new OrthographicCamera();
    this.world = new World(new Vector2(0.0F, -9.81F), true);
    this.world.setContactListener(new ContactListener()
    {
      public void beginContact(Contact paramAnonymousContact)
      {
        Gdx.app.log("", "Contact");
      }
      
      public void endContact(Contact paramAnonymousContact)
      {
        paramAnonymousContact = new Vector2();
        paramAnonymousContact.x = MathUtils.random(-3.0F, 3.0F);
        paramAnonymousContact.y = MathUtils.random(-1.0F, 1.0F);
      }
      
      public void postSolve(Contact paramAnonymousContact, ContactImpulse paramAnonymousContactImpulse) {}
      
      public void preSolve(Contact paramAnonymousContact, Manifold paramAnonymousManifold) {}
    });
    localObject = new BodyDef();
    this.groundBody = this.world.createBody((BodyDef)localObject);
    FixtureDef localFixtureDef = new FixtureDef();
    createWall(new Vector2(0.0F, 0.0F), new Vector2(this.width, 0.0F), 1.0F, 0.2F, 0.8F, (BodyDef)localObject, localFixtureDef);
    createWall(new Vector2(0.0F, 0.0F), new Vector2(0.0F, this.height), 1.0F, 0.2F, 0.8F, (BodyDef)localObject, localFixtureDef);
    createWall(new Vector2(this.width, 0.0F), new Vector2(this.width, this.height), 1.0F, 0.2F, 0.8F, (BodyDef)localObject, localFixtureDef);
    createWall(new Vector2(0.0F, this.height), new Vector2(this.width, this.height), 1.0F, 0.2F, 0.8F, (BodyDef)localObject, localFixtureDef);
    int i = 0;
    if (i >= 12) {
      i = 0;
    }
    for (;;)
    {
      if (i >= 3)
      {
        return;
        createBall(MathUtils.random(0.0F, this.width), MathUtils.random(0.0F, this.height), MathUtils.random(this.width / 15.0F, this.width / 30.0F), 1.0F, 0.0F, 0.9F, (BodyDef)localObject, localFixtureDef);
        i += 1;
        break;
      }
      creatBox(MathUtils.random(0.0F, this.width), MathUtils.random(0.0F, this.height), MathUtils.random(this.width / 7.0F, this.height / 10.0F), MathUtils.random(this.width / 7.0F, this.height / 10.0F), 1.0F, 0.3F, 0.8F, (BodyDef)localObject, localFixtureDef);
      i += 1;
    }
  }
  
  public boolean tap(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    Gdx.app.log("", "touchdown ");
    return false;
  }
  
  public boolean touchDown(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    this.cam.unproject(this.testPoint.set(paramFloat1, paramFloat2, 0.0F));
    this.hitBody = null;
    this.world.QueryAABB(this.callback, this.testPoint.x - 1.0E-4F, this.testPoint.y - 1.0E-4F, this.testPoint.x + 1.0E-4F, this.testPoint.y + 1.0E-4F);
    if (this.hitBody == this.groundBody) {
      this.hitBody = null;
    }
    if ((this.hitBody != null) && (this.hitBody.getType() == BodyDef.BodyType.KinematicBody)) {}
    while (this.hitBody == null) {
      return false;
    }
    MouseJointDef localMouseJointDef = new MouseJointDef();
    localMouseJointDef.bodyA = this.groundBody;
    localMouseJointDef.bodyB = this.hitBody;
    localMouseJointDef.collideConnected = true;
    localMouseJointDef.target.set(this.testPoint.x, this.testPoint.y);
    localMouseJointDef.maxForce = (1000.0F * this.hitBody.getMass());
    this.mouseJoint = ((MouseJoint)this.world.createJoint(localMouseJointDef));
    this.hitBody.setAwake(true);
    return false;
  }
  
  public boolean touchDown(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return false;
  }
  
  public boolean touchDragged(int paramInt1, int paramInt2, int paramInt3)
  {
    return false;
  }
  
  public boolean touchUp(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Gdx.app.log("", "up");
    if (this.mouseJoint != null)
    {
      this.world.destroyJoint(this.mouseJoint);
      this.mouseJoint = null;
    }
    return false;
  }
  
  protected void updateText() {}
  
  public boolean zoom(float paramFloat1, float paramFloat2)
  {
    return false;
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\johnathongoss\libgdxtests\tests\Box2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */