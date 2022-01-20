package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.comportement.Comportement;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;

/**
 * Classe permettant de gerer les enemis
 */
public class Ennemi extends Vivant {

    /**
     * Constructeur de la classe ennemi
     * @param position position de l'ennemi
     * @param collision collision de l'ennemi
     * @param dimension dcollision de l'ennemi
     * @param comportement comportement de l'ennemi
     * @param velocite velocite de l'ennemi
     * @param degats degat de l'ennemi
     * @param pointsDeVie points de vie de l'ennemi
     * @throws IllegalArgumentException
     */
    public Ennemi(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                  double velocite, int degats, int pointsDeVie) throws IllegalArgumentException {
        super(position, collision, dimension, comportement, velocite, degats, pointsDeVie);
    }

    /**
     * Autre constructeur de la classe
     * @param position position de l'ennemi
     * @param collision collision de l'ennemi
     * @param dimension collision de l'ennemi
     * @param comportement comportement de l'ennemi
     * @param velocite velocite de l'ennemi
     * @param degats degat de l'ennemi
     * @throws IllegalArgumentException
     */
    public Ennemi(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                  double velocite, int degats) throws IllegalArgumentException {
        super(position, collision, dimension, comportement, velocite, degats);
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

    @Override
    public String toString() {
        return super.toString();
    }
}
