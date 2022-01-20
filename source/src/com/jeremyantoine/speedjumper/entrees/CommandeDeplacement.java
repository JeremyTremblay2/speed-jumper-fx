package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.actions.AdaptateurDeplaceur;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.monde.Niveau;

/**
 * Classe permettant de gerer l'action se deplacer
 */
public class CommandeDeplacement implements Commande {
    private AdaptateurDeplaceur deplaceur;

    /**
     * Constructeur de la classe
     * @param direction direction dans laquelle l'enetite se deplace
     * @param niveau niveau dans lequelle l'entite se deplace
     */
    public CommandeDeplacement(Direction direction, Niveau niveau) {
        if (niveau == null) {
            throw new IllegalArgumentException("Le niveau passé en paramètre ne peut pas être null.");
        }
        deplaceur = new AdaptateurDeplaceur(direction, niveau.getCarte());
    }

    /**
     * Execute la commande et fais asuter l'entite
     * @param entite
     * @param temps
     */
    @Override
    public void execute(Entite entite, float temps) {
        deplaceur.miseAJourEtatDeJeu(entite, temps);
    }
}
