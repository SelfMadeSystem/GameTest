package uwu.smsgamer.testgame.screens;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.*;

public class GameScreenManager {
    public static final InGameScreen inGameScreen = new InGameScreen();
    public static void init() {
        Camera camera = new Camera();
        camera.setClampToMap(true);
        Game.world().setCamera(camera);
        Game.screens().add(inGameScreen);
    }
}
