package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;

import java.util.*;

/**
 * Cette classe gere les différents etats du jeu
 */
public class ManagerEtatDeJeu {
    private Map<EtatJeu, EtatDeJeu> lesEtats;
    private EtatDeJeu etatCourant;
    private EtatJeu etatJeuCourant;
    private TableauJeu jeu;

    /**
     * Constructeur du managerEtatDeJeu
     * @param jeu le jeu en cour
     * @param recuperateur recuperateur de touche
     * @throws IllegalArgumentException
     */
    public ManagerEtatDeJeu(TableauJeu jeu, RecuperateurDeTouches recuperateur) throws IllegalArgumentException {
        if (jeu == null) {
                throw new IllegalArgumentException("Le tableau de jeu passé en paramètre ne peut pas être null.");
        }
        this.jeu = jeu;
        lesEtats = new HashMap<>();
        chargementEtats(recuperateur);
        etatCourant = lesEtats.get(EtatJeu.ETAT_JEU_JOUE);
    }

    /**
     * retourne l'etat du jeu courant
     * @return
     */
    public EtatJeu getEtatJeuCourant() {
        return etatJeuCourant;
    }

    /**
     * retourne l'etat courant
     * @return
     */
    public EtatDeJeu getEtatCourant() {
        return etatCourant;
    }

    /**
     * sezt l'etat courrant
     * @param etat
     */
    public void setEtatCourant(EtatJeu etat) {
        if (etat != null && !lesEtats.get(etat).equals(etatCourant)) {
            etatCourant = lesEtats.get(etat);
            etatJeuCourant = etat;
        }
    }

    /**
     * retourne le jeu
     * @return
     */
    public TableauJeu getJeu() {
        return jeu;
    }

    /**
     * gere les entrees utilisateurs et modifiant l'etat courant et l'etatJeuCourant
     * @param temps
     */
    public void entreeUtilisateur(float temps) {
        EtatJeu etat = etatCourant.entreeUtilisateur(temps);
        if (etat != null && !lesEtats.get(etat).equals(etatCourant)) {
            etatCourant = lesEtats.get(etat);
            etatJeuCourant = etat;
        }
    }

    /**
     * Met a jour l 'etat courrant en donction du temps
     * @param temps
     */
    public void miseAJour(float temps) {
        etatCourant.miseAJour(temps);
    }

    /**
     * Affiche l'etat courant
     */
    public void affichage() {
        etatCourant.affichage();
    }

    /**
     * Ajoute un  état
     * @param etat l'etat du jeu
     * @param etatConcret correspondance entre l'etat et un etat de jeu concret
     * @throws IllegalArgumentException
     */
    public void ajouterEtat(EtatJeu etat, EtatDeJeu etatConcret) throws IllegalArgumentException {
        if (etatConcret == null) {
            throw new IllegalArgumentException("L'état à ajouter donné en paramètre ne peut pas être null.");
        }
        lesEtats.put(etat, etatConcret);
    }

    /**
     * Supprime un etat
     * @param etat etat a supprimer
     */
    public void supprimerEtat(EtatJeu etat) {
        lesEtats.remove(etat);
    }

    /**
     * charge les etats de jeu
     * @param recuperateur
     */
    private void chargementEtats(RecuperateurDeTouches recuperateur) {
        lesEtats.put(EtatJeu.ETAT_JEU_JOUE, new EtatDeJeuJoue(jeu, recuperateur));
        lesEtats.put(EtatJeu.ETAT_MENU_PAUSE, new EtatDeJeuPause(jeu, recuperateur));
    }
}
