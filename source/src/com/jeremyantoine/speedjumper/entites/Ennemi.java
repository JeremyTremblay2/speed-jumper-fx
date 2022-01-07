package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.comportement.Comportement;
import com.jeremyantoine.speedjumper.logique.Attaque;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;

public class Ennemi extends Vivant {
    private Comportement comportement;

    public Ennemi(Position2D position, Rectangle collision, Dimension dimension, int degats, int pointsDeVie,
                  Comportement comportement) throws IllegalArgumentException {
        super(position, collision, dimension, degats, pointsDeVie);
        this.comportement = comportement;
    }

    public Ennemi(Position2D position, Rectangle collision, Dimension dimension, int degats,
            Comportement comportement) throws IllegalArgumentException {
        super(position, collision, dimension, degats);
        this.comportement = comportement;
    }

    public Comportement getComportement() {
        return comportement;
    }

    @Override
    public void miseAJour(double temps) {
        comportement.agit(this, temps);
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
        return super.equals(ennemi)
                && comportement.equals(ennemi.getComportement());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
