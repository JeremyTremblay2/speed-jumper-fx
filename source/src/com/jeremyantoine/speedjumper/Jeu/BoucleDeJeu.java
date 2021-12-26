package com.jeremyantoine.speedjumper.Jeu;

public class BoucleDeJeu extends Sujet implements Runnable {
    public static final long NOMBRE_MILLISECONDES_AVANT_NOTIFICATION = 5000000;
    private boolean enCours = true;

    public BoucleDeJeu() {
    }

    @Override
    public void run() {
//        long tempsCourant;
//        long tempsDerniereIteration = System.nanoTime();
        while (enCours) {
//            tempsCourant = System.nanoTime();
//            if (tempsCourant - tempsDerniereIteration >= NOMBRE_MILLISECONDES_AVANT_NOTIFICATION) {
                notifier();
//            }
        }
    }
}
