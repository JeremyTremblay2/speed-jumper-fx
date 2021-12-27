package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.logique.Attaque;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Ombre extends Ennemi {
    private static final int NOMBRE_MAXIMUM_POSITIONS = 5000;
    private List<Position2D> positions = new ArrayList<>();

    public Ombre(Position2D position, Rectangle collision, Attaque attaque, int pointsDeVie) throws IllegalArgumentException {
        super(position, collision, attaque, pointsDeVie);
    }

    public Ombre(Position2D position, Rectangle collision, Attaque attaque) throws IllegalArgumentException {
        super(position, collision, attaque);
    }

    public List<Position2D> getPositions() {
        return positions;
    }

    public int getNombrePositions() {
        return positions.size();
    }

    public void ajouterPosition(Position2D position) {
        if (getNombrePositions() > NOMBRE_MAXIMUM_POSITIONS) {
            positions.remove(0);
        }
        positions.add(position);
    }

    @Override
    public String toString() {
        return super.toString() + " Positions : " + positions.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ombre ombre = (Ombre) o;
        return equals(ombre);
    }

    public boolean equals(Ombre ombre) {
        return super.equals(ombre) && positions.equals(ombre.getPositions());
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 7 * positions.hashCode();
    }
}
