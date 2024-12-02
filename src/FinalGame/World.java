package FinalGame;

import Doctrina.Camera;
import Doctrina.Canvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class World {
    private static final String MAP_PATH = "images/Tilemap.png";
    private static final String MAP_BLOCKADE_PATH = "resources/MapFiles/Walls_walls.csv";
    private Camera camera;
    private Player player;
    private ArrayList<Blockade> blockades;

    public Enemy getEnemy() {
        return enemy;
    }

    private Enemy enemy;

    private Image background;

    public World(Player player) {
        this.player = player;
        enemy = new Enemy(800,200,true);
        enemy.teleport(400,280);
        camera = new Camera(player);
        player.initCam(camera);
        initialize();
    }
    public void update() {
        camera.update();
        enemy.update();
    }

    private void initialize(){
        blockades = new ArrayList<>();
        load();
        blockadeInit();
    }
    private void blockadeInit(){
        FileLoader fileLoader = new FileLoader();
        int[][] temp = fileLoader.loadCsvFile(30,30,MAP_BLOCKADE_PATH);
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                if(temp[j][i] != -1){
                    Blockade temporaryBlock = new Blockade();
                    temporaryBlock.setDimension(28,28);
                    temporaryBlock.teleport(i*32,j*32);
                    blockades.add(temporaryBlock);
                }
            }
        }
    }
    private void load() {
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
        //canvas.drawImage(background, camera.getX(), camera.getY());

        canvas.drawImage(background, -camera.getX(), -camera.getY());
        canvas.drawRectangle(enemy.getX() -camera.getX(), enemy.getY() -camera.getY(), enemy.getWidth(), enemy.getHeight(), Color.RED);
    }
}
