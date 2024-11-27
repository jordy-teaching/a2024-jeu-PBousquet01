package FinalGame;

import Doctrina.Canvas;
import Doctrina.MovableEntity;

import java.awt.*;

public class Enemy extends MovableEntity {
    private static final String SPRITE_PATH = "images/Joker.png";
    private static final int ANIMATION_SPEED = 8;

    boolean endPoint1 = true;
    boolean endPoint2 = false;
    int baliseEndpoint1;
    int baliseEndpoint2;

    public Enemy(int baliseEndpoint1, int baliseEndpoint2){
        setDimension(32,32);
        setSpeed(2);
        this.baliseEndpoint1 = baliseEndpoint1;
        this.baliseEndpoint2 = baliseEndpoint2;
    }

    public void update() {
        super.update();
        if(endPoint1){
            x+=getSpeed();
            if (x>baliseEndpoint1){
                endPoint1 = false;
                endPoint2=true;
            }
        } else if (endPoint2) {
            x-=getSpeed();
            if (x<baliseEndpoint2){
                endPoint1 = true;
                endPoint2=false;
            }
        }
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(this, Color.RED);
    }
}
