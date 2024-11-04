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
        player.teleport(250, 250);
        world = new World();
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
    }

    @Override
    protected void draw(Canvas canvas) {
        world.draw(canvas);
        player.draw(canvas);
    }
}
