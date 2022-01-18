package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.actions.AdaptateurDeplaceur;
import com.jeremyantoine.speedjumper.actions.CollisionneurCarte;
import com.jeremyantoine.speedjumper.actions.Deplaceur;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.monde.Niveau;

public class CommandeDeplacement implements Commande {
    private AdaptateurDeplaceur deplaceur;
    private Direction direction;
    private Niveau niveau;

    public CommandeDeplacement(Direction direction, Niveau niveau) {
        if (niveau == null) {
            throw new IllegalArgumentException("Le niveau passé en paramètre ne peut pas être null.");
        }
        this.direction = direction;
        this.niveau = niveau;
        deplaceur = new AdaptateurDeplaceur(direction, niveau.getCarte());
    }

    @Override
    public void execute(Entite entite, float temps) {
        deplaceur.miseAJourEtatDeJeu(entite, temps);
    }
}
