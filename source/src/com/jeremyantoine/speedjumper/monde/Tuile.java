package com.jeremyantoine.speedjumper.monde;

import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Rectangle;

public class Tuile {
    private static final Dimension DIMENSION_PAR_DEFAUT = new Dimension(32, 32);
    private static int nombreTuiles = 0;
    private Dimension dimension;
    private Rectangle collision;
    private int id;

    public Tuile(Rectangle collision, Dimension dimension) {
        this.collision = collision;
        this.dimension = dimension;
        id = nombreTuiles;
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

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuile tuile = (Tuile) o;
        return equals(tuile);
    }

    public boolean equals(Tuile tuile) {
        return id == tuile.getId();
    }

    @Override
    public int hashCode() {
        return 7 * id;
    }

    @Override
    public String toString() {
        return "[" + id + "]" + collision + " " + dimension.toString();
    }
}
