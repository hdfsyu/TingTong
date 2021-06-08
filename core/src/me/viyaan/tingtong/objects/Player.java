package me.viyaan.tingtong.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import me.viyaan.tingtong.GameScreen;

public class Player extends PlayerPaddle{
    public Player(float x, float y, GameScreen gameScreen) {
        super(x, y, gameScreen);
        /*change 15 to whatever u want and uncomment it if u want to cheat (optional)*/ //score = 15;
    }
    public void update(){
        super.update();
        if(Gdx.input.isKeyPressed(Input.Keys.W))
            velY = 1;
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            velY = -1;
        body.setLinearVelocity(0, velY * speed);
    }
}
