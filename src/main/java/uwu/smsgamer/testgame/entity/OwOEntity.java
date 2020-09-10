package uwu.smsgamer.testgame.entity;

import uwu.smsgamer.testgame.utils.*;

public class OwOEntity extends GEntity {
    private final V2D size;
    public OwOEntity(V2D pos, V2D size) {
        this.pos = pos;
        this.size = size;
        this.hitMethod = 2;
        this.resistance = 5;
    }

    @Override
    public HitBox getStaticHitBox() {
        return new HitBox(V2D.origin(), size.clone());
    }

    @Override
    public void tick() {
        this.relVel.y = -1;
        changeDirection((float) -Math.toDegrees(Math.atan2(EntityManager.getInstance().player.pos.x - this.pos.x,
          EntityManager.getInstance().player.pos.y - this.pos.y)));
        super.tick();
    }

    @Override
    public void move() {
        moveWithCollision(0);
    }

    @Override
    public void relativeToGrid() {
        super.relativeToGrid();
    }
}
