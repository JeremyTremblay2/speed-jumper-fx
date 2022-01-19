package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.observateurs.Sujet;
import javafx.application.Platform;

public class BoucleDeJeu extends Sujet implements Runnable {
    public static final long NOMBRE_MILLISECONDES_AVANT_NOTIFICATION = 1000000000 / 60;
    private boolean enCours;

    public BoucleDeJeu() {
        enCours = true;
    }

    public void setEnCours(boolean enCours) {
        this.enCours = enCours;
    }

    @Override
    public void run() {
        long tempsCourant;
        long tempsDerniereIteration = System.nanoTime();
        while (enCours) {
            tempsCourant = System.nanoTime();
            if (tempsCourant - tempsDerniereIteration >= NOMBRE_MILLISECONDES_AVANT_NOTIFICATION) {
                Platform.runLater(this::notifier);
                tempsDerniereIteration = tempsCourant;
            }
        }
    }
}
