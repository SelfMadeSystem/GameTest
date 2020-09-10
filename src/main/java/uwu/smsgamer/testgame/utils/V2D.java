package uwu.smsgamer.testgame.utils;

import java.awt.geom.Point2D;
import java.util.Objects;

public class V2D implements Cloneable {
    public static V2D origin() {
        return new V2D();
    }

    public double x;
    public double y;

    public V2D() {
    }

    public V2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public V2D set(V2D v) {
        return set(v.x, v.y);
    }

    public V2D set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public V2D add(V2D v) {
        return add(v.x, v.y);
    }

    public V2D add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public V2D subtract(V2D v) {
        return subtract(v.x, v.y);
    }

    public V2D subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public V2D mult(double a) {
        return mult(a, a);
    }

    public V2D mult(double x, double y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public V2D div(double a) {
        return div(a, a);
    }

    public V2D div(double x, double y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public V2D clamp(double a) {
        return this.clamp(a, a);
    }

    public V2D clamp(double x, double y) {
        this.x = Math.max(-x, Math.min(x, this.x));
        this.y = Math.max(-y, Math.min(y, this.y));
        return this;
    }

    public boolean lessThan(V2D other) {
        return this.x < other.x && this.y < other.y;
    }

    public boolean greaterThan(V2D other) {
        return other.lessThan(this);
    }

    public Point2D toPoint2D() {
        return new Point2D.Double(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        V2D v2D = (V2D) o;
        return Double.compare(v2D.x, x) == 0 &&
          Double.compare(v2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "V2D{" +
          "x=" + x +
          ", y=" + y +
          '}';
    }

    @Override
    public V2D clone() {
        try {
            return (V2D) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    public V2D mid(V2D other) {
        return new V2D(x + (x - other.x) / 2, y + (y - other.y) / 2);
    }

    public double diffS(V2D other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }
}
