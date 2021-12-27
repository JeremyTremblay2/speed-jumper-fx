package com.jeremyantoine.speedjumper.jeu;

import java.util.HashMap;
import java.util.Map;

public abstract class EtatDeJeu extends Sujet {
    protected Map<EtatJeu, EtatDeJeu> lesEtatsTransitoires;

    public EtatDeJeu() {
        lesEtatsTransitoires = new HashMap<>();
    }

    public abstract EtatDeJeu entreeUtilisateur();
    public abstract void miseAJour(double temps);
    public abstract void affichage();
}
