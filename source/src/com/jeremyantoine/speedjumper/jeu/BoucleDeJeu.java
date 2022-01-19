package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.observateurs.Sujet;

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
        System.out.println("coucou 2");

        long tempsCourant;
        long tempsDerniereIteration = System.nanoTime();
        while (enCours) {
            tempsCourant = System.nanoTime();
            if (tempsCourant - tempsDerniereIteration >= NOMBRE_MILLISECONDES_AVANT_NOTIFICATION) {
                notifier();
                tempsDerniereIteration = tempsCourant;
            }
        }
    }
}
