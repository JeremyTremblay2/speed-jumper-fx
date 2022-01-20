package com.jeremyantoine.speedjumper.observateurs;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe sujet du patron observateur .
 * Cette classe sera celle observée.
 */
public abstract class Sujet {
    private List<Observateur> lesObservateurs;

    /**
     * Constructeur du sujet.
     * Créer une ArrayList d'observateur
     */
    public Sujet() {
        lesObservateurs = new ArrayList<>();
    }

    /**
     * Attache un observateur au sujet
     * @param o Observateur auquel attaché le sujet
     */
    public void attacher(Observateur o) {
        lesObservateurs.add(o);
    }


    /**
     * Dettache l'observateur du sujet
     * @param o Observateur auquel dettaché le sujet
     */
    public void detacher(Observateur o) {
        lesObservateurs.remove(o);
    }

    /**
     * Methode pour notifier et mettre a jour les observateurs
     */
    public void notifier() {
        for (Observateur o : lesObservateurs) {
            o.miseAjour();
        }
    }
}
