package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.logique.Attaque;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;

public class PersonnageJouable extends Vivant {
    public PersonnageJouable(Position2D position, Rectangle collision, Attaque attaque, int pointsDeVie) throws IllegalArgumentException {
        super(position, collision, attaque, pointsDeVie);
    }

    public PersonnageJouable(Position2D position, Rectangle collision, Attaque attaque) throws IllegalArgumentException {
        super(position, collision, attaque);
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
