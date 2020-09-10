package uwu.smsgamer.testgame.entity;

import uwu.smsgamer.testgame.utils.*;

public abstract class GEntity {
    // Position on grid
    public V2D pos = new V2D();
    // Grid velocity
    public V2D gridVel = new V2D();
    public V2D finalVel = new V2D();
    // Relative velocity
    public V2D relVel = new V2D();
    public float direction = 0;
    //0 none
    //1 push out
    //2 solid
    public int hitMethod = 0;
    public double resistance = 1;

    public void tick() {
        direction = MathUtils.wrapAngle180(direction);
        //System.out.println(direction + "  " + relVel + "  " + gridVel);
        applyBasicMovement();
        relativeToGrid();
        if (Math.abs(gridVel.x) < 0.0001)
            gridVel.x = 0;
        if (Math.abs(gridVel.y) < 0.0001)
            gridVel.y = 0;
        move();
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

    public void moveWithCollision(double pushOut) {
        if (resistance == 0) {
            resistance = 0.5;
            new IllegalArgumentException("Resistance is equals to zero.").printStackTrace();
        }
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
                    gridVel.add(MathUtils.cosr(dir) * pushOut, MathUtils.sinr(dir) * pushOut);
                    break;
                }
                case 2: {// TODO: 2020-09-10 shit code idk why it no work wtf
                    if (box.collided(myBox)) {
                        float dir = (float) Math.atan2(entity.pos.y - this.pos.y, entity.pos.x - this.pos.x);
                        System.out.println("wtf");
                        for (float d = 0.05f; box.clone().add(gridVel.clone().add(finalVel)).collided(myBox); ) {
                            finalVel.add(MathUtils.cosr(dir)*d, MathUtils.sinr(dir)*d);
                        }
                        continue;
                    }
                    if (myBox.clone().add(-gridVel.x, 0).collided(box)) {
                        // boolean above = myBox.max.x < box.max.x;
                        System.out.println(entity.gridVel.x + "/" + resistance + "/" + (entity.gridVel.x / (resistance)) +
                          "/" + getClass().getSimpleName());
                        finalVel.x = entity.gridVel.x / (resistance/ entity.resistance);
                        entity.gridVel.x += gridVel.x / (entity.resistance / resistance);
                        finalVel.x -= gridVel.x;
                    } else if (myBox.clone().add(0, -gridVel.y).collided(box)) {
                        // boolean above = myBox.max.y < box.max.y;
                        System.out.println(entity.gridVel.y + "/" + resistance + "/" + (entity.gridVel.y / (resistance)) +
                          "/" + getClass().getSimpleName());
                        finalVel.y = entity.gridVel.y / (resistance/ entity.resistance);
                        entity.gridVel.y += gridVel.y / (entity.resistance / resistance);
                        finalVel.y -= gridVel.y;
                    }
                }

            }
            //V2D force = getRotCollWEnts().mult(d);
        }
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

    public void move() {
        //pos.add(gridVel.clone().mult(-1, -1));
    }

    public void actuallyMove() {
        pos.add(gridVel.clone().add(finalVel).mult(-1));
        finalVel.set(0, 0);
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
