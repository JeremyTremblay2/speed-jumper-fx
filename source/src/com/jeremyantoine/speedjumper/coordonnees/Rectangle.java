package com.jeremyantoine.speedjumper.coordonnees;

import java.util.Objects;

public class Rectangle {

    private Position2D position;
    private Dimension dimension;

    public Rectangle(double positionX, double positionY, double largeur, double hauteur) {
        position = new Position2D(positionX, positionY);
        dimension = new Dimension(largeur, hauteur);
    }

    public Position2D getPosition() {
        return position;
    }

    private void setPosition(Position2D position) {
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
