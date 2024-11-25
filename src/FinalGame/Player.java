package FinalGame;

import Doctrina.*;
import Doctrina.Canvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends ControllableEntity {
    private static final String SPRITE_PATH = "images/Joker.png";
    private static final int ANIMATION_SPEED = 8;

    private int currentAnimationFrame = 1;
    private int nextFrame = ANIMATION_SPEED;
    private int animationStep = 1; // 1 or -1 (reverse) for animation cycle

    private Camera cam;
    private Animator animator;

    public void setCam(Camera cam) {
        this.cam = cam;
    }

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        setSpeed(3);
        animator = new Animator();
        load();
    }

    private void load() {
        animator.loadSpriteSheet(SPRITE_PATH);
        animator.loadAnimationFrames(width,height);
    }

    @Override
    public void update() {
        super.update();
        moveWithController();

        if (hasMoved()) {
            --nextFrame;
            if (nextFrame == 0) {
                currentAnimationFrame += animationStep;
                if (currentAnimationFrame == 0 || currentAnimationFrame >= animator.getFrameTableLenght() - 1) {
                    animationStep *= -1;
                }
                nextFrame = ANIMATION_SPEED;
            }
        } else {
            currentAnimationFrame = 1; // Idle state
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawImage(animator.getCurrentAnimation(getDirection(),currentAnimationFrame), (cam.getHalfscreenX()+width)/2, cam.getHalfscreenY()/2);
    }
}
