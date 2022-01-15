package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.comportement.Comportement;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;

public abstract class Entite {
    private static final double VELOCITE_PAR_DEFAUT = 1;
    private static final double GRAVITE_PAR_DEFAUT = 2;
    private Position2D position;
    private Rectangle collision;
    private Dimension dimension;
    private Comportement comportement;
    private double gravite;
    private double velocite;

    public Entite(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                  double velocite) throws IllegalArgumentException {
        if (position == null) {
            throw new IllegalArgumentException("La position passée en paramètre de l'entité est nulle.");
        }
        if (collision == null) {
            throw new IllegalArgumentException("La collision passée en paramètre de l'entité est nulle.");
        }
        if (dimension == null || dimension.getLargeur() <= 0 || dimension.getHauteur() <= 0
                || dimension.getLargeur() < collision.getDimension().getLargeur()
                || dimension.getHauteur() < collision.getDimension().getHauteur()) {
            throw new IllegalArgumentException("La dimension passée en paramètre de l'entité est nulle ou "
                    + "inférieure à 0, ou inférieure à la collision de l'entité. Donné : " + dimension);
        }
        this.dimension = dimension;
        this.comportement = comportement;
        this.position = position;
        this.collision = collision;
        if (velocite <= 0) {
            this.velocite = VELOCITE_PAR_DEFAUT;
        }
        else {
            this.velocite = velocite;
        }
        gravite = GRAVITE_PAR_DEFAUT;
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

    public Comportement getComportement() {
        return comportement;
    }

    public void miseAJour(double temps) {
        if (comportement != null) {
            comportement.agit(this, temps);
        }
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
                && collision.equals(entite.getCollision())
                && comportement.equals(entite.getComportement());
    }

    @Override
    public int hashCode() {
        return 7 * position.hashCode()
                + 7 * dimension.hashCode()
                + 7 * collision.hashCode()
                + 7 * comportement.hashCode();
    }

    @Override
    public String toString() {
        return this.getClass() + " : " + position.toString() + " "
                + "\nZone collision : " + collision.toString()
                + "\n" + gravite + "g " + velocite + "v"
                + " \nCompotement : " + comportement.toString();
    }
}
