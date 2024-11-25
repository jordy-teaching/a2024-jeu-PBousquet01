package Doctrina;

import FinalGame.Player;

import java.awt.*;

public class Camera extends StaticEntity{
    private Player player;
    private int offsetX;
    private int offsetY;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int halfscreenX = screenSize.width/2;
    private int halfscreenY = screenSize.height/2;

    public int getHalfscreenX() {
        return halfscreenX;
    }
    public int getHalfscreenY() {
        return halfscreenY;
    }
    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }
    public Camera(Player player) {
        this.player = player;
        offsetX = player.getX()- halfscreenX;
        offsetY = player.getY()-halfscreenY;
        //this.setDimension(15,15);
    }

    public void update() {
        if(player.hasMoved()){
            offsetX = player.getX()- halfscreenX;
            offsetY = player.getY()- halfscreenY;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(this, Color.RED);
    }
}
