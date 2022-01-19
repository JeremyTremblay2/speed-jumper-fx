package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.entites.PersonnageJouable;
import com.jeremyantoine.speedjumper.entrees.GestionnaireActionUtilisateur;
import com.jeremyantoine.speedjumper.entrees.GestionnaireActionUtilisateurJeu;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;
import com.jeremyantoine.speedjumper.monde.Niveau;
import com.jeremyantoine.speedjumper.observateurs.Sujet;

public abstract class EtatDeJeu extends Sujet {
    protected TableauJeu jeu;
    protected GestionnaireActionUtilisateur gestionnaireActions;
    protected Niveau niveauCourant;
    protected PersonnageJouable joueur;


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

    public abstract EtatJeu entreeUtilisateur(float temps);
    public abstract void miseAJour(float temps);
    public abstract void affichage();
}
