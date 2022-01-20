package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.actions.Chuteur;
import com.jeremyantoine.speedjumper.actions.CollisionneurAABB;
import com.jeremyantoine.speedjumper.actions.CollisionneurPointRectangle;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.Vivant;
import com.jeremyantoine.speedjumper.entrees.Commande;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EtatDeJeuJoue extends EtatDeJeu {
    private List<Entite> lesEntites;
    private Chuteur chuteur;
    private CollisionneurAABB collisionneur;
    private CollisionneurPointRectangle collisionneurPointRectangle;
    private Rectangle collisionJoueur;

    public EtatDeJeuJoue(TableauJeu jeu, RecuperateurDeTouches recuperateur) throws IllegalArgumentException {
        super(jeu, recuperateur);
        lesEntites = niveauCourant.getLesEntites();
        chuteur = new Chuteur(niveauCourant.getCarte());
        collisionneur = new CollisionneurAABB();
        collisionneurPointRectangle = new CollisionneurPointRectangle();
    }

    @Override
    public EtatJeu entreeUtilisateur(float temps) {
        List<Commande> actions = gestionnaireActions.attribuerAction();
        for (Commande commande : actions) {
            commande.execute(joueur, temps);
        }

        if (jeu.isGameOver()) {
            System.out.println("perdu");
            return EtatJeu.ETAT_JEU_PERDU;
        }

        if (gestionnaireActions.isPause()) {
            return EtatJeu.ETAT_MENU_PAUSE;
        }

        if (collisionneurPointRectangle.collisionne(niveauCourant.getPointArrivee(), collisionJoueur)) {
            System.out.println("fini");
            return EtatJeu.ETAT_JEU_VICTOIRE;
        }

        return null;
    }

    @Override
    public void miseAJour(float temps) {
        gestionEnnemis(temps);

        chuteur.miseAJourEtatDeJeu(joueur, temps);
        new Thread(chuteur).start();

        collisionJoueur = new Rectangle(joueur.getPosition(),
                new Dimension(joueur.getPosition().getX() + joueur.getCollision().getPosition().getX(),
                        joueur.getPosition().getY() + joueur.getCollision().getPosition().getY()));

        double positionPersonnageY = joueur.getPosition().getY() / niveauCourant.getCarte().getDimensionTuiles().getHauteur();
        System.out.println(positionPersonnageY);
        if (positionPersonnageY >= niveauCourant.getCarte().getDimension().getHauteur() - 1) {
            joueur.setPointsDeVie(0);
        }
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

        //On peut faire un visiteur pour éviter les casts ici, je n'ai pas eu le temps de le faire
        Iterator<Entite> iterateur = lesEntites.iterator();
        List<Entite> entiteASupprimer = new ArrayList<>();
        while (iterateur.hasNext()) {
            Entite entiteCourante = iterateur.next();
            if (entiteCourante instanceof Vivant vivant) {
                if (collisionneur.collisionne(joueur.getCollision(), vivant.getCollision())) {
                    joueur.setPointsDeVie(joueur.getPointsDeVie() - vivant.getDegats());
                    entiteASupprimer.add(vivant);
                }
            }
        }
        lesEntites.removeAll(entiteASupprimer);

        niveauCourant.getOmbre().ajouterPosition(joueur.getPosition());
    }
}
