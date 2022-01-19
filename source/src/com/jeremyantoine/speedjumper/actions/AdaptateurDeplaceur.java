package com.jeremyantoine.speedjumper.actions;

import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.monde.Carte2D;

public class AdaptateurDeplaceur implements Simulation {
    private Deplaceur deplaceur;
    private Direction direction;
    private Carte2D carteCourante;

    public AdaptateurDeplaceur(Direction direction, Carte2D carteCourante) {
        this.direction = direction;
        this.carteCourante = carteCourante;
        deplaceur = new Deplaceur(carteCourante);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Carte2D getCarteCourante() {
        return carteCourante;
    }

    public void setCarteCourante(Carte2D carteCourante) {
        this.carteCourante = carteCourante;
    }

    @Override
    public void miseAJourEtatDeJeu(Entite entite, double temps) {
        deplaceur.deplace(entite, temps, direction);
    }
}
