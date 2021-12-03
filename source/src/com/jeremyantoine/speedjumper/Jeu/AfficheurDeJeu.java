package com.jeremyantoine.speedjumper.Jeu;

import com.jeremyantoine.speedjumper.actions.Collisionneur;
import com.jeremyantoine.speedjumper.actions.Deplaceur;
import com.jeremyantoine.speedjumper.entites.Entite;

import java.util.List;

public class AfficheurDeJeu {

    private List<Entite> lesEntites;
    //private Camera camera;

    public AfficheurDeJeu(List<Entite> lesEntites) {
        this.lesEntites = lesEntites;
    }

    public void afficheJeu() {
        System.out.println(lesEntites.get(0));
    }
}
