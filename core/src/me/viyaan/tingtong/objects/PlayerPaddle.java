package me.viyaan.tingtong.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import me.viyaan.tingtong.GameScreen;
import me.viyaan.tingtong.objects.helper.BodyHelper;
import me.viyaan.tingtong.objects.helper.Const;
import me.viyaan.tingtong.objects.helper.ContactType;

public abstract class PlayerPaddle {
    protected Body body;
    protected float x, y, speed, velY;
    protected int width, height, score;
    protected Texture texture;
    protected GameScreen gameScreen;
    public PlayerPaddle(float x, float y, GameScreen gameScreen) {
        this.x = x;
        this.y = y;
        this.gameScreen = gameScreen;
        this.speed = 6;
        this.width = 16;
        this.height = 64;
        this.texture = new Texture("color.png");
        this.body = BodyHelper.createBody(x, y, width, height, false, 10000, gameScreen.getWorld(), ContactType.PLAYER);
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public void update(){
        x = body.getPosition().x * Const.PPM - (width/2);
        y = body.getPosition().y * Const.PPM - (height/2);
        velY = 0;
    }
    public void render(SpriteBatch batch){
        batch.draw(texture, x, y, width, height);
    }
    public void score(){
        this.score++;
    }
}
