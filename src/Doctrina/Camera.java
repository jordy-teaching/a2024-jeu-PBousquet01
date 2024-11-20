package Doctrina;

import FinalGame.Player;

import java.awt.*;

public class Camera extends StaticEntity{
    private Player player;
    private int offsetX;
    private int offsetY;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }
    public Camera(Player player) {
        this.player = player;
        offsetX = player.getX()- screenSize.width/2;
        offsetY = player.getY()-screenSize.height/2;
        this.setDimension(15,15);
    }

    public void update() {
        if(player.hasMoved()){
            offsetX = player.getX()- screenSize.width/2;
            offsetY = player.getY()- screenSize.height/2;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(this, Color.RED);
    }
}
