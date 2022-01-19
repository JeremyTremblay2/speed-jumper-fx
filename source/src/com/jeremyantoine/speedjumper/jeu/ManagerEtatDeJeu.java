package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;

import java.util.*;

public class ManagerEtatDeJeu {
    private Map<EtatJeu, EtatDeJeu> lesEtats;
    private EtatDeJeu etatCourant;
    private EtatJeu etatJeuCourant;
    private TableauJeu jeu;

    public ManagerEtatDeJeu(TableauJeu jeu, RecuperateurDeTouches recuperateur) throws IllegalArgumentException {
        if (jeu == null) {
                throw new IllegalArgumentException("Le tableau de jeu passé en paramètre ne peut pas être null.");
        }
        this.jeu = jeu;
        lesEtats = new HashMap<>();
        chargementEtats(recuperateur);
        etatCourant = lesEtats.get(EtatJeu.ETAT_JEU_JOUE);
    }

    public EtatJeu getEtatJeuCourant() {
        return etatJeuCourant;
    }

    public EtatDeJeu getEtatCourant() {
        return etatCourant;
    }

    public void setEtatCourant(EtatJeu etat) {
        if (etat != null && !lesEtats.get(etat).equals(etatCourant)) {
            etatCourant = lesEtats.get(etat);
            etatJeuCourant = etat;
        }
    }

    public TableauJeu getJeu() {
        return jeu;
    }

    public void entreeUtilisateur(float temps) {
        EtatJeu etat = etatCourant.entreeUtilisateur(temps);
        if (etat != null && !lesEtats.get(etat).equals(etatCourant)) {
            etatCourant = lesEtats.get(etat);
            etatJeuCourant = etat;
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
        lesEtats.put(EtatJeu.ETAT_JEU_JOUE, new EtatDeJeuJoue(jeu, recuperateur));
        lesEtats.put(EtatJeu.ETAT_MENU_PAUSE, new EtatDeJeuPause(jeu, recuperateur));
    }
}
