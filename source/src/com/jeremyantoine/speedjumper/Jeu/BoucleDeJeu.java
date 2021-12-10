package com.jeremyantoine.speedjumper.Jeu;

import java.util.List;

public class BoucleDeJeu implements Runnable, Notificateur {
    private List<Observateur> lesObservateurs;
    public static final long NOMBRE_MILLISECONDES_AVANT_NOTIFICATION = 5000000;
    private boolean enCours = true;

    public BoucleDeJeu(List<Observateur> observateurs) {
        lesObservateurs = observateurs;
    }

    @Override
    public void run() {
        long tempsCourant;
        long tempsDerniereIteration = System.nanoTime();
        while (enCours) {
            tempsCourant = System.nanoTime();
            if (tempsCourant - tempsDerniereIteration >= NOMBRE_MILLISECONDES_AVANT_NOTIFICATION) {
                notifier();
            }
        }
    }

    @Override
    public void attacher(Observateur o) {
        lesObservateurs.add(o);
    }

    @Override
    public void detacher(Observateur o) {
        lesObservateurs.remove(o);
    }

    @Override
    public void notifier() {
        for (Observateur o : lesObservateurs) {
            o.miseAjour();
        }
    }
}
