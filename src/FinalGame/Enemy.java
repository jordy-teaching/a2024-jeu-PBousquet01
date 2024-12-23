package FinalGame;

import Doctrina.*;
import Doctrina.Canvas;

import java.awt.*;

public class Enemy extends MovableEntity {
    private static final String SPRITE_PATH = "images/Ennemi.png";
    private static final Color SIGHT_COLOR = new Color(255,255,0,64);

    private boolean vertical = false;
    private boolean endPoint1 = true;
    private boolean endPoint2 = false;
    private int baliseEndpoint1;
    private int baliseEndpoint2;
    private Animator animator;


    public Enemy(int baliseEndpoint1, int baliseEndpoint2, boolean vertical){
        setDimension(32,32);
        setSpeed(2);
        this.baliseEndpoint1 = baliseEndpoint1;
        this.baliseEndpoint2 = baliseEndpoint2;
        this.vertical = vertical;
        animator = new Animator(this);
        animator.load(SPRITE_PATH,width,height);
    }

    public void update() {
        super.update();
        if (!vertical){
            if(endPoint1){
                move(Direction.RIGHT);
                if (x>baliseEndpoint1){
                    endPoint1 = false;
                    endPoint2=true;
                }
            } else if (endPoint2) {
                move(Direction.LEFT);
                if (x<baliseEndpoint2){
                    endPoint1 = true;
                    endPoint2=false;
                }
            }
        } else {
            if(endPoint1){
                move(Direction.DOWN);
                if (y>baliseEndpoint1){
                    endPoint1 = false;
                    endPoint2=true;
                }
            } else if (endPoint2) {
                move(Direction.UP);
                if (y<baliseEndpoint2){
                    endPoint1 = true;
                    endPoint2=false;
                }
            }
        }
        animator.update();
    }


    public void drawWithPositions(Canvas canvas, int posX,int posY) {
        drawVision(canvas,posX,posY);
        canvas.drawImage(animator.getCurrentAnimation(getDirection()), posX, posY);
    }

    @Override
    public void draw(Canvas canvas) {

    }

    public boolean getVision(Player player, int posX, int posY){
        boolean sighted = false;
        if(getFrontSight(getDirection(),posX,posY).intersects(new Rectangle(player.getRelativeX(),player.getRelativeY(),player.getWidth(),player.getHeight())) || getLeftSight(getDirection(),posX,posY).intersects(new Rectangle(player.getRelativeX(),player.getRelativeY(),player.getWidth(),player.getHeight())) || getRightSight(getDirection(),posX,posY).intersects(new Rectangle(player.getRelativeX(),player.getRelativeY(),player.getWidth(),player.getHeight()))){
            sighted = true;
        }
        return sighted;
    }

    private void drawVision(Canvas canvas, int posX,int posY){
        canvas.drawRectangle(getFrontSight(getDirection(),posX,posY),SIGHT_COLOR);
        canvas.drawRectangle(getRightSight(getDirection(),posX,posY),SIGHT_COLOR);
        canvas.drawRectangle(getLeftSight(getDirection(),posX,posY),SIGHT_COLOR);
    }
    private Rectangle getLeftSight(Direction direction, int posX, int posY){
        if(!vertical){
            return new Rectangle(posX,posY+32,32,25);
        }
        return new Rectangle(posX+25,posY,25,32);
    }
    private Rectangle getRightSight(Direction direction, int posX, int posY){
        if(!vertical){
            return new Rectangle(posX,posY-25,32,32);
        }
        return new Rectangle(posX-32,posY,32,32);
    }
    private Rectangle getFrontSight(Direction direction, int posX, int posY){
        if(!vertical){
            if(direction == Direction.RIGHT){
                return new Rectangle(posX+32,posY-25,160,82);
            }
            return new Rectangle(posX-160,posY-25,160,82);
        }
        if (direction == Direction.DOWN){
            return new Rectangle(posX-32,posY+32,82,160);
        }
        return new Rectangle(posX-32,posY-160,82,160);
    }
}
