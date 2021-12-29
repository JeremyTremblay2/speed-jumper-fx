package com.jeremyantoine.speedjumper.jeu;

public abstract class EtatDeJeu extends Sujet {
    public abstract EtatJeu entreeUtilisateur(float temps);
    public abstract void miseAJour(float temps);
    public abstract void affichage();
}
