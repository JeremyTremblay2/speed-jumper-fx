package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.comportement.Comportement;
import com.jeremyantoine.speedjumper.logique.*;

/**
 * Classe permettant de gerer les personnage jouable
 */
public class PersonnageJouable extends Vivant {
    private Score score;

    /**
     * Constructeur de la classe personnageJouable
     * @param position position du personnage
     * @param collision rectangle de colision du personnage
     * @param dimension dimension du personnage
     * @param comportement comportement du personnage
     * @param velocite valocite du personnage
     * @param degats degat du personnage
     * @param pointsDeVie pv du personnage
     * @throws IllegalArgumentException
     */
    public PersonnageJouable(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                             double velocite, int degats, int pointsDeVie) throws IllegalArgumentException {
        super(position, collision, dimension, comportement, velocite, degats, pointsDeVie);
        score = new Score();
    }

    /**
     * Autre constructeur du personnage
     * @param position position du personnage
     * @param collision rectangle de colision du personnage
     * @param dimension dimension du personnage
     * @param comportement comportement du personnage
     * @param velocite valocite du personnage
     * @param degats degat du personnage
     * @throws IllegalArgumentException
     */
    public PersonnageJouable(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                             double velocite, int degats) throws IllegalArgumentException {
        super(position, collision, dimension, comportement, velocite, degats);
    }

    /**
     * retourne le score du personnage
     * @return
     */
    public Score getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonnageJouable personnageJouable = (PersonnageJouable) o;
        return equals(personnageJouable);
    }

    public boolean equals(PersonnageJouable personnageJouable) {
        return super.equals(personnageJouable)
                && score == personnageJouable.getScore();
    }

    @Override
    public int hashCode() {
        return super.hashCode() + score.hashCode();
    }

    @Override
    public String toString() {
        return super.toString() + ", " + score;
    }
}
