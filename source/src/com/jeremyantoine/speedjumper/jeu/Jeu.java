package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;
import com.jeremyantoine.speedjumper.observateurs.Observateur;

public class Jeu implements Observateur {
    private static final int FPS_CIBLE = 60;
    private static final double TEMPS_MISE_A_JOUR = 10000000;
    private int compteurFrame = 0;
    private long tempsOrigine = System.nanoTime();
    private boolean joue;
    private ManagerEtatDeJeu managerEtats;
    private TableauJeu jeu;

    public Jeu(RecuperateurDeTouches recuperateur) {
        jeu = new TableauJeu(recuperateur);
        managerEtats = new ManagerEtatDeJeu(jeu, recuperateur);
        joue = true;
    }

    public TableauJeu getJeu() {
        return jeu;
    }

    public ManagerEtatDeJeu getManagerEtats() {
        return managerEtats;
    }

    public void initialise() {

    }

    public void joue() {
        float precedent = System.nanoTime();
        float courant, ecoule;
        float lag = 0, temps = 0;
        final double seconde = 1000000000;

        BoucleDeJeu boucleDeJeu = new BoucleDeJeu();
        boucleDeJeu.attacher(this);
        Thread boucle = new Thread(boucleDeJeu);
        boucle.setDaemon(true);
        boucle.start();

        while (joue) {
            courant = System.nanoTime();
            ecoule = courant - precedent;
            precedent = courant;
            lag += ecoule;

            managerEtats.entreeUtilisateur(temps);

            while (lag >= TEMPS_MISE_A_JOUR) {
                managerEtats.miseAJour(ecoule);
                lag -= TEMPS_MISE_A_JOUR;
            }
            managerEtats.affichage();

            temps += ecoule;
        }
        System.out.println("Jeu fini");
    }

    @Override
    public void miseAjour() {

    }

    public void ferme() {

    }
}