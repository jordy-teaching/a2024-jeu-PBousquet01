package FinalGame;

import Doctrina.Canvas;
import Doctrina.Game;
import Doctrina.RenderingEngine;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class FinalGame extends Game {
    private Player player;
    private GamePad gamePad;
    private World world;

    private int soundCooldown;
    private boolean wasSeen;
    private boolean won;

    @Override
    protected void initialize() {
        GameConfig.setDebugEnabled(false);

        gamePad = new GamePad();
        player = new Player(gamePad);
        player.teleport(2615,4800);
        world = new World(player);
        wasSeen = false;
        won = false;
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
        if (!player.isSighted() || !won){
            player.update();
            world.update();
            if(player.intersectWith(4928,352,32,32)){
                won = true;
            }
        } else if(!wasSeen){
            wasSeen = true;
           SoundEffect.MURLOC.play();
        }

    }

    @Override
    protected void draw(Canvas canvas) {
        canvas.clear(0,0,RenderingEngine.getInstance().getScreen().getWidth(),RenderingEngine.getInstance().getScreen().getHeight());
        if (!player.isSighted()){
            world.draw(canvas);
            player.draw(canvas);
            if(won){
                canvas.drawText("Won",275,200);
            }
        }else {
            canvas.drawText("Game Over",275,200);
        }
    }
}
