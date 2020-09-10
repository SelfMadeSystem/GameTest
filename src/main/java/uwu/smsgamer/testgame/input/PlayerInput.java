package uwu.smsgamer.testgame.input;

public class PlayerInput {
    private static double mult = 1;

    public static double horizontal() {
        mult();
        double x = GameInputManager.LEFT.isDown() ? -mult : 0;
        x += GameInputManager.RIGHT.isDown() ? mult : 0;
        return x;
    }

    public static double vertical() {
        mult();
        double y = GameInputManager.UP.isDown() ? mult : 0;
        y += GameInputManager.DOWN.isDown() ? -mult : 0;
        return y;
    }

    private static void mult() {
        mult = GameInputManager.SPEED.isDown() ? 5 : 1;
    }
}
