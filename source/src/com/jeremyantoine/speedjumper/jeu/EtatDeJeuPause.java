package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;

/**
 * Classe de l'etat de jeu en pause
 */
public class EtatDeJeuPause extends EtatDeJeu {
    private boolean enPause;

    /**
     * Constructeur de EtatDeJeuPause
     * @param jeu
     * @param recuperateur
     * @throws IllegalArgumentException
     */
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
