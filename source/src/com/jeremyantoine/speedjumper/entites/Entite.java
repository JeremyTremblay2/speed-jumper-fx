package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.coordonnees.Position;

import java.util.Objects;

public class Entite {

    private Position position;

    public Entite(Position position) throws IllegalArgumentException {
        if (position == null) {
            throw new IllegalArgumentException("La position passée en paramètre est nulle.");
        }
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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
        return "Entite : " + position.toString();
    }
}
