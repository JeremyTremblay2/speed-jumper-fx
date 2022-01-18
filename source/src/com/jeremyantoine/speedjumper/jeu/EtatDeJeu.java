package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.observateurs.Sujet;

public abstract class EtatDeJeu extends Sujet {
    public abstract EtatJeu entreeUtilisateur(float temps);
    public abstract void miseAJour(float temps);
    public abstract void affichage();
}
