package Doctrina;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Animator {
    private static final int ANIMATION_SPEED = 8;

    private MovableEntity entity;
    private BufferedImage image;
    private Image[] rightFrames;
    private Image[] leftFrames;
    private Image[] upFrames;
    private Image[] downFrames;

    private int currentAnimationFrame = 1;
    private int nextFrame = ANIMATION_SPEED;
    private int animationStep = 1; // 1 or -1 (reverse) for animation cycle

    public Animator(MovableEntity entity){
        this.entity = entity;
    }

    public int getFrameTableLenght(){
        return leftFrames.length;
    }

    public void update(){
        if (entity.hasMoved()) {
            --nextFrame;
            if (nextFrame == 0) {
                currentAnimationFrame += animationStep;
                if (currentAnimationFrame == 0 || currentAnimationFrame >= getFrameTableLenght() - 1) {
                    animationStep *= -1;
                }
                nextFrame = ANIMATION_SPEED;
            }
        } else {
            currentAnimationFrame = 1; // Idle state
        }
    }
    public Image getCurrentAnimation(Direction direction){
        if (direction == Direction.DOWN) {
            return downFrames[currentAnimationFrame];
        } else if (direction == Direction.UP) {
            return upFrames[currentAnimationFrame];
        } else if (direction == Direction.RIGHT) {
            return rightFrames[currentAnimationFrame];
        } else if (direction == Direction.LEFT) {
            return leftFrames[currentAnimationFrame];
        }
        return downFrames[0];
    }
    public void load(String spritePath,int width,int height) {
        loadSpriteSheet(spritePath);
        loadAnimationFrames(width,height);
    }
    private void loadSpriteSheet(String spritePath) {
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(spritePath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadAnimationFrames(int width,int height) {
        downFrames = new Image[3];
        for (int i = 0; i < 3; i++) {
            downFrames[i] = image.getSubimage((32*i),0,width,height);
        }

        leftFrames = new Image[3];
        leftFrames[0] = image.getSubimage(0, 32, width, height);
        leftFrames[1] = image.getSubimage(32, 32, width, height);
        leftFrames[2] = image.getSubimage(64, 32, width, height);

        rightFrames = new Image[3];
        rightFrames[0] = image.getSubimage(0, 64, width, height);
        rightFrames[1] = image.getSubimage(32, 64, width, height);
        rightFrames[2] = image.getSubimage(64, 64, width, height);

        upFrames = new Image[3];
        upFrames[0] = image.getSubimage(0, 96, width, height);
        upFrames[1] = image.getSubimage(32, 96, width, height);
        upFrames[2] = image.getSubimage(64, 96, width, height);
    }


}
