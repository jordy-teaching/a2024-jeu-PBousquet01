package FinalGame;

import Doctrina.Canvas;
import Doctrina.Game;
import Doctrina.RenderingEngine;
import FinalGame.*;
import FinalGame.GamePad;
import FinalGame.Player;
import FinalGame.World;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class FinalGame extends Game {
    private Player player;
    private GamePad gamePad;
    private World world;

    private int soundCooldown;

    @Override
    protected void initialize() {
        GameConfig.setDebugEnabled(false);

        gamePad = new GamePad();
        player = new Player(gamePad);
        player.teleport(400,500);
        //player.teleport(0,0);
        world = new World(player);
        world.load();
        try{
            Clip clip = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("audios/Normal.wav"));
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        RenderingEngine.getInstance().getScreen().fullscreen();
        //RenderingEngine.getInstance().getScreen().hideCursor();
    }

    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        if(world.getEnemy().intersectWith(player)){
            System.out.println("WE HAVE COLLIDED");
        }
        player.update();
        world.update();
    }

    @Override
    protected void draw(Canvas canvas) {
        canvas.clear(0,0,RenderingEngine.getInstance().getScreen().getWidth(),RenderingEngine.getInstance().getScreen().getHeight());
        world.draw(canvas);
        player.draw(canvas);
    }
}
