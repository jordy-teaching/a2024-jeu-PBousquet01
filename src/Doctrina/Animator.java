package Doctrina;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Animator {

    private BufferedImage image;
    private Image[] rightFrames;
    private Image[] leftFrames;
    private Image[] upFrames;
    private Image[] downFrames;


    public int getFrameTableLenght(){
        return leftFrames.length;
    }
    public void loadSpriteSheet(String spritePath) {
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(spritePath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadAnimationFrames(int width,int height) {
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

    public Image getCurrentAnimation(Direction direction, int currentAnimationFrame){
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
}
