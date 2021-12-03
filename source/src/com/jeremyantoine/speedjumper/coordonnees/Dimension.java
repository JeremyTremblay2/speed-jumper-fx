package com.jeremyantoine.speedjumper.coordonnees;

import java.util.Objects;

public class Dimension {
    private double largeur;
    private double hauteur;

    public Dimension(double largeur, double hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public double getLargeur() {
        return largeur;
    }

    private void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public double getHauteur() {
        return hauteur;
    }

    private void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dimension dimension = (Dimension) o;
        return equals(dimension);
    }

    public boolean equals(Dimension dimension) {
        return Double.compare(dimension.largeur, largeur) == 0
                && Double.compare(dimension.hauteur, hauteur) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(largeur, hauteur);
    }

    @Override
    public String toString() {
        return  largeur + "x" + hauteur;
    }
}
