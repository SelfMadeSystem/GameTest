package uwu.smsgamer.testgame.input;

import de.gurkenlabs.litiengine.input.Input;
import uwu.smsgamer.testgame.entity.EntityManager;
import uwu.smsgamer.testgame.utils.V2D;

import java.awt.event.*;

public class GameInputManager implements KeyListener {
    public static void init() {
        Input.keyboard().addKeyListener(new GameInputManager());
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
        //unused
    }

    private double mult = 1;

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) { //37 <---> 39
        //System.out.println("Pressed:" + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            EntityManager.getInstance().player.changeDirection(EntityManager.getInstance().player.direction + 0.5f);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            EntityManager.getInstance().player.changeDirection(EntityManager.getInstance().player.direction - 0.5f);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            EntityManager.getInstance().player.inputs.x = -mult;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            EntityManager.getInstance().player.inputs.x = mult;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            EntityManager.getInstance().player.inputs.y = -mult;
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            EntityManager.getInstance().player.inputs.y = mult;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            EntityManager.getInstance().player.pos.set(V2D.origin());
        } else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            mult = 10;
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            this.mult = 1;
        }
        //System.out.println("Released:" + e.getKeyCode());
    }
}
