package com.jeremyantoine.speedjumper.monde;

import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Position2D;

public class ArrierePlan {
    private int zindex = 1;
    private float vitesseDefilement;
    private Position2D position;
    private Dimension dimension;

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

    public ArrierePlan(int zindex, float vitesseDefilement, Dimension dimension)
            throws IllegalArgumentException {
        this(zindex, vitesseDefilement, dimension, new Position2D(0, 0));
    }

    public int getZindex() {
        return zindex;
    }

    public float getVitesseDefilement() {
        return vitesseDefilement;
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

    @Override
    public String toString() {
        return "[" + zindex + "] " + vitesseDefilement + "v, " + position.toString()
                + " " + dimension.toString();
    }
}
