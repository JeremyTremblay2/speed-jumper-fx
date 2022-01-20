package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;

public class EtatDeJeuPause extends EtatDeJeu {
    private boolean enPause;

    public EtatDeJeuPause(TableauJeu jeu, RecuperateurDeTouches recuperateur) throws IllegalArgumentException {
        super(jeu, recuperateur);
        enPause = true;
    }

    public boolean isEnPause() {
        return enPause;
    }

    @Override
    public EtatJeu entreeUtilisateur(float temps) {
        if (!enPause) {
            return EtatJeu.ETAT_JEU_JOUE;
        }
        return null;
    }

    @Override
    public void miseAJour(float temps) {
    }

    @Override
    public void affichage() {
    }
}
