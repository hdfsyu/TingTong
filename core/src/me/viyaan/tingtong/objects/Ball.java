package me.viyaan.tingtong.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import me.viyaan.tingtong.GameScreen;
import me.viyaan.tingtong.TingTong;
import me.viyaan.tingtong.objects.helper.BodyHelper;
import me.viyaan.tingtong.objects.helper.Const;
import me.viyaan.tingtong.objects.helper.ContactType;

public class Ball {
    private Body body;
    private float x, y, speed, velX, velY;
    private int width, height;
    private GameScreen gameScreen;
    private Texture texture;
    public Ball(GameScreen gameScreen){
        this.x = TingTong.INSTANCE.getScreenWidth()/2;
        this.y = TingTong.INSTANCE.getScreenHeight()/2;
        this.speed = 5;
        this.velX = getRandomDirection();
        this.velY = getRandomDirection();
        this.texture = new Texture("color.png");
        this.gameScreen = gameScreen;
        this.width = 32;
        this.height = 32;
        this.body = BodyHelper.createBody(x, y, width, height, false, 0, gameScreen.getWorld(), ContactType.BALL);
    }
    private float getRandomDirection(){
        return (Math.random() < 0.5) ? 1 : -1;
    }
    public void update(){
        x = body.getPosition().x * Const.PPM - (width/2);
        y = body.getPosition().y * Const.PPM - (height/2);
        this.body.setLinearVelocity(velX * speed, velY * speed);
        // score
        if(x < 0){
            gameScreen.getPlayerAI().score();
            reset();
        }
        if(x > TingTong.INSTANCE.getScreenWidth()){
            gameScreen.getPlayer().score();
            reset();
        }
    }

    public float getY() {
        return y;
    }

    public void reset(){
        this.velX = this.getRandomDirection();
        this.velY = this.getRandomDirection();
        this.speed = 5;
        this.body.setTransform(TingTong.INSTANCE.getScreenWidth()/2/Const.PPM, TingTong.INSTANCE.getScreenHeight()/2/Const.PPM, 0);
    }
    public void render(SpriteBatch batch){
        batch.draw(texture, x, y, width, height);
    }
    public void reverseVelX(){
        this.velX *= -1;
    }
    public void reverseVelY(){
        this.velY *= -1;
    }
    public void incSpeed(){
        this.speed *= 1.1f;
    }
}
