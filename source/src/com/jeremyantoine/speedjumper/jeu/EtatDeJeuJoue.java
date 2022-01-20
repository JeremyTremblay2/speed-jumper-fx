package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.actions.Chuteur;
import com.jeremyantoine.speedjumper.actions.CollisionneurAABB;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.Vivant;
import com.jeremyantoine.speedjumper.entrees.Commande;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;

import java.util.List;

public class EtatDeJeuJoue extends EtatDeJeu {
    private List<Entite> lesEntites;
    private Chuteur chuteur;
    private CollisionneurAABB collisionneur;

    public EtatDeJeuJoue(TableauJeu jeu, RecuperateurDeTouches recuperateur) throws IllegalArgumentException {
        super(jeu, recuperateur);
        lesEntites = niveauCourant.getLesEntites();
        chuteur = new Chuteur(niveauCourant.getCarte());
        collisionneur = new CollisionneurAABB();
    }

    @Override
    public EtatJeu entreeUtilisateur(float temps) {
        Commande action = gestionnaireActions.attribuerAction();
        if (action != null) {
            action.execute(joueur, temps);
        }

        if (jeu.isGameOver()) {
            return EtatJeu.ETAT_JEU_PERDU;
        }
        return null;
    }

    @Override
    public void miseAJour(float temps) {
        gestionEnnemis(temps);
    }

    @Override
    public void affichage() {
        //Ne fait rien ici hormis notifier qu'il faut mettre à jour tout l'affichage.
        notifier();
    }

    private void gestionEnnemis(double temps) {
        for (Entite entite : lesEntites) {
            chuteur.miseAJourEtatDeJeu(entite, temps);
            new Thread(chuteur).start();
            entite.miseAJour(temps);
        }

        chuteur.miseAJourEtatDeJeu(joueur, temps);
        new Thread(chuteur).start();

        for (Entite entite : lesEntites) {
            if (entite instanceof Vivant) {
                if (collisionneur.collisionne(joueur.getCollision(), entite.getCollision())) {
                    joueur.setPointsDeVie(joueur.getPointsDeVie() - ((Vivant) entite).getDegats());
                }
            }
        }

        niveauCourant.getOmbre().ajouterPosition(joueur.getPosition());
    }
}
