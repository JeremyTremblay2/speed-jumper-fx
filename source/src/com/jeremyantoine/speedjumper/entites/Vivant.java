package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.logique.Attaque;
import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;

import java.util.Objects;

public abstract class Vivant extends Entite {
    private static final int POINTS_DE_VIE_INITIAUX = 10;
    private int pointsDeVie;
    private Direction direction;
    private Attaque attaque;

    public Vivant(Position2D position, Rectangle collision, Attaque attaque, int pointsDeVie) throws IllegalArgumentException {
        super(position, collision);
        if (pointsDeVie <= 0) {
            this.pointsDeVie = POINTS_DE_VIE_INITIAUX;
        }
        else {
            this.pointsDeVie = pointsDeVie;
        }
        this.attaque = attaque;
        direction = Direction.DROITE;
    }

    public Vivant(Position2D position, Rectangle collision, Attaque attaque) throws IllegalArgumentException {
        this(position, collision, attaque, POINTS_DE_VIE_INITIAUX);
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

    @Override
    public String toString() {
        return super.toString() + " " + pointsDeVie + "\u2764 " + direction;
    }
}
