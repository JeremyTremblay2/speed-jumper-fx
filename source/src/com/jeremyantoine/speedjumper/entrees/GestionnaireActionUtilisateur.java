package com.jeremyantoine.speedjumper.entrees;

import java.util.List;

public abstract class GestionnaireActionUtilisateur {
    protected RecuperateurDeTouches recuperateurDeTouches;
    protected List<Touche> lesTouches;

    public GestionnaireActionUtilisateur(RecuperateurDeTouches recuperateur) {
        recuperateurDeTouches = recuperateur;
    }

    public RecuperateurDeTouches getRecuperateurDeTouches() {
        return recuperateurDeTouches;
    }

    public abstract Commande attribuerAction();
}

