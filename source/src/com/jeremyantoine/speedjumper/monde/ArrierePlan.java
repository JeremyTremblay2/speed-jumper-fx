package com.jeremyantoine.speedjumper.monde;

import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Position2D;

/**
 * Classe pour gerer l'arriere plan
 */
public class ArrierePlan {
    private final int zindex;
    private float vitesseDefilement;
    private Position2D position;
    private Dimension dimension;

    /**
     * Constructeur de l'arrierePlan
     * @param zindex index ou afficher l'arriere plan
     * @param vitesseDefilement vitesse de defilement de l'arriere plan
     * @param dimension dimension de l'arriere plan
     * @param position position de l'arriere plan
     * @throws IllegalArgumentException
     */
    public ArrierePlan(int zindex, float vitesseDefilement, Dimension dimension, Position2D position)
            throws IllegalArgumentException {
        if (position == null || position.getX() < 0 || position.getY() < 0) {
            throw new IllegalArgumentException("La position donnée à l'arrière plan ne "
                    + "peut pas être nulle ou inférieure à zéro. Donné : " + position);
        }

        if (dimension == null || dimension.getLargeur() < position.getX()
                || dimension.getHauteur() < position.getY()) {
            throw new IllegalArgumentException("La dimension donnée à l'arrière plan ne "
                    + "peut pas être nulle ou inférieure à sa position. Donné : " + dimension);
        }

        this.position = position;
        this.dimension = dimension;
        this.zindex = zindex;
        this.vitesseDefilement = vitesseDefilement;
    }

    /**
     * Autre constructeur de l'arriere plan
     * @param zindex index ou afficher l'arriere plan
     * @param vitesseDefilement vitesse de defilement de l'arriere plan
     * @param dimension dimension de l'arriere plan
     * @throws IllegalArgumentException
     */
    public ArrierePlan(int zindex, float vitesseDefilement, Dimension dimension)
            throws IllegalArgumentException {
        this(zindex, vitesseDefilement, dimension, new Position2D(0, 0));
    }

    /**
     * retourne l'index de l'arriere plan
     * @return
     */
    public int getZindex() {
        return zindex;
    }

    /**
     * retourne la vitesse de defilement
     * @return
     */
    public float getVitesseDefilement() {
        return vitesseDefilement;
    }

    /**
     * retourne la position de l'arriere plan
     * @return
     */
    public Position2D getPosition() {
        return position;
    }

    /**
     * set la position de l'arriere plan
     * @param position
     */
    public void setPosition(Position2D position) {
        this.position = position;
    }

    /**
     * retourne la dimension de l'arriere plan
     * @return
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * compare deux objets entre eux ici arriere plan
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrierePlan arrierePlan = (ArrierePlan) o;
        return equals(arrierePlan);
    }

    public boolean equals(ArrierePlan arrierePlan) {
        return zindex == arrierePlan.getZindex()
                && position.equals(arrierePlan.getPosition());
    }

    @Override
    public int hashCode() {
        return 7 * zindex + position.hashCode();
    }

    /**
     * retourne une chaine pour afficher le contenu des données de l'objet
     * @return
     */
    @Override
    public String toString() {
        return "[" + zindex + "] " + vitesseDefilement + "v, " + position.toString()
                + " " + dimension.toString();
    }
}
