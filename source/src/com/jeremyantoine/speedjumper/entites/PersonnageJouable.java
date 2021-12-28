package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.comportement.Comportement;
import com.jeremyantoine.speedjumper.logique.Attaque;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;

public class PersonnageJouable extends Vivant {
    private int score;

    public PersonnageJouable(Position2D position, Rectangle collision, Comportement comportement, int degats,
                             int pointsDeVie) throws IllegalArgumentException {
        super(position, collision, comportement, degats, pointsDeVie);
    }

    public PersonnageJouable(Position2D position, Rectangle collision, Comportement comportement, int degats)
            throws IllegalArgumentException {
        super(position, collision, comportement, degats);
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonnageJouable personnageJouable = (PersonnageJouable) o;
        return equals(personnageJouable);
    }

    public boolean equals(PersonnageJouable personnageJouable) {
        return super.equals(personnageJouable)
                && score == personnageJouable.getScore();
    }

    @Override
    public int hashCode() {
        return super.hashCode() + score;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Score : " + score + "points.";
    }
}
