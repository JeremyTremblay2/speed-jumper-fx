package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.comportement.Comportement;
import com.jeremyantoine.speedjumper.logique.Attaque;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;

public class Ennemi extends Vivant {

    public Ennemi(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                  double velocite, int degats, int pointsDeVie) throws IllegalArgumentException {
        super(position, collision, dimension, comportement, velocite, degats, pointsDeVie);
    }

    public Ennemi(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                  double velocite, int degats) throws IllegalArgumentException {
        super(position, collision, dimension, comportement, velocite, degats);
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
