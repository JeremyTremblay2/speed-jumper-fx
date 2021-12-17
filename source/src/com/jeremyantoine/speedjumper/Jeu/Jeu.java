package com.jeremyantoine.speedjumper.Jeu;

import com.jeremyantoine.speedjumper.coordonnees.Position2D;
import com.jeremyantoine.speedjumper.coordonnees.Rectangle;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.PersonnageJouable;
import com.jeremyantoine.speedjumper.entites.Vivant;

import java.util.ArrayList;
import java.util.List;

public class Jeu implements Observateur {
    public static final int FPS_CIBLE = 60;
    private int compteurFrame = 0;
    private long tempsOrigine = System.nanoTime();
    private boolean joue;

    private List<Entite> lesEntites;
    private AfficheurDeJeu afficheurDeJeu;
    private RecuperateurDeTouches recuperateurDeTouches;

    public Jeu() {
        lesEntites = initialisation();
        afficheurDeJeu = new AfficheurDeJeu(lesEntites);
        recuperateurDeTouches = new RecuperateurDeTouchesFX();
        joue = true;

        joue();
        sauvegarde();
    }

    private List<Entite> initialisation() {
        //charger ressources
        List<Entite> lesEntites = new ArrayList<Entite>();
        lesEntites.add(new PersonnageJouable(new Position2D(0, 0),
                new Rectangle(10, 10, 20, 20),
                15));
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
            lesTouches = recuperateurDeTouches.detecte();
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