package me.viyaan.tingtong.objects;

import me.viyaan.tingtong.GameScreen;

public class PlayerAI extends PlayerPaddle{
    public PlayerAI(float x, float y, GameScreen gameScreen) {
        super(x, y, gameScreen);
    }
    @Override
    public void update(){
        super.update();
        //AI is magic
        Ball ball = gameScreen.getBall();
        if(ball.getY() + 10 > y && ball.getY() - 10 > y)
        velY = 1;
        if(ball.getY() + 10 < y && ball.getY() - 10 < y)
            velY = -1;
        body.setLinearVelocity(0, velY * speed);
    }
}
