package FinalGame;

import Doctrina.*;
import Doctrina.Canvas;

public class Player extends ControllableEntity {
    private static final String SPRITE_PATH = "images/Joker.png";
    private Camera cam;
    private Animator animator;


    private int relativeX;
    private int relativeY;

    private boolean sighted;

    public int getRelativeX() {
        return relativeX;
    }

    public int getRelativeY() {
        return relativeY;
    }
    public void initCam(Camera cam) {
        this.cam = cam;
    }

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        setSpeed(3);
        sighted = false;
        animator = new Animator(this);
        animator.load(SPRITE_PATH,width,height);
    }

    public void hasBeenSeen(){
        sighted = true;
    }

    public boolean isSighted() {
        return sighted;
    }
    @Override
    public void update() {
        super.update();
        moveWithController();

        this.relativeX = cam.getOffsetX() + cam.getHalfscreenX();
        this.relativeY = cam.getOffsetY()+ cam.getHalfscreenY();
        animator.update();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawImage(animator.getCurrentAnimation(getDirection()), relativeX, relativeY);
    }
}
