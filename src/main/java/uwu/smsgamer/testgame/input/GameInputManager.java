package uwu.smsgamer.testgame.input;

import de.gurkenlabs.litiengine.input.Input;

import java.awt.event.*;
import java.util.HashMap;

public class GameInputManager implements KeyListener, MouseListener {
    private static GameInputManager instance;

    public static GameInputManager getInstance() {
        if (instance == null) instance = new GameInputManager();
        return instance;
    }

    public static final GInput LEFT = new GInput(0, KeyEvent.VK_A);
    public static final GInput RIGHT = new GInput(0, KeyEvent.VK_D);
    public static final GInput UP = new GInput(0, KeyEvent.VK_W);
    public static final GInput DOWN = new GInput(0, KeyEvent.VK_S);
    public static final GInput SPAWN = new GInput(0, KeyEvent.VK_SPACE);
    public static final GInput SPEED = new GInput(0, KeyEvent.VK_SHIFT);

    public static final HashMap<String, GInput> inputs = new HashMap<>();

    public static void init() {
        Input.keyboard().addKeyListener(getInstance());
        Input.mouse().addMouseListener(getInstance());
        inputs.put("LEFT", LEFT);
        inputs.put("RIGHT", RIGHT);
        inputs.put("UP", UP);
        inputs.put("DOWN", DOWN);
        inputs.put("SPAWN", SPAWN);
        inputs.put("SPEED", SPEED);
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        for (GInput input : inputs.values()) {
            if (input.type == 0 && input.keycode == e.getKeyCode()){
                input.press();
            }
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        for (GInput input : inputs.values()) {
            if (input.type == 0 && input.keycode == e.getKeyCode()){
                input.unpress();
            }
        }
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        /*for (GInput input : inputs.values()) { TODO: O_o
            if (input.type == 1 && input.keycode == e.getButton()){
                input.press();
            }
        }*/
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     */
    @Override
    public void mousePressed(MouseEvent e) {//unused
    }

    /**
     * Invoked when a mouse button has been released on a component.
     */
    @Override
    public void mouseReleased(MouseEvent e) {//unused
    }

    /**
     * Invoked when the mouse enters a component.
     */
    @Override
    public void mouseEntered(MouseEvent e) {//unused
    }

    /**
     * Invoked when the mouse exits a component.
     */
    @Override
    public void mouseExited(MouseEvent e) {//unused
    }

    /*
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
    @Override
    public void mouseMoved(MouseEvent e) {

    }*/
}
