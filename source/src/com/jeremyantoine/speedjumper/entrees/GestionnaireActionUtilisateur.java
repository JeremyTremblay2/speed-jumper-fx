package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.monde.Niveau;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite gérant les actions utilisateur s
 */
public abstract class GestionnaireActionUtilisateur {
    protected RecuperateurDeTouches recuperateurDeTouches;
    protected List<Touche> lesTouches;
    protected List<Commande> lesCommandes;
    protected boolean pause;
    protected Niveau niveauCourant;

    public GestionnaireActionUtilisateur(RecuperateurDeTouches recuperateur, Niveau niveau) {
        if (niveau == null) {
            throw new IllegalArgumentException("Le niveau passé en paramètre ne peut pas être null.");
        }
        niveauCourant = niveau;
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

    public void setNiveauCourant(Niveau niveauCourant) {
        this.niveauCourant = niveauCourant;
    }

    public abstract List<Commande> attribuerAction();
}

