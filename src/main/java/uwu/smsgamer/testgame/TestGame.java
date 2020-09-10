package uwu.smsgamer.testgame;

import de.gurkenlabs.litiengine.Game;
import uwu.smsgamer.testgame.entity.EntityManager;
import uwu.smsgamer.testgame.input.GameInputManager;
import uwu.smsgamer.testgame.screens.GameScreenManager;

public class TestGame {
    private static TestGame instance;
    private String[] args;

    public static TestGame getInstance() {
        if (instance == null) instance = new TestGame();
        return instance;
    }

    public void start(String[] args) {
        this.args = args;
        // set meta information about the game
        Game.info().setName("ÒwÓ");
        Game.info().setSubTitle("");
        Game.info().setVersion("");
        /*Game.info().setWebsite("https://github.com/gurkenlabs/litiengine-gurk-nukem");
        Game.info().setDescription("An example 2D platformer with shooter elements made in the LITIengine");*/

        // init the game infrastructure
        Game.init(args);

        // set the icon for the game (this has to be done after initialization because the ScreenManager will not be present otherwise)
        //Game.window().setIcon(Resources.images().get("sprites/icon.png"));
        Game.graphics().setBaseRenderScale(4.001f);

        // load data from the utiLITI game file
        //Resources.load("game.litidata");

        // load the first level (resources for the map were implicitly loaded from the game file)
        //Game.world().loadEnvironment("map/level1.tmx");

        GameInputManager.init();
        GameScreenManager.init();
        EntityManager.init();

        Game.start();
        /*Game.init(this.args);
        TestScreen screen = new TestScreen();
        System.out.println(Game.screens().current() + "   " + Game.time().sinceGameStart());
        Game.screens().add(screen);
        Game.start();*/
        //Game.screens().display(screen);
    }
}
