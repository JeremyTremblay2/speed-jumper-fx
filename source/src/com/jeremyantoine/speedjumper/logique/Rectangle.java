package com.jeremyantoine.speedjumper.logique;

import java.util.Objects;

public class Rectangle {

    private Position2D position;
    private Dimension dimension;

    public Rectangle(double positionX, double positionY, double largeur, double hauteur)
            throws IllegalArgumentException {
        this(new Position2D(positionX, positionY), new Dimension(largeur, hauteur));
    }

    public Rectangle(Position2D position, Dimension dimension) throws IllegalArgumentException {
        if (dimension == null || dimension.getLargeur() < 0 || dimension.getHauteur() < 0) {
            throw new IllegalArgumentException("La dimension d'un rectangle ne peut pas être nulle ou être "
                    + "inférieure à 0. Donné : " + dimension);
        }
        if (position == null) {
            throw new IllegalArgumentException("La position est nulle, il est impossible de créer un nouveau rectangle.");
        }
        this.position = position;
        this.dimension = dimension;
    }

    public Rectangle(double positionX, double positionY, Dimension dimension) {
        this(new Position2D(positionX, positionY), dimension);
    }

    public Rectangle(Position2D position, double largeur, double hauteur) {
        this(position, new Dimension(largeur, hauteur));
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

    @Override
    public String toString() {
        return position.toString() + " " + dimension.toString();
    }
}
