package com.jeremyantoine.speedjumper.jeu;

import java.util.ArrayList;
import java.util.List;

public class Sujet {
    private List<Observateur> lesObservateurs;

    public Sujet() {
        lesObservateurs = new ArrayList<>();
    }

    public void attacher(Observateur o) {
        lesObservateurs.add(o);
    }

    public void detacher(Observateur o) {
        lesObservateurs.remove(o);
    }

    public void notifier() {
        for (Observateur o : lesObservateurs) {
            o.miseAjour();
        }
    }
}
