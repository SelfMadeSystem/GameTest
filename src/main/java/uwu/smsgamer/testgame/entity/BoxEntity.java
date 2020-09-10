package uwu.smsgamer.testgame.entity;

import uwu.smsgamer.testgame.utils.*;

public class BoxEntity extends GEntity {
    private final V2D size;
    public BoxEntity(V2D pos, V2D size) {
        this.pos = pos;
        this.size = size;
        this.hitMethod = 2;
        this.resistance = 0.5;
    }

    @Override
    public HitBox getStaticHitBox() {
        return new HitBox(V2D.origin(), size.clone());
    }

    @Override
    public void tick() {
        gridVel.y *= 0.95;
        gridVel.x *= 0.95;
        if (Math.abs(gridVel.x) < 0.0001)
            gridVel.x = 0;
        if (Math.abs(gridVel.y) < 0.0001)
            gridVel.y = 0;
    }

    @Override
    public void relativeToGrid() {
        super.relativeToGrid();
        moveWithCollision(0.3);
    }
}
