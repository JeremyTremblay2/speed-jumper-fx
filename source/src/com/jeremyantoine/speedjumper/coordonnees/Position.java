package com.jeremyantoine.speedjumper.coordonnees;

import java.util.Objects;

public class Position {

    private double positionX;
    private double positionY;

    public Position(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public double getPositionX() {
        return positionX;
    }

    private void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    private void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    @Override
    public String toString() {
        return "{" + positionX + "; " + positionY + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return equals(position);
    }

    public boolean equals(Position position) {
        return Double.compare(position.positionX, positionX) == 0
                && Double.compare(position.positionY, positionY) == 0;
    }

    @Override
    public int hashCode() {
        return 7 * Objects.hash(positionX, positionY);
    }
}
