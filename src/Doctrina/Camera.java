package Doctrina;

import FinalGame.Player;

import java.awt.*;

public class Camera extends MovableEntity{
    private Player player;
    int posX;
    int posY;

    public Camera(Player player) {
        this.player = player;
        posX = 0;
        posY= 0;
        teleport(player.getX(), player.getY());
        this.setDimension(15,15);
    }

    public void update() {
        if(player.hasMoved()){
            teleport(player.getX(), player.getY());
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRectangle(this, Color.RED);
    }
}
