package com.jeremyantoine.speedjumper.logique;

import java.util.Objects;

public class Position2D {

    private double x;
    private double y;

    public Position2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    private void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    private void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position2D position = (Position2D) o;
        return equals(position);
    }

    public boolean equals(Position2D position) {
        return Double.compare(position.x, x) == 0
                && Double.compare(position.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return 7 * Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "{" + x + "; " + y + "}";
    }
}
