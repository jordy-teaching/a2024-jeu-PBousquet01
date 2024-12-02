package FinalGame;

import Doctrina.Canvas;
import Doctrina.MovableEntity;

import java.awt.*;

public class Enemy extends MovableEntity {
    private static final String SPRITE_PATH = "images/Joker.png";

    boolean vertical = false;
    boolean endPoint1 = true;
    boolean endPoint2 = false;
    int baliseEndpoint1;
    int baliseEndpoint2;

    public Enemy(int baliseEndpoint1, int baliseEndpoint2, boolean vertical){
        setDimension(32,32);
        setSpeed(2);
        this.baliseEndpoint1 = baliseEndpoint1;
        this.baliseEndpoint2 = baliseEndpoint2;
        this.vertical = vertical;
    }

    public void update() {
        super.update();
        if (vertical){
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
        } else {
            if(endPoint1){
                y+=getSpeed();
                if (y>baliseEndpoint1){
                    endPoint1 = false;
                    endPoint2=true;
                }
            } else if (endPoint2) {
                y-=getSpeed();
                if (y<baliseEndpoint2){
                    endPoint1 = true;
                    endPoint2=false;
                }
            }
        }

    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(this, Color.RED);
    }
}
