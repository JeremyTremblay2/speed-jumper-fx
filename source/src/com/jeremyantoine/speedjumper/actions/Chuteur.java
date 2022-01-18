package com.jeremyantoine.speedjumper.actions;

import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.monde.Niveau;

public class Chuteur implements Simulation {
    private Niveau niveauCourant;

    public Chuteur(Niveau niveauCourant) throws IllegalArgumentException {
        if (niveauCourant == null) {
            throw new IllegalArgumentException("Impossible d'instancier un chuteur car la niveau passé en paramètre "
                    + "est null.");
        }
        this.niveauCourant = niveauCourant;
    }

    @Override
    public void miseAJourEtatDeJeu(Entite entite, double temps) {
        double gravite = entite.getGravite();;
        if (gravite == 0 || entite.isSurSol()) {
            return;
        }

        /*if

        case BAS -> new Position2D(entite.getPosition().getX(),
                entite.getPosition().getY() + entite.getVelocite() * (temps / 1000000000));*/
    }
}
