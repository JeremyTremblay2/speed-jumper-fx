package com.jeremyantoine.speedjumper.monde;

import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Rectangle;

public class Tuile {
    private static final Dimension DIMENSION_PAR_DEFAUT = new Dimension(32, 32);
    private static int nombreTuiles = 0;
    private final int idTuile;
    private Dimension dimension;
    private Rectangle collision;

    public Tuile(Rectangle collision, Dimension dimension) {
        this.collision = collision;
        this.dimension = dimension;
        idTuile = nombreTuiles;
        nombreTuiles++;
    }

    public Tuile(Rectangle collision) {
        this(collision, DIMENSION_PAR_DEFAUT);
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Rectangle getCollision() {
        return collision;
    }

    public int getIdTuile() {
        return idTuile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuile tuile = (Tuile) o;
        return equals(tuile);
    }

    public boolean equals(Tuile tuile) {
        return idTuile == tuile.getIdTuile();
    }

    @Override
    public int hashCode() {
        return 7 * idTuile;
    }

    @Override
    public String toString() {
        return "[" + idTuile + "]" + collision + " " + dimension.toString();
    }
}
