package FinalGame;

import Doctrina.*;
import Doctrina.Canvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends ControllableEntity {
    private static final String SPRITE_PATH = "images/Joker.png";

    private Camera cam;
    private Animator animator;

    public int getRelativeX() {
        return relativeX;
    }

    public int getRelativeY() {
        return relativeY;
    }

    private int relativeX;
    private int relativeY;

    public void initCam(Camera cam) {
        this.cam = cam;
    }

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        setSpeed(3);
        animator = new Animator(this);
        animator.load(SPRITE_PATH,width,height);
    }

    @Override
    public void update() {
        super.update();
        moveWithController();

        this.relativeX = cam.getOffsetX() + cam.getHalfscreenX();
        //System.out.println(relativeX);
        this.relativeY = cam.getOffsetY()+ cam.getHalfscreenY();
        //System.out.println(relativeY);
        animator.update();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawImage(animator.getCurrentAnimation(getDirection()), relativeX, relativeY);
    }
}
