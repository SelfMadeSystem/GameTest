package uwu.smsgamer.testgame.entity;

import uwu.smsgamer.testgame.utils.*;

public abstract class GEntity {
    // Position on grid
    public V2D pos = new V2D();
    // Grid velocity
    public V2D gridVel = new V2D();
    // Relative velocity
    public V2D relVel = new V2D();
    public float direction = 0;
    //0 none
    //1 push out
    //2 solid
    public int hitMethod = 0;

    public void tick() {
        direction = MathUtils.wrapAngle180(direction);
        //System.out.println(direction + "  " + relVel + "  " + gridVel);
        applyBasicMovement();
        relativeToGrid();
        if (Math.abs(gridVel.x) < 0.0001)
            gridVel.x = 0;
        if (Math.abs(gridVel.y) < 0.0001)
            gridVel.y = 0;
        addToPos();
    }

    public void applyBasicMovement() {
        relVel.y -= 0.025;
        relVel.y *= 0.96;
        relVel.x *= 0.95;
        if (Math.abs(relVel.x) < 0.0001)
            relVel.x = 0;
        if (Math.abs(relVel.y) < 0.0001)
            relVel.y = 0;
    }

    public void moveWithCollision(double d) {
        for (GEntity entity : EntityManager.getInstance().getEntities()) {
            if (entity.equals(this))
                continue;
            HitBox myBox = getHitBox();
            HitBox box = entity.getHitBox();
            switch (entity.hitMethod) {
                case 1: {
                    if (!box.collided(myBox))
                        continue;
                    float dir = (float) Math.atan2(entity.pos.y - this.pos.y, entity.pos.x - this.pos.x);
                    gridVel.add(MathUtils.cosr(dir) * d, MathUtils.sinr(dir) * d);
                    break;
                }
                case 2: {
                    if (box.collided(myBox))
                        continue;
                    if (myBox.clone().add(-gridVel.x, 0).collided(box)) {
                        // boolean above = myBox.max.x < box.max.x;
                        gridVel.x = 0;
                    } else if (myBox.clone().add(0, -gridVel.y).collided(box)) {
                        // boolean above = myBox.max.y < box.max.y;
                        gridVel.y = 0;
                    }
                }

            }
            //V2D force = getRotCollWEnts().mult(d);
        }
        pos.add(-gridVel.x, -gridVel.y);
    }

    /*//get rotational collision with an entity
    public V2D getRotCollWEnt(GEntity entity) {
        HitBox myBox = getHitBox();
        if (entity.equals(this))
            return new V2D();
        HitBox box = entity.getHitBox();
        if (!box.collided(myBox))
            return new V2D();
        float dir = (float) Math.atan2(entity.pos.y - this.pos.y, entity.pos.x - this.pos.x);
        return new V2D(MathUtils.cosr(dir), MathUtils.sinr(dir));
    }

    //get rotational collision with entities
    public V2D getRotCollWEnts() {
        HitBox myBox = getHitBox();
        V2D collision = new V2D();
        for (GEntity entity : EntityManager.getInstance().getEntities()) {
            if (entity.equals(this))
                continue;
            HitBox box = entity.getHitBox();
            if (!box.collided(myBox))
                continue;
            float dir = (float) Math.atan2(entity.pos.y - this.pos.y, entity.pos.x - this.pos.x);
            collision.x += MathUtils.cosr(dir);
            collision.y += MathUtils.sinr(dir);
        }
        return collision;
    }*/

    public void addToPos() {
        pos.add(gridVel.clone().mult(-1, -1));
    }

    public void changeDirection(float direction) {
        if (MathUtils.getAngleDifference(direction, this.direction) > 150) {
            this.direction = direction;
            gridToRelative();
        } else
            this.direction = direction;
    }

    public void relativeToGrid() {
        double x = relVel.x * MathUtils.cos(direction);
        x -= relVel.y * MathUtils.sin(direction);
        double y = relVel.x * MathUtils.sin(direction);
        y += relVel.y * MathUtils.cos(direction);
        gridVel.set(x, y);
    }

    public void gridToRelative() {
        double x = gridVel.x * MathUtils.cos(direction);
        x -= gridVel.y * MathUtils.sin(direction);
        double y = gridVel.x * MathUtils.sin(direction);
        y += gridVel.y * MathUtils.cos(direction);
        relVel.set(x, y);
        relVel.clamp(1);
    }

    public HitBox getStaticHitBox() {
        return new HitBox(16, 16);
    }

    public HitBox getHitBox() {
        return getStaticHitBox().addR(pos);
    }
}
