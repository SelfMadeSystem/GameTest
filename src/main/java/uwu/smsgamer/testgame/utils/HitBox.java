package uwu.smsgamer.testgame.utils;

import java.util.Objects;

public class HitBox implements Cloneable{
    public V2D min;
    public V2D max;

    public HitBox(double x, double y) {
        this(0, 0, x, y);
    }

    public HitBox(V2D max) {
        this(V2D.origin(), max);
    }

    public HitBox(V2D min, V2D max) {
        this.min = min;
        this.max = max;
    }

    public HitBox(double minX, double minY, double maxX, double maxY) {
        this.min = new V2D(minX, minY);
        this.max = new V2D(maxX, maxY);
    }

    public HitBox add(V2D pos) {
        return add(pos.x, pos.y);
    }

    public HitBox add(double x, double y) {
        min.add(x, y);
        max.add(x, y);
        return this;
    }

    public HitBox addR(V2D pos) {
        return addR(pos.x, pos.y);
    }

    public HitBox addR(double x, double y) {
        V2D size = getSize();
        x += size.x/2;
        y += size.y/2;
        min.add(x, y);
        max.add(x, y);
        return this;
    }

    public V2D getSize() {
        return new V2D(min.x - max.x, min.y - max.y);
    }

    public V2D getCenter() {
        return new V2D(min.x + (min.x - max.x) / 2, min.y + (min.y - max.y) / 2);
    }

    public boolean collided(HitBox other) {
        return this.max.greaterThan(other.min) && this.min.lessThan(other.max);
    }

    @Override
    public HitBox clone() {
        try {
            return (HitBox) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    @Override
    public String toString() {
        return "HitBox{" +
          "min=" + min +
          ", max=" + max +
          '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HitBox hitBox = (HitBox) o;
        return min.equals(hitBox.min) &&
          max.equals(hitBox.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }
}
