package com.jeremyantoine.speedjumper.logique;

import java.util.Objects;

/**
 * Classe pour une dimension
 */
public class Dimension {
    private double largeur;
    private double hauteur;

    /**
     * Constructeur de la classe Dimension
     * @param largeur lageur de la dimension
     * @param hauteur hauteur de la dimension
     */
    public Dimension(double largeur, double hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    /**
     * Retourne la largeur de la dimension
     * @return
     */
    public double getLargeur() {
        return largeur;
    }


    /**
     * set la largeur de la dimension
     * @param largeur largeur de la dimension
     */
    private void setLargeur(double largeur) {
        this.largeur = largeur;
    }


    /**
     * retourne la hauteur de la dimension
     * @return
     */
    public double getHauteur() {
        return hauteur;
    }

    /**
     * set la hauteur de la dimension
     * @param hauteur hauteur de la dimension
     */
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
        return largeur + "x" + hauteur;
    }
}
