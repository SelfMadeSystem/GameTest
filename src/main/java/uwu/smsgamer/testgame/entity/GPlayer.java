package uwu.smsgamer.testgame.entity;

import uwu.smsgamer.testgame.utils.*;

public class GPlayer extends GEntity {
    public V2D inputs = new V2D();

    public GPlayer() {
        this.hitMethod = 2;
    }

    @Override
    public void tick() {
        if (inputs.y > 0)
            this.relVel.y = 1.5 * inputs.y;
        else if (inputs.y < 0)
            this.relVel.y += 0.05 * inputs.y;
        this.relVel.x -= 0.05 * inputs.x;
        changeDirection((float) -Math.toDegrees(Math.atan2(64 - this.pos.x, 64 - this.pos.y)));
        super.tick();
        inputs.set(0, 0);
    }

    @Override
    public void addToPos() {
        moveWithCollision(0.3);
    }

    @Override
    public HitBox getStaticHitBox() {
        return new HitBox(32, 16);
    }
}
