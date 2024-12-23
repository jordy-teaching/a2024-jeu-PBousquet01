package FinalGame;

import Doctrina.Camera;
import Doctrina.Canvas;
import Doctrina.RenderingEngine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class World {
    private static final String MAP_PATH = "images/Entrepo_map.png";
    private static final String MAP_MUR_PATH = "resources/MapFiles/Entrepot_mur copie.csv";
    private static final String MAP_PROPS_PATH = "resources/MapFiles/Entrepot_objet_props copie.csv";
    private static final String MAP_OBJET_PATH = "resources/MapFiles/Entrepot_objet arrière plan copie.csv";
    private static final String MAP_CONTAINER_PATH = "resources/MapFiles/Entrepot_boite_conteneur copie.csv";
    private static final String MAP_BOX_CONTAINER_PATH = "resources/MapFiles/Entrepot_sous boite_contenuer copie.csv";

    private Camera camera;
    private Player player;
    private ArrayList<Blockade> blockades;

    private ArrayList<Enemy> enemies;

    private Image background;

    public World(Player player) {
        this.player = player;
        enemies = new ArrayList<>();
        initEnnemies();
        camera = new Camera(player);
        player.initCam(camera);
        initialize();
    }
    public void update() {
        camera.update();
        for (Enemy enemy: enemies){
            enemy.update();
            if(enemy.getVision(player,enemy.getX() -camera.getX(),enemy.getY() -camera.getY())){
                player.hasBeenSeen();
            }
        }
    }

    public void draw(Canvas canvas) {
        //Mode triller(remove camera.getX())
        //canvas.drawImage(background, camera.getX(), camera.getY());

        canvas.drawImage(background, -camera.getX(), -camera.getY());
        canvas.drawRectangle(-camera.getX(),-camera.getY(), RenderingEngine.getInstance().getScreen().getHeight(), RenderingEngine.getInstance().getScreen().getWidth(),new Color(0,0,0,180));
        for (Enemy enemy: enemies){
            enemy.drawWithPositions(canvas,enemy.getX() -camera.getX(),enemy.getY() -camera.getY());
        }

    }

    private void initialize(){
        blockades = new ArrayList<>();
        load();
        blockadeInit();
    }
    private void blockadeInit(){
        FileLoader fileLoader = new FileLoader();
        int[][] temp = fileLoader.loadCsvFile(164,164,MAP_MUR_PATH);
        fillBlockades(temp);
        temp = fileLoader.loadCsvFile(164,164,MAP_OBJET_PATH);
        fillBlockades(temp);
        temp = fileLoader.loadCsvFile(164,164,MAP_PROPS_PATH);
        fillBlockades(temp);
        temp = fileLoader.loadCsvFile(164,164,MAP_CONTAINER_PATH);
        fillBlockades(temp);
        temp = fileLoader.loadCsvFile(164,164,MAP_BOX_CONTAINER_PATH);
        fillBlockades(temp);
    }

    private void fillBlockades(int[][] temp){
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

    private void initEnnemies(){
        addToEnemies(3648,1472,3600,4320,false);
        addToEnemies(1248,160,180,3872,false);
        addToEnemies(1248,160,180,2784,false);
        addToEnemies(3552,1408,1500,2528,false);
        addToEnemies(2496,1472,2300,2848,false);
        addToEnemies(4032,3552,2752,3500,true);
        addToEnemies(2624,1664,2500,4096,false);
        addToEnemies(2368,1664,2200,3936,false);
        addToEnemies(3776,3616,2016,3650,true);
        addToEnemies(2688,2304,2400,3776,false);
        addToEnemies(1312,128,700,1182,false);
        addToEnemies(2400,1472,2300,768,false);
        addToEnemies(4864,3840,4500,2784,false);
        addToEnemies(3648,3040,3200,2784,false);
        addToEnemies(992,320,4160,900,true);
        addToEnemies(4960,3200,4800,1536,false);
    }

    private void addToEnemies(int balise1, int balise2, int posX, int posY, boolean vertical){
        Enemy enemy = new Enemy(balise1,balise2,vertical);
        enemy.teleport(posX,posY);
        enemies.add(enemy);
    }
}
