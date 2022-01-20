package com.jeremyantoine.speedjumper.logique;

import java.util.Objects;

/**
 * Classe rectangle permettant de créer une zone notemment pour le systeme de collision
 */
public class Rectangle {
    private Position2D position;
    private Dimension dimension;

    /**
     * Constructeur de la classe Rectangle
     * @param positionX coordonnée X du début du rectangle
     * @param positionY coordonnée Y du début du rectangle
     * @param largeur largeur du rectangle
     * @param hauteur hauteur du rectangle
     * @throws IllegalArgumentException
     */
    public Rectangle(double positionX, double positionY, double largeur, double hauteur)
            throws IllegalArgumentException {
        this(new Position2D(positionX, positionY), new Dimension(largeur, hauteur));
    }

    /**
     * Autre constructeur de la classe rectangle recevant la position et la dimension
     * @param position position 2D du rectangle
     * @param dimension dimension du rectangle
     * @throws IllegalArgumentException
     */
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

    /**
     * Autre constructeur de Rectangle créant une position et recevant une dimension
     * @param positionX coordonnée X du début du rectangle
     * @param positionY coordonnée Y du début du rectangle
     * @param dimension dimension du rectangle
     */
    public Rectangle(double positionX, double positionY, Dimension dimension) {
        this(new Position2D(positionX, positionY), dimension);
    }

    /**
     * Autre constructeur de Rectangle créant une dimension et recevant une position
     * @param position position 2D du rectangle
     * @param largeur largeur du rectangle
     * @param hauteur hauteur du rectangle
     */
    public Rectangle(Position2D position, double largeur, double hauteur) {
        this(position, new Dimension(largeur, hauteur));
    }


    /**
     * getter de la position du rectangle
     * @return position 2D du rectangle
     */
    public Position2D getPosition() {
        return position;
    }

    /**
     * setter de la position du rectangle
     * @param position position 2D
     */
    private void setPosition(Position2D position) {
        this.position = position;
    }

    /**
     * getter de la dimension du rectangle
     * @return dimension
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * setter de la dimension du rectangle
     * @param dimension dimension du rectangle
     */
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
