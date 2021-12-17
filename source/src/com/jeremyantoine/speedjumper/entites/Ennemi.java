package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.coordonnees.Position2D;
import com.jeremyantoine.speedjumper.coordonnees.Rectangle;

import java.util.Objects;

public class Ennemi extends Vivant {

    public Ennemi(Position2D position, Rectangle collision, int pointsDeVie) throws IllegalArgumentException {
        super(position, collision, pointsDeVie);
    }

    public Ennemi(Position2D position, Rectangle collision) throws IllegalArgumentException {
        super(position, collision);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ennemi ennemi = (Ennemi) o;
        return equals(ennemi);
    }

    public boolean equals(Ennemi ennemi) {
        return super.equals(ennemi);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
