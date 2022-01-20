package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.comportement.Comportement;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;

import java.util.Objects;

/**
 * Classe entite
 */
public abstract class Entite {
    private static final double VELOCITE_PAR_DEFAUT = 1;
    private Position2D position;
    private Rectangle collision;
    private Dimension dimension;
    private Comportement comportement;
    private double velocite;
    private boolean surSol;
    private boolean chute;

    /**
     * Constructeur de la classe entite
     * @param position position de base de l'entite
     * @param collision sa zone de colision
     * @param dimension sa dimension
     * @param comportement son comportement
     * @param velocite sa velocite
     * @throws IllegalArgumentException
     */
    public Entite(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                  double velocite) throws IllegalArgumentException {
        verificationParametre(position, "position");
        verificationParametre(dimension, "dimension");
        verificationParametre(collision, "collision");

        if (dimension.getLargeur() <= 0 || dimension.getHauteur() <= 0
                || dimension.getLargeur() < collision.getDimension().getLargeur()
                || dimension.getHauteur() < collision.getDimension().getHauteur()) {
            throw new IllegalArgumentException("La dimension passée en paramètre de l'entité est nulle ou "
                    + "inférieure à 0, ou inférieure à la collision de l'entité. Donné : " + dimension);
        }

        this.dimension = dimension;
        this.comportement = comportement;
        this.position = position;
        this.collision = collision;
        this.velocite = velocite <= 0 ? VELOCITE_PAR_DEFAUT : velocite;
        surSol = false;
        chute = true;
    }

    /**
     * retourne la position de l'entite
     * @return
     */
    public Position2D getPosition() {
        return position;
    }

    /**
     * modifie la position de l'entite
     * @param position
     */
    public void setPosition(Position2D position) {
        this.position = position;
    }

    /**
     * retourne le rectangle de collision de l'entite
     * @return
     */
    public Rectangle getCollision() {
        return collision;
    }

    /**
     * retourne la dimension de l'entite
     * @return
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * retourne la velocite de l'entiote
     * @return
     */
    public double getVelocite() {
        return velocite;
    }

    /**
     * methode qui retourne true si l'entite est sur le sol
     * @return
     */
    public boolean isSurSol() {
        return surSol;
    }

    /**
     * methode qui retourne true si l'entite tombe
     * @return
     */
    public boolean isChute() {
        return chute;
    }


    /**
     * set l'entite sur le sol
     * @param surSol
     */
    public void setSurSol(boolean surSol) {
        this.surSol = surSol;
    }

    /**
     * set l'entite entrain de chutter
     * @param chute
     */
    public void setChute(boolean chute) {
        this.chute = chute;
    }

    /**
     * retourne le comportement de l'entite
     * @return
     */
    public Comportement getComportement() {
        return comportement;
    }

    /**
     * methode qui met a jour le comportement de l'entite
     * @param temps
     */
    public void miseAJour(double temps) {
        comportement.agit(this, temps);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entite entite = (Entite) o;
        return equals(entite);
    }

    public boolean equals(Entite entite) {
        return position.equals(entite.getPosition())
                && dimension.equals(entite.getDimension())
                && collision.equals(entite.getCollision())
                && comportement.equals(entite.getComportement());
    }

    @Override
    public int hashCode() {
        return Objects.hash(position.hashCode(), dimension.hashCode(),
                collision.hashCode(), comportement.hashCode());
    }

    @Override
    public String toString() {
        return this.getClass() + " : " + position.toString() + " "
                + "\nZone collision : " + collision.toString()
                + "\n" + velocite + "v"
                + "\n" + "Se trouve sur sol : " + surSol
                + "\n" + "Est en train de chuter : " + chute
                + "\nComportement : " + comportement;
    }

    private void verificationParametre(Object o, String nom) throws IllegalArgumentException {
        if (o == null) {
            throw new IllegalArgumentException("La " + nom + " passée en paramètre est nulle.");
        }
    }
}
