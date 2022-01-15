package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.comportement.Comportement;
import com.jeremyantoine.speedjumper.logique.*;

public class PersonnageJouable extends Vivant {
    private Score score;

    public PersonnageJouable(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                             double velocite, int degats, int pointsDeVie) throws IllegalArgumentException {
        super(position, collision, dimension, comportement, velocite, degats, pointsDeVie);
        score = new Score();
    }

    public PersonnageJouable(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                             double velocite, int degats) throws IllegalArgumentException {
        super(position, collision, dimension, comportement, velocite, degats);
    }

    public Score getScore() {
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
        return super.hashCode() + score.hashCode();
    }

    @Override
    public String toString() {
        return super.toString() + ", " + score;
    }
}
