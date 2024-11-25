package FinalGame;

import Doctrina.Camera;
import Doctrina.Canvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class World {
    private static final String MAP_PATH = "images/Tilemap.png";
    private Camera camera;
    private Player player;

    private Image background;

    public World(Player player) {
        this.player = player;
        camera = new Camera(player);
        player.setCam(camera);
    }

    public void update() {
        camera.update();
    }

    public void load() {
        try {
            background = ImageIO.read(
                    getClass().getClassLoader().getResourceAsStream(MAP_PATH)
            );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            //System.out.println("TOUJOURS EXECUTER");
        }
    }

    public void draw(Canvas canvas) {
        //Mode triller(remove camera.getX())
        //canvas.drawImage(background, camera.getOffsetX(), camera.getOffsetY());
        canvas.drawImage(background, camera.getX() -camera.getOffsetX(), camera.getY() -camera.getOffsetY());
        camera.draw(canvas);
    }
}
