package com.jeremyantoine.speedjumper.logique;

import java.util.Objects;

public class Attaque {
    private static final float DUREE_PAR_DEFAUT = 0.5f;
    private Rectangle collision;
    private float duree;
    private int degats;

    public Attaque(Rectangle collision, int degats, float duree) {
        this.collision = collision;
        this.degats = degats;
        this.duree = duree;
    }

    public Attaque(Rectangle collision, int degats) {
        this(collision, degats, DUREE_PAR_DEFAUT);
    }

    public Rectangle getCollision() {
        return collision;
    }

    public float getDuree() {
        return duree;
    }

    public int getDegats() {
        return degats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attaque attaque = (Attaque) o;
        return equals(attaque);
    }

    public boolean equals(Attaque attaque) {
        return duree == attaque.getDuree()
                && collision.equals(attaque.getCollision())
                && degats == attaque.getDegats();
    }

    @Override
    public int hashCode() {
        return Objects.hash(collision, duree, degats);
    }

    @Override
    public String toString() {
        return collision.toString() + ", " + degats + "\u2764 " + duree + "s";
    }
}
