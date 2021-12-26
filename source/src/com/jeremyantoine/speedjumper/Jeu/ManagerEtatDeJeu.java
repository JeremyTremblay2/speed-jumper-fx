package com.jeremyantoine.speedjumper.Jeu;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerEtatDeJeu {
    private List<EtatDeJeu> lesEtats;
    private EtatDeJeu etatCourant;

    public ManagerEtatDeJeu() {
        try {
            lesEtats = new ArrayList<>();
            lesEtats.add(new EtatDeJeuJoue());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        etatCourant = lesEtats.get(0);
    }

    public void miseAJour(double temps) {
        etatCourant.miseAJour(temps);
    }

    public void affichage() {
        etatCourant.affichage();
    }

    public void entreeUtilisateur() {
        etatCourant.entreeUtilisateur();
    }
}
