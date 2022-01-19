package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.actions.CollisionneurAABB;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.Vivant;
import com.jeremyantoine.speedjumper.entrees.Commande;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;

import java.util.List;

public class EtatDeJeuJoue extends EtatDeJeu {
    private List<Entite> lesEntites;

    public EtatDeJeuJoue(TableauJeu jeu, RecuperateurDeTouches recuperateur) throws IllegalArgumentException {
        super(jeu, recuperateur);
        lesEntites = niveauCourant.getLesEntites();
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



//        if (lesTouches.contains(Touche.ECHAP)) {
//            return EtatJeu.ETAT_MENU_PAUSE;
//        }
    }

    @Override
    public void affichage() {
        //Ne fait rien ici hormis notifier qu'il faut mettre à jour tout l'affichage.
        notifier();
    }

    private void gestionEnnemis(double temps) {
        System.out.println(joueur);
        for (Entite entite : lesEntites) {
            entite.miseAJour(temps);
        }

        for (Entite entite : lesEntites) {
            if (entite instanceof Vivant) {
                if (CollisionneurAABB.collisionne(joueur.getCollision(), entite.getCollision())) {
                    joueur.setPointsDeVie(joueur.getPointsDeVie() - ((Vivant) entite).getDegats());
                }
            }
        }

        niveauCourant.getOmbre().ajouterPosition(joueur.getPosition());
    }
}
