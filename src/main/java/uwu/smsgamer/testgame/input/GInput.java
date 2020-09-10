package uwu.smsgamer.testgame.input;

public class GInput {
    public int type; //0 keyboard, 1 mouse
    /**
     * 1 left
     * 2 right
     * 3 mid
     * 4 up wheel
     * 5 down wheel
     * @see java.awt.event.MouseEvent
     * @see java.awt.event.KeyEvent
     */
    public int keycode;
    private long pressTime = 0;

    public GInput(int type, int keycode) {
        this.type = type;
        this.keycode = keycode;
    }

    void press() {
        pressTime++;
    }

    void unpress() {
        pressTime = 0;
    }

    public boolean isPressed() {
        return pressTime == 1;
    }

    public boolean isDown() {
        return pressTime > 0;
    }

    public long pressTime() {
        return pressTime;
    }
}
