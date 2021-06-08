package me.viyaan.tingtong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import me.viyaan.tingtong.objects.Ball;
import me.viyaan.tingtong.objects.Player;
import me.viyaan.tingtong.objects.PlayerAI;
import me.viyaan.tingtong.objects.Wall;

public class GameScreen extends ScreenAdapter {
    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final World world;
    private final Box2DDebugRenderer box2DDebugRenderer;
    private final GameContactListener gameContactListener;
    // game objects here
    private final Player player;
    private final PlayerAI playerAI;
    private final Ball ball;
    private final Wall wallTop, wallBottom;
    private final TextureRegion[] numbers;
    public GameScreen(OrthographicCamera camera){
        this.camera = camera;
        this.camera.position.set(new Vector3(TingTong.INSTANCE.getScreenWidth()/2, TingTong.INSTANCE.getScreenHeight()/2, 0));
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0, 0), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        this.player = new Player(16, TingTong.INSTANCE.getScreenHeight()/2, this);
        this.gameContactListener = new GameContactListener(this);
        this.world.setContactListener(this.gameContactListener);
        this.playerAI = new PlayerAI(TingTong.INSTANCE.getScreenWidth() - 16, TingTong.INSTANCE.getScreenHeight()/2, this);
        this.ball = new Ball(this);
        this.wallTop = new Wall(32,this);
        this.wallBottom = new Wall(TingTong.INSTANCE.getScreenHeight() - 32, this);
        this.numbers = loadTextureSprite("numbers.png", 10);
    }

    public World getWorld() {
        return world;
    }

    public void update(){
        world.step(1/60f, 6, 2);
        batch.setProjectionMatrix(camera.combined);
        this.camera.update();
        this.player.update();
        this.playerAI.update();
        this.ball.update();
        batch.setProjectionMatrix(camera.combined);
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)){
            this.ball.reset();
            this.player.setScore(0);
            this.playerAI.setScore(0);
        }
    }

    public Ball getBall() {
        return ball;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerAI getPlayerAI() {
        return playerAI;
    }

    @Override
    public void render(float delta) {
        update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //to debug the game comment these lines btw to comment do // before the line starts that then u can debug the renderer (this will be useful if ur making mods or making new objects)
/*comment this*/ this.player.render(batch);
/*comment this*/ this.playerAI.render(batch);
/*comment this*/ this.ball.render(batch);
/*comment this*/ this.wallTop.render(batch);
/*comment this*/ this.wallBottom.render(batch);
/*don't do anything with this*/ drawNumbers(batch, player.getScore(), 64, TingTong.INSTANCE.getScreenHeight() - 55, 30, 42);
/*don't do anything with this*/ drawNumbers(batch, playerAI.getScore(), TingTong.INSTANCE.getScreenWidth() - 96, TingTong.INSTANCE.getScreenHeight() - 55, 30, 42);
        batch.end();
        /*uncomment this*/ //this.box2DDebugRenderer.render(world, camera.combined.scl(Const.PPM));
    }
    private void drawNumbers(SpriteBatch batch, int number, float x, float y, float width, float height){
        if(number < 10){
            batch.draw(numbers[number], x, y, width, height);
        }else{
            batch.draw(numbers[Integer.parseInt((""+number).substring(0,1))], x, y, width, height);
        }
    }
    private TextureRegion[] loadTextureSprite(String filename, int colums){
        Texture texture = new Texture(filename);
        return TextureRegion.split(texture, texture.getWidth()/colums, texture.getHeight())[0];
    }
}
