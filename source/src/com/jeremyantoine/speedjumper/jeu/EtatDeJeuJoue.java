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

/**
 * Classe EtatDeJeuJoue  gerant les actions lors du jeu
 */
public class EtatDeJeuJoue extends EtatDeJeu {
    private List<Entite> lesEntites;
    private Chuteur chuteur;
    private CollisionneurAABB collisionneur;
    private CollisionneurPointRectangle collisionneurPointRectangle;
    private Rectangle collisionJoueur;

    /**
     * Constructeur EtatDeJeuJoue. Recupere les entites du niveau et initialiser le chuteur
     * @param jeu le jeu
     * @param recuperateur le recuperateur de touche
     * @throws IllegalArgumentException
     */
    public EtatDeJeuJoue(TableauJeu jeu, RecuperateurDeTouches recuperateur) throws IllegalArgumentException {
        super(jeu, recuperateur);
        lesEntites = niveauCourant.getLesEntites();
        chuteur = new Chuteur(niveauCourant.getCarte());
        collisionneur = new CollisionneurAABB();
        collisionneurPointRectangle = new CollisionneurPointRectangle();
    }

    /**
     * Methode qui gere les entrée utilisateur lors du temps
     * @param temps
     * @return
     */
    @Override
    public EtatJeu entreeUtilisateur(float temps) {
        List<Commande> actions = gestionnaireActions.attribuerAction();
        for (Commande commande : actions) {
            commande.execute(joueur, temps);
        }

        if (jeu.isGameOver()) {
            //System.out.println("perdu");
            return EtatJeu.ETAT_JEU_PERDU;
        }

        if (gestionnaireActions.isPause()) {
            //System.out.println("pause");
            gestionnaireActions.setPause(false);
            return EtatJeu.ETAT_MENU_PAUSE;
        }

        System.out.println(collisionJoueur);

        /*
        Implémenter le système de détection de fin de niveau.
        if (collisionneurPointRectangle.collisionne(niveauCourant.getPointArrivee(), joueur.getCollision())) {
            System.out.println("fini");
            return EtatJeu.ETAT_JEU_VICTOIRE;
        }
         */
        return null;
    }

    /**
     * mise a jour de la gestion des enemis
     * @param temps
     */
    @Override
    public void miseAJour(float temps) {
        gestionEnnemis(temps);

        chuteur.miseAJourEtatDeJeu(joueur, temps);
        new Thread(chuteur).start();

        double positionPersonnageY = joueur.getPosition().getY() / niveauCourant.getCarte().getDimensionTuiles().getHauteur();
        if (positionPersonnageY >= niveauCourant.getCarte().getDimension().getHauteur() - 1) {
            joueur.setPointsDeVie(0);
        }
    }

    /**
     * Ne fait rien ici hormis notifier qu'il faut mettre à jour tout l'affichage.
     */
    @Override
    public void affichage() {
        //Ne fait rien ici hormis notifier qu'il faut mettre à jour tout l'affichage.
        notifier();
    }

    @Override
    public void raffraichirNiveauCourant() {
        super.raffraichirNiveauCourant();
        lesEntites = niveauCourant.getLesEntites();
        chuteur = new Chuteur(niveauCourant.getCarte());
    }


    /**
     * Methode permettant de gérant les énemis sur un niveau ( action, déplacement .. )
     * @param temps
     */
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
    }
}
