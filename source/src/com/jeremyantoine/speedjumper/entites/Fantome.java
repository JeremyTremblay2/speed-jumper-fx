package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.comportement.Comportement;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Fantome extends Ennemi {
    private static final int NOMBRE_MAXIMUM_POSITIONS = 5000;
    private List<Position2D> positions = new ArrayList<>();

    public Fantome(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                   double velocite, int degats, int pointsDeVie) throws IllegalArgumentException {
        super(position, collision, dimension, comportement, velocite, degats, pointsDeVie);
    }

    public Fantome(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                   double velocite, int degats) throws IllegalArgumentException {
        super(position, collision, dimension, comportement, velocite, degats);
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
        StringBuilder chaine = new StringBuilder(super.toString());
        chaine.append("\nPositions : ");
        for (Position2D position : positions) {
            chaine.append(position.toString());
        }
        return chaine.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Fantome fantome = (Fantome) o;
        return equals(fantome);
    }

    public boolean equals(Fantome fantome) {
        return super.equals(fantome) && positions.equals(fantome.getPositions());
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 7 * positions.hashCode();
    }
}
