package com.jeremyantoine.speedjumper.entrees;

import java.util.List;

/**
 * Classe abstraite gérant les actions utilisateur s
 */
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

