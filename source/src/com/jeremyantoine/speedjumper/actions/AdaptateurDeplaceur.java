package com.jeremyantoine.speedjumper.actions;

import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.monde.Carte2D;

/**
 * Classe permettant d'adapter le deplacement
 */
public class AdaptateurDeplaceur implements Simulation {
    private Deplaceur deplaceur;
    private Direction direction;
    private Carte2D carteCourante;

    /**
     * constructeur de la classe
     * @param direction direction dans laquelle se deplacer
     * @param carteCourante carte ou se deplacer
     */
    public AdaptateurDeplaceur(Direction direction, Carte2D carteCourante) {
        this.direction = direction;
        this.carteCourante = carteCourante;
        deplaceur = new Deplaceur(carteCourante);
    }

    /**
     * retourne la direction
     * @return direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * set la direction
     * @param direction la direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * retourne la carte courante
     * @return la carte courrante
     */
    public Carte2D getCarteCourante() {
        return carteCourante;
    }

    /**
     * set la carte courrante
     * @param carteCourante la carte courrante
     */
    public void setCarteCourante(Carte2D carteCourante) {
        this.carteCourante = carteCourante;
    }

    /**
     * met a jout l'etat de jeu en fonction du deplacement
     * @param entite entite qui se deplace
     * @param temps temps de la boucle
     */
    @Override
    public void miseAJourEtatDeJeu(Entite entite, double temps) {
        deplaceur.deplace(entite, temps, direction);
    }
}
