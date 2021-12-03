package com.jeremyantoine.speedjumper.coordonnees;

import java.util.Objects;

public class Rectangle {

    private Position position;
    private Dimension dimension;

    public Rectangle(double positionX, double positionY, double largeur, double hauteur) {
        position = new Position(positionX, positionY);
        dimension = new Dimension(largeur, hauteur);
    }

    public Position getPosition() {
        return position;
    }

    private void setPosition(Position position) {
        this.position = position;
    }

    public Dimension getDimension() {
        return dimension;
    }

    private void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return position.toString() + " " + dimension.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return equals(rectangle);
    }

    public boolean equals(Rectangle rectangle) {
        return position.equals(rectangle.getPosition())
                && dimension.equals(rectangle.getDimension());
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, dimension);
    }
}
