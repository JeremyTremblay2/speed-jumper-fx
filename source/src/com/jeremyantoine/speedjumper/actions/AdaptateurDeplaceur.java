package com.jeremyantoine.speedjumper.actions;

import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Direction;

public class AdaptateurDeplaceur implements Simulation {
    private static Deplaceur deplaceur = new Deplaceur();
    private Direction direction;

    public AdaptateurDeplaceur(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void miseAJourEtatDeJeu(Entite entite, double temps) {
        deplaceur.deplace(entite, temps, direction);
    }
}
