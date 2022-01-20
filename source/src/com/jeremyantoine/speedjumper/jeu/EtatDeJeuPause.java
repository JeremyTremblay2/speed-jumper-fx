package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;

/**
 * Classe de l'etat de jeu en pause
 */
public class EtatDeJeuPause extends EtatDeJeu {

    /**
     * Constructeur de EtatDeJeuPause
     * @param jeu
     * @param recuperateur
     * @throws IllegalArgumentException
     */
    public EtatDeJeuPause(TableauJeu jeu, RecuperateurDeTouches recuperateur) throws IllegalArgumentException {
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
