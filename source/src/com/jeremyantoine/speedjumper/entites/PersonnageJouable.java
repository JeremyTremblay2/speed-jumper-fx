package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.coordonnees.Position2D;
import com.jeremyantoine.speedjumper.coordonnees.Rectangle;

public class PersonnageJouable extends Vivant {
    public PersonnageJouable(Position2D position, Rectangle collision, int pointsDeVie) throws IllegalArgumentException {
        super(position, collision, pointsDeVie);
    }

    public PersonnageJouable(Position2D position, Rectangle collision) throws IllegalArgumentException {
        super(position, collision);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonnageJouable personnageJouable = (PersonnageJouable) o;
        return equals(personnageJouable);
    }

    public boolean equals(PersonnageJouable personnageJouable) {
        return super.equals(personnageJouable);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
