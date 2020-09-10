package uwu.smsgamer.testgame.entity;

import uwu.smsgamer.testgame.utils.*;

public class StaticEntity extends GEntity {
    private final V2D size;
    public StaticEntity(V2D pos, V2D size) {
        this.pos = pos;
        this.size = size;
        this.hitMethod = 1;
    }

    @Override
    public HitBox getStaticHitBox() {
        return new HitBox(V2D.origin(), size.clone());
    }

    @Override
    public void applyBasicMovement() {
    }

    @Override
    public void move() {
    }
}
