package com.jeremyantoine.speedjumper.Jeu;

import com.jeremyantoine.speedjumper.coordonnees.Position;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.Personnage;

import java.util.ArrayList;
import java.util.List;

public class Jeu implements Observateur {
    public static final int FPS_CIBLE = 60;
    private int compteurFrame = 0;
    private long tempsOrigine = System.nanoTime();
    private boolean joue;

    private List<Entite> lesEntites;
    private AfficheurDeJeu afficheurDeJeu;
    private GestionnaireDeTouches gestionnaireDeTouches;

    public Jeu() {
        lesEntites = initialisation();
        afficheurDeJeu = new AfficheurDeJeu(lesEntites);
        gestionnaireDeTouches = new GestionnaireDeTouchesFX();
        joue = true;

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
        long tempsCourant;
        long tempsDerniereIteration = System.nanoTime();
        final long nanoseconde = 1000000000;

        List<Touche> lesTouches;

        BoucleDeJeu boucleDeJeu = new BoucleDeJeu(new ArrayList<>());
        boucleDeJeu.attacher(this);
        Thread boucle = new Thread(boucleDeJeu);
        boucle.setDaemon(true);
        boucle.start();

        while (joue) {
            tempsCourant = System.nanoTime();
            if (tempsCourant - tempsDerniereIteration >= nanoseconde) {
                affiche();
                tempsDerniereIteration = System.nanoTime();
            }
            lesTouches = gestionnaireDeTouches.detectionEntrees();
            if (lesTouches != null) {
                miseAJour();
            }
            miseAJour();
        }
        System.out.println("Jeu fini");
    }

    private void affiche() {
        System.out.println("NOUVELLE SECONDE");
    }

    private void sauvegarde() {

    }

    public void miseAJour() {

    }

    @Override
    public void miseAjour() {
        System.out.println("tada");
    }
}