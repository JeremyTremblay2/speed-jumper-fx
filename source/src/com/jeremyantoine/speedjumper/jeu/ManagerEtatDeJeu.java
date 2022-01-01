package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;

import java.util.*;

public class ManagerEtatDeJeu {
    private Map<EtatJeu, EtatDeJeu> lesEtats;
    private EtatDeJeu etatCourant;

    public ManagerEtatDeJeu(RecuperateurDeTouches recuperateur) {
        lesEtats = new HashMap<>();
        chargementEtats(recuperateur);
        etatCourant = lesEtats.get(EtatJeu.ETAT_JEU_JOUE);
    }

    public EtatDeJeu getEtatCourant() {
        return etatCourant;
    }

    public void entreeUtilisateur(float temps) {
        EtatJeu etat = etatCourant.entreeUtilisateur(temps);
        if (etat != null && !lesEtats.get(etat).equals(etatCourant)) {
            etatCourant = lesEtats.get(etat);
        }
    }

    public void miseAJour(float temps) {
        etatCourant.miseAJour(temps);
    }

    public void affichage() {
        etatCourant.affichage();
    }

    public void ajouterEtat(EtatJeu etat, EtatDeJeu etatConcret) throws IllegalArgumentException {
        if (etatConcret == null) {
            throw new IllegalArgumentException("L'état à ajouter donné en paramètre ne peut pas être null.");
        }
        lesEtats.put(etat, etatConcret);
    }

    public void supprimerEtat(EtatJeu etat) {
        lesEtats.remove(etat);
    }

    private void chargementEtats(RecuperateurDeTouches recuperateur) {
        lesEtats.put(EtatJeu.ETAT_JEU_JOUE, new EtatDeJeuJoue(recuperateur));
    }
}
