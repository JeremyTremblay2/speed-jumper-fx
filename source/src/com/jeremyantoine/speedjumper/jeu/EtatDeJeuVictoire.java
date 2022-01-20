package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;

public class EtatDeJeuVictoire extends EtatDeJeu {
    public EtatDeJeuVictoire(TableauJeu jeu, RecuperateurDeTouches recuperateur) throws IllegalArgumentException {
        super(jeu, recuperateur);
    }

    @Override
    public EtatJeu entreeUtilisateur(float temps) {
        return null;
    }

    @Override
    public void miseAJour(float temps) {

    }

    @Override
    public void affichage() {

    }
}
