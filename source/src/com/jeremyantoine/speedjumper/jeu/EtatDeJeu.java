package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.entites.PersonnageJouable;
import com.jeremyantoine.speedjumper.entrees.GestionnaireActionUtilisateur;
import com.jeremyantoine.speedjumper.entrees.GestionnaireActionUtilisateurDebug;
import com.jeremyantoine.speedjumper.entrees.GestionnaireActionUtilisateurJeu;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;
import com.jeremyantoine.speedjumper.monde.Niveau;
import com.jeremyantoine.speedjumper.observateurs.Sujet;

/**
 * Classe de l'etat de jeu
 */
public abstract class EtatDeJeu extends Sujet {
    protected TableauJeu jeu;
    protected GestionnaireActionUtilisateur gestionnaireActions;
    protected Niveau niveauCourant;
    protected PersonnageJouable joueur;

    /**
     * Constructeur EtatDeJeu. Recupere le joueur , le niveau courant et le gestionnaire d'actions
     * @param jeu
     * @param recuperateur
     * @throws IllegalArgumentException
     */
    public EtatDeJeu(TableauJeu jeu, RecuperateurDeTouches recuperateur) throws IllegalArgumentException {
        if (jeu == null) {
            throw new IllegalArgumentException("Le tableau contenant les données du jeu donné en paramètre ne peut "
                    + "pas être null.");
        }
        this.jeu = jeu;
        joueur = jeu.getJoueur();
        niveauCourant = jeu.getNiveauCourant();
        gestionnaireActions = new GestionnaireActionUtilisateurJeu(recuperateur, niveauCourant);
    }

    public void raffraichirNiveauCourant() {
        niveauCourant = jeu.getNiveauCourant();
        gestionnaireActions.setNiveauCourant(niveauCourant);
    }

    /**
     * retourne le gestionnaire d'actions
     * @return
     */
    public GestionnaireActionUtilisateur getGestionnaireActions() {
        return gestionnaireActions;
    }

    public abstract EtatJeu entreeUtilisateur(float temps);
    public abstract void miseAJour(float temps);
    public abstract void affichage();
}
