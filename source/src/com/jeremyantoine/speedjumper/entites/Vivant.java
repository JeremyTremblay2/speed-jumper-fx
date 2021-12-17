package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.coordonnees.Direction;
import com.jeremyantoine.speedjumper.coordonnees.Position2D;
import com.jeremyantoine.speedjumper.coordonnees.Rectangle;

import java.util.Objects;

public abstract class Vivant extends Entite {
    private static final int POINTS_DE_VIE_INITIAUX = 10;
    private int pointsDeVie;
    private Direction direction;

    public Vivant(Position2D position, Rectangle collision, int pointsDeVie) throws IllegalArgumentException {
        super(position, collision);
        if (pointsDeVie <= 0) {
            this.pointsDeVie = POINTS_DE_VIE_INITIAUX;
        }
        else {
            this.pointsDeVie = pointsDeVie;
        }
        direction = Direction.DROITE;
    }

    public Vivant(Position2D position, Rectangle collision) throws IllegalArgumentException {
        this(position, collision, POINTS_DE_VIE_INITIAUX);
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    private void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    public Direction getDirection() {
        return direction;
    }

    private void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return super.toString() + " " + pointsDeVie + "\u2764";
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
                && super.equals(vivant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pointsDeVie, direction);
    }
}
