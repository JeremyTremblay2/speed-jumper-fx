package com.jeremyantoine.speedjumper.Jeu;

import com.jeremyantoine.speedjumper.coordonnees.Position;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.Personnage;

import java.util.ArrayList;
import java.util.List;

public class Jeu {

    private List<Entite> lesEntites;
    private AfficheurDeJeu afficheurDeJeu;

    public Jeu() {
        lesEntites = initialisation();
        afficheurDeJeu = new AfficheurDeJeu(lesEntites);
        joue();
        sauvegarde();
    }

    private List<Entite> initialisation() {
        //charger ressources
        List<Entite> lesEntites = new ArrayList<Entite>();
        lesEntites.add(new Personnage(new Position(0, 0), 10));
        return lesEntites;
    }

    private void joue() {
        //boucle
        affiche();
        metAJour();
        //input
    }

    private void affiche() {
        afficheurDeJeu.afficheJeu();
    }

    private void metAJour() {

    }

    private void sauvegarde() {

    }
}
