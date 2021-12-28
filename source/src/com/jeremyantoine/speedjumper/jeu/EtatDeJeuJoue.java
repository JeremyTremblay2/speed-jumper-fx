package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.actions.AdaptateurDeplaceur;
import com.jeremyantoine.speedjumper.actions.CollisionneurAABB;
import com.jeremyantoine.speedjumper.actions.CollisionneurCarte;
import com.jeremyantoine.speedjumper.comportement.ComportementNull;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.PersonnageJouable;
import com.jeremyantoine.speedjumper.entites.Vivant;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouchesFX;
import com.jeremyantoine.speedjumper.entrees.Touche;
import com.jeremyantoine.speedjumper.logique.*;
import com.jeremyantoine.speedjumper.monde.CameraCarteTuiles;
import com.jeremyantoine.speedjumper.monde.Niveau;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EtatDeJeuJoue extends EtatDeJeu {
    private static final AdaptateurDeplaceur deplaceur = new AdaptateurDeplaceur(Direction.DROITE);
    private static final Dimension DIMENSION_CAMERA_PAR_DEFAUT = new Dimension(32, 20);
    private RecuperateurDeTouches recuperateurDeTouches;
    private AffichageJeu afficheur;
    private List<Touche> lesTouches = new ArrayList<>();
    private List<Niveau> lesNiveaux = new ArrayList<>();
    private PersonnageJouable joueur;
    private Niveau niveauCourant;
    private CameraCarteTuiles camera;

    public EtatDeJeuJoue() throws MalformedURLException {
        recuperateurDeTouches = new RecuperateurDeTouchesFX(new URL("file:D:\\Cours\\2021-2022\\S1\\Conception et Prog Avancée\\speed-jumper\\source\\ressources\\touches.txt"), null);

        initialisation();
        setNiveauCourant(0);
        afficheur = new AffichageJeu(niveauCourant, joueur, camera);
    }

    public void setNiveauCourant(int niveau) {
        niveauCourant = lesNiveaux.get(niveau);
    }

    private void initialisation() {
        //charger ressources
        joueur = new PersonnageJouable(new Position2D(0, 0),
                new Rectangle(10, 10, 20, 20),
                new ComportementNull(),
                5);
        camera = new CameraCarteTuiles(niveauCourant.getCarte(), DIMENSION_CAMERA_PAR_DEFAUT);
    }

    @Override
    public EtatDeJeu entreeUtilisateur() {
        lesTouches = recuperateurDeTouches.detecte();
        if (lesTouches.contains(Touche.ECHAP)) {
            return lesEtatsTransitoires.get(EtatJeu.ETAT_MENU_PAUSE);
        }
        return null;
    }

    @Override
    public void miseAJour(double temps) {
        if (lesTouches.contains(Touche.FLECHE_DROITE) || lesTouches.contains(Touche.FLECHE_GAUCHE)) {
            Position2D positionFuture = null;
            if (lesTouches.contains(Touche.FLECHE_DROITE)) {
                positionFuture = new Position2D(joueur.getPosition().getX() + joueur.getVelocite(),
                        joueur.getPosition().getY());
                deplaceur.setDirection(Direction.DROITE);
            }
            if (lesTouches.contains(Touche.FLECHE_GAUCHE)) {
                positionFuture = new Position2D(joueur.getPosition().getX() - joueur.getVelocite(),
                        joueur.getPosition().getY());
                deplaceur.setDirection(Direction.GAUCHE);
            }
            Rectangle collisionJoueur = new Rectangle(joueur.getCollision().getPosition().getX() + positionFuture.getX(),
                    joueur.getCollision().getPosition().getY() + positionFuture.getY(),
                    joueur.getCollision().getDimension().getLargeur(),
                    joueur.getCollision().getDimension().getHauteur());
            if (!CollisionneurCarte.collisionne(collisionJoueur, niveauCourant.getCarte())) {
                deplaceur.miseAJourEtatDeJeu(joueur, temps);
                camera.centrerSurEntite(joueur);
            }
        }

        gestionEnnemis(temps);

        if (joueur.getPointsDeVie() <= 0) {
            System.out.println("Jeu terminé, vous avez perdu!");
        }
    }

    @Override
    public void affichage() {
        afficheur.affiche();
    }

    private void gestionEnnemis(double temps) {
        List<Entite> lesEntites = niveauCourant.getLesEntites();

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
