package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.observateurs.Sujet;
import javafx.application.Platform;

public class BoucleDeJeu extends Sujet implements Runnable {
    private static final long SECONDE_EN_NANOSECONDE = 1000000000;
    public static final long FPS_CIBLE = 60;
    public static final long NOMBRE_NANOSECONDES_AVANT_NOTIFICATION =  SECONDE_EN_NANOSECONDE / FPS_CIBLE;
    private boolean enCours;
    private long tempsEcoule;

    public BoucleDeJeu() {
        enCours = true;
    }

    public void setEnCours(boolean enCours) {
        this.enCours = enCours;
    }

    public long getTempsEcoule() {
        return tempsEcoule;
    }

    @Override
    public void run() {
        long tempsCourant, ecoule;
        long tempsDerniereIteration = System.nanoTime();
        while (enCours) {
            tempsCourant = System.nanoTime();
            ecoule = tempsCourant - tempsDerniereIteration;
            if (ecoule >= NOMBRE_NANOSECONDES_AVANT_NOTIFICATION) {
                tempsEcoule = ecoule;
                Platform.runLater(this::notifier);
                tempsDerniereIteration = tempsCourant;
            }
        }
    }
}
