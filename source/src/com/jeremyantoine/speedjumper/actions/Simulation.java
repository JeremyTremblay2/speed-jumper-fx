package com.jeremyantoine.speedjumper.actions;

import com.jeremyantoine.speedjumper.entites.Entite;

/**
 * interface de la mise ajour de l'etat de jeu
 */
public interface Simulation {
    void miseAJourEtatDeJeu(Entite entite, double temps);
}
