package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.coordonnees.Position;

import java.util.Objects;

public class Personnage extends Entite {

    private static final int POINTS_DE_VIE_INITIAUX = 10;
    private int pointsDeVie;

    public Personnage(Position position, int pointsDeVie) throws IllegalArgumentException {
        super(position);
        if (pointsDeVie <= 0) {
            this.pointsDeVie = POINTS_DE_VIE_INITIAUX;
        }
        else {
            this.pointsDeVie = pointsDeVie;
        }
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    private void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
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
        Personnage personnage = (Personnage) o;
        return equals(personnage);
    }

    public boolean equals(Personnage personnage) {
        return pointsDeVie == personnage.getPointsDeVie()
                && super.equals(personnage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pointsDeVie);
    }
}
