package com.jeremyantoine.speedjumper.Jeu;

import com.jeremyantoine.speedjumper.actions.Collisionneur;
import com.jeremyantoine.speedjumper.actions.Deplaceur;
import com.jeremyantoine.speedjumper.entites.Entite;

import java.util.ArrayList;
import java.util.List;

public class MetteurAJourDeJeu {
    private Collisionneur collisionneur;
    private Deplaceur deplaceur;
    private List<Entite> lesEntites;

    public MetteurAJourDeJeu(List<Entite> lesEntites) {
        deplaceur = new Deplaceur();
        collisionneur = new Collisionneur();
        this.lesEntites = lesEntites;
    }

    public void miseAJour() {

    }
}
