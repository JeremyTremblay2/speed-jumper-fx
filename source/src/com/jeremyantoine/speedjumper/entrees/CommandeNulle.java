package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.entites.Entite;

/**
 * Gestion de l'action NULL d'une entité
 */
public class CommandeNulle implements Commande {

    /**
     * Cette classe ne fais rien car action = null
     * @param entite entite de l'action
     * @param temps
     */
    @Override
    public void execute(Entite entite, float temps) {
        //Ne fait rien
    }
}
