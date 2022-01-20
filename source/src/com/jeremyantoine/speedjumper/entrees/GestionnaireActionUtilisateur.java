package com.jeremyantoine.speedjumper.entrees;

import java.util.ArrayList;
import java.util.List;

public abstract class GestionnaireActionUtilisateur {
    protected RecuperateurDeTouches recuperateurDeTouches;
    protected List<Touche> lesTouches;
    protected List<Commande> lesCommandes;
    protected boolean pause;

    public GestionnaireActionUtilisateur(RecuperateurDeTouches recuperateur) {
        recuperateurDeTouches = recuperateur;
        lesCommandes = new ArrayList<>();
        pause = false;
    }

    public RecuperateurDeTouches getRecuperateurDeTouches() {
        return recuperateurDeTouches;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public abstract List<Commande> attribuerAction();
}

