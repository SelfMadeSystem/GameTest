package uwu.smsgamer.testgame.entity;

import uwu.smsgamer.testgame.utils.*;

public class BoxEntity extends GEntity {
    private final V2D size;
    public BoxEntity(V2D pos, V2D size) {
        this.pos = pos;
        this.size = size;
        this.hitMethod = 2;
    }

    @Override
    public HitBox getStaticHitBox() {
        return new HitBox(V2D.origin(), size.clone());
    }

    @Override
    public void tick() {
    }

    @Override
    public void relativeToGrid() {
        super.relativeToGrid();
        moveWithCollision(0.3);
    }
}
