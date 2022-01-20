package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.actions.Sauteur;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.monde.Niveau;

public class CommandeSaut implements Commande {
    private Niveau niveau;
    private Sauteur sauteur;

    public CommandeSaut(Niveau niveau) throws IllegalArgumentException {
        this.niveau = niveau;
        sauteur = new Sauteur(niveau.getCarte());
    }

    @Override
    public void execute(Entite entite, float temps) {
        sauteur.miseAJourEtatDeJeu(entite, temps);
        new Thread(sauteur).start();
    }
}
