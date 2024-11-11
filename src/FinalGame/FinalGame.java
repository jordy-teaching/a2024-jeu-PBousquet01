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
        player.teleport(400, 400);
        world = new World(player);
        world.load();

        RenderingEngine.getInstance().getScreen().fullscreen();
        //RenderingEngine.getInstance().getScreen().hideCursor();
    }

    @Override
    protected void update() {
        if (gamePad.isQuitPressed()) {
            stop();
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
