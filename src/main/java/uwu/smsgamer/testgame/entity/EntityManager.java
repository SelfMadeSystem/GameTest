package uwu.smsgamer.testgame.entity;

import de.gurkenlabs.litiengine.*;
import de.gurkenlabs.litiengine.configuration.ClientConfiguration;
import uwu.smsgamer.testgame.utils.V2D;

import java.util.*;

public class EntityManager implements IUpdateable {
    private static EntityManager instance;
    public static EntityManager getInstance() {
        return instance == null ? instance = new EntityManager() : instance;
    }

    public final Set<GEntity> entities = new HashSet<>();
    public GPlayer player;

    public Set<GEntity> getEntities() {
        Set<GEntity> gEntities = new HashSet<>(entities);
        gEntities.add(player);
        return gEntities;
    }

    public static void init() {
        Game.loop().attach(getInstance());
        getInstance().player = new GPlayer();
        getInstance().entities.add(new StaticEntity(new V2D(-40, -40), new V2D(16, 16)));
        getInstance().entities.add(new BoxEntity(new V2D(64, -192), new V2D(16, 16)));
        getInstance().entities.add(new BoxEntity(new V2D(80, -208), new V2D(16, 48)));
        getInstance().entities.add(new OwOEntity(new V2D(40, 40), new V2D(16, 16)));
    }

    /**
     * This method is called by the game loop on all objects that are attached to the loop.
     * It's called on every tick of the loop and the frequency can be configured using the {@code ClientConfiguration}.
     *
     * @see ClientConfiguration#setMaxFps(int)
     */
    @Override
    public void update() {
        for (GEntity e : getEntities()) {
            e.tick();
        }
    }
}
