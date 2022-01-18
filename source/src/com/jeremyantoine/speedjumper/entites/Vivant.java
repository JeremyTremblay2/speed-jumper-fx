package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.comportement.Comportement;
import com.jeremyantoine.speedjumper.logique.*;

import java.util.Objects;

public abstract class Vivant extends Entite {
    private static final int POINTS_DE_VIE_PAR_DEFAUT = 10;
    private final int pointsDeViesInitiaux;
    private int pointsDeVie;
    private int degats;
    private Direction direction;

    public Vivant(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                  double velocite, int degats, int pointsDeVie) throws IllegalArgumentException {
        super(position, collision, dimension, comportement, velocite);
        pointsDeVie = pointsDeVie <= 0 ? POINTS_DE_VIE_PAR_DEFAUT : pointsDeVie;
        pointsDeViesInitiaux = pointsDeVie;
        this.degats = degats;
        direction = Direction.DROITE;
    }

    public Vivant(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                  double velocite, int degats) throws IllegalArgumentException {
        this(position, collision, dimension, comportement, velocite, degats, POINTS_DE_VIE_PAR_DEFAUT);
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getDegats() {
        return degats;
    }

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
