package Doctrina;

import FinalGame.Player;

import java.awt.*;

public class Camera extends StaticEntity{
    private Player player;
    private int offsetX;
    private int offsetY;
    private int halfscreenX = RenderingEngine.getInstance().getScreen().getWidth()/2;
    private int halfscreenY = RenderingEngine.getInstance().getScreen().getHeight()/2;

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
        x = player.getX() - halfscreenX;
        y = player.getY() - halfscreenY;
        //this.setDimension(15,15);
    }

    public void update() {
        if(player.hasMoved()){
            x = player.getX() - halfscreenX;
            y = player.getY() - halfscreenY;
        }
        //System.out.println("Cam: " + x + "," + y);
    }

    @Override
    public void draw(Canvas canvas) {
        //canvas.drawRectangle(this, Color.RED);
    }
}
