package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;

public class Entite {
    private static final double VELOCITE_PAR_DEFAUT = 1;
    private static final double GRAVITE_PAR_DEFAUT = 2;
    private Position2D position;
    private Rectangle collision;
    private double gravite;
    private double velocite;

    public Entite(Position2D position, Rectangle collision) throws IllegalArgumentException {
        if (position == null) {
            throw new IllegalArgumentException("La position passée en paramètre est nulle.");
        }
        if (collision == null) {
            throw new IllegalArgumentException("La collision passée en paramètre est nulle.");
        }
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

    public double getGravite() {
        return gravite;
    }

    public double getVelocite() {
        return velocite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entite entite = (Entite) o;
        return equals(entite);
    }

    public boolean equals(Entite entite) {
        return position.equals(entite.getPosition());
    }

    @Override
    public int hashCode() {
        return 7 * position.hashCode();
    }

    @Override
    public String toString() {
        return this.getClass() + " : " + position.toString() + " "
                + "\nZone collision : " + collision.toString()
                + "\n" + gravite + "g " + velocite + "v";
    }
}
