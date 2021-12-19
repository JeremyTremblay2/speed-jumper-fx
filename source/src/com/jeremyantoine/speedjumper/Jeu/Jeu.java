package com.jeremyantoine.speedjumper.Jeu;

import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouchesFX;
import com.jeremyantoine.speedjumper.entrees.Touche;
import com.jeremyantoine.speedjumper.logique.Attaque;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.PersonnageJouable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Jeu implements Observateur {
    public static final int FPS_CIBLE = 60;
    private int compteurFrame = 0;
    private long tempsOrigine = System.nanoTime();
    private boolean joue;

    private List<Entite> lesEntites;
    private RecuperateurDeTouches recuperateurDeTouches;

    public Jeu() throws MalformedURLException {
        lesEntites = initialisation();
        recuperateurDeTouches = new RecuperateurDeTouchesFX(new URL("file:D:\\Cours\\2021-2022\\S1\\Conception et Prog Avancée\\speed-jumper\\source\\ressources\\touches.txt"), null);
        joue = true;

        joue();
        sauvegarde();
    }

    private List<Entite> initialisation() {
        //charger ressources
        List<Entite> lesEntites = new ArrayList<Entite>();
        lesEntites.add(new PersonnageJouable(new Position2D(0, 0),
                new Rectangle(10, 10, 20, 20),
                new Attaque(null, 4, 0.3f)));
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

    }
}