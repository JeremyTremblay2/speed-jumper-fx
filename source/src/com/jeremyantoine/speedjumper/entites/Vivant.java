package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.comportement.Comportement;
import com.jeremyantoine.speedjumper.logique.*;

import java.util.Objects;

/**
 * Classe abstraite pour les entite vivante
 */
public abstract class Vivant extends Entite {
    private static final int POINTS_DE_VIE_PAR_DEFAUT = 10;
    private final int pointsDeViesInitiaux;
    private int pointsDeVie;
    private int degats;
    private Direction direction;

    /**
     * Constructeur de cette classe. Initialise les parametre necessaire
     * @param position position 2D de l'entite vivante
     * @param collision collision de cette entite
     * @param dimension dimension de cette entite
     * @param comportement comportement de l'entite
     * @param velocite vitesse de l'entite
     * @param degats degat  de l'entite
     * @param pointsDeVie nombre de point de vie de l'entite
     * @throws IllegalArgumentException
     */
    public Vivant(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                  double velocite, int degats, int pointsDeVie) throws IllegalArgumentException {
        super(position, collision, dimension, comportement, velocite);
        this.pointsDeVie = pointsDeVie <= 0 ? POINTS_DE_VIE_PAR_DEFAUT : pointsDeVie;
        pointsDeViesInitiaux = pointsDeVie;
        this.degats = degats;
        direction = Direction.DROITE;
    }

    /**
     * Autre constructeur de la classe
     * @param position position 2D de l'entite vivante
     * @param collision collision de cette entite
     * @param dimension dimension de cette entite
     * @param comportement comportement de l'entite
     * @param velocite vitesse de l'entite
     * @param degats degat  de l'entite
     * @throws IllegalArgumentException
     */
    public Vivant(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                  double velocite, int degats) throws IllegalArgumentException {
        this(position, collision, dimension, comportement, velocite, degats, POINTS_DE_VIE_PAR_DEFAUT);
    }

    /**
     * retourne les points de vie
     * @return
     */
    public int getPointsDeVie() {
        return pointsDeVie;
    }

    /**
     * set les points de vies
     * @param pointsDeVie nouveau nombre de pv
     */
    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    /**
     * retourne la direction
     * @return
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * modifie la direction
     * @param direction nouvelle direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * retourne le nombre de degat
     * @return
     */
    public int getDegats() {
        return degats;
    }

    /**
     * retourne le nombre de points de vie initial
     * @return
     */
    public int getPointsDeViesInitiaux() {
        return pointsDeViesInitiaux;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Vivant vivant = (Vivant) o;
        return equals(vivant);
    }

    public boolean equals(Vivant vivant) {
        return pointsDeVie == vivant.getPointsDeVie()
                && direction == vivant.getDirection()
                && degats == vivant.getDegats()
                && super.equals(vivant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pointsDeVie, direction, degats);
    }

    @Override
    public String toString() {
        return super.toString()
                + "\n" + pointsDeVie + "\u2764"
                + "\n" + degats + "\u2764 degats, "
                + "\nDirection : " + direction;
    }
}
