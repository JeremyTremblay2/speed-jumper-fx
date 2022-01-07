package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.comportement.Comportement;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;

import java.util.Objects;

public abstract class Entite {
    private static final double VELOCITE_PAR_DEFAUT = 1;
    private static final double GRAVITE_PAR_DEFAUT = 2;
    private Position2D position;
    private Rectangle collision;
    private Dimension dimension;
    private double gravite;
    private double velocite;

    public Entite(Position2D position, Rectangle collision, Dimension dimension) throws IllegalArgumentException {
        verificationParametre(position, "position");
        verificationParametre(dimension, "dimension");
        verificationParametre(collision, "collision");
        this.dimension = dimension;
        this.position = position;
        this.collision = collision;
        gravite = GRAVITE_PAR_DEFAUT;
        velocite = VELOCITE_PAR_DEFAUT;
    }

    public Position2D getPosition() {
        return position;
    }

    public void setPosition(Position2D position) {
        this.position = position;
    }

    public Rectangle getCollision() {
        return collision;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public double getGravite() {
        return gravite;
    }

    public double getVelocite() {
        return velocite;
    }

    public void miseAJour(double temps) {
        //Ne fait rien.
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entite entite = (Entite) o;
        return equals(entite);
    }

    public boolean equals(Entite entite) {
        return position.equals(entite.getPosition())
                && dimension.equals(entite.getDimension())
                && collision.equals(entite.getCollision());
    }

    @Override
    public int hashCode() {
        return Objects.hash(position.hashCode(), dimension.hashCode(), collision.hashCode());
    }

    @Override
    public String toString() {
        return this.getClass() + " : " + position.toString() + " "
                + "\nZone collision : " + collision.toString()
                + "\n" + gravite + "g " + velocite + "v";
    }

    private void verificationParametre(Object o, String nom) throws IllegalArgumentException {
        if (o == null) {
            throw new IllegalArgumentException("La " + nom + " passée en paramètre est nulle.");
        }
    }
}
