package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.actions.AdaptateurDeplaceur;
import com.jeremyantoine.speedjumper.actions.CollisionneurAABB;
import com.jeremyantoine.speedjumper.comportement.ComportementNull;
import com.jeremyantoine.speedjumper.donnees.*;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.PersonnageJouable;
import com.jeremyantoine.speedjumper.entites.Vivant;
import com.jeremyantoine.speedjumper.entrees.Commande;
import com.jeremyantoine.speedjumper.entrees.GestionnaireActionUtilisateur;
import com.jeremyantoine.speedjumper.entrees.GestionnaireActionUtilisateurJeu;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;
import com.jeremyantoine.speedjumper.logique.*;
import com.jeremyantoine.speedjumper.monde.CameraCarteTuiles;
import com.jeremyantoine.speedjumper.monde.Carte2D;
import com.jeremyantoine.speedjumper.monde.Niveau;

import java.util.ArrayList;
import java.util.List;

public class EtatDeJeuJoue extends EtatDeJeu {
    private static final Dimension DIMENSION_CAMERA_PAR_DEFAUT = new Dimension(18, 18);
    private AdaptateurDeplaceur deplaceur = new AdaptateurDeplaceur(Direction.DROITE);
    private GestionnaireDeRessources gestionnaireDeRessources;
    private GestionnaireActionUtilisateur gestionnaireActions;
    private List<Niveau> lesNiveaux = new ArrayList<>();
    private PersonnageJouable joueur;
    private Niveau niveauCourant;
    private CameraCarteTuiles camera;

    public EtatDeJeuJoue(RecuperateurDeTouches recuperateur) {
        gestionnaireDeRessources = new GestionnaireDeRessources(new AdaptateurChargeurDeCarteTiledCSV(","),
                new ChargeurDeJeuxDeTuilesTextuel());
        gestionnaireActions = new GestionnaireActionUtilisateurJeu(recuperateur, niveauCourant);
        initialisation();
    }

    public boolean isGameOver() {
        return joueur.getPointsDeVie() <= 0;
    }

    public Niveau getNiveauCourant() {
        return niveauCourant;
    }

    public void setNiveauCourant(int niveau) {
        niveauCourant = lesNiveaux.get(niveau);
        camera.changeCarte(niveauCourant.getCarte());
        joueur.setPointsDeVie(joueur.getPointsDeViesInitiaux());
        joueur.setPosition(niveauCourant.getPointsDepart());
    }

    public PersonnageJouable getJoueur() {
        return joueur;
    }

    public CameraCarteTuiles getCamera() {
        return camera;
    }

    public GestionnaireDeRessources getGestionnaireDeRessources() {
        return gestionnaireDeRessources;
    }

    @Override
    public EtatJeu entreeUtilisateur(float temps) {
        Commande action = gestionnaireActions.attribuerAction();
        if (action != null) {
            action.execute(joueur, temps);
        }

        if (isGameOver()) {
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
        //Ne fait rien ici.
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

    private void initialisation() {
        List<Carte2D> lesCartes = new ArrayList<>();
        Niveau niveau;

        try {
            gestionnaireDeRessources.charge();
            lesCartes = gestionnaireDeRessources.getLesCartes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Carte2D carte : lesCartes) {
            niveau = new Niveau(carte, null, null, new Position2D(100, 400));
            lesNiveaux.add(niveau);
        }

        niveauCourant = lesNiveaux.get(0);

        joueur = new PersonnageJouable(new Position2D(0, 0),
                new Rectangle(10, 10, 20, 20),
                new ComportementNull(),
                5);
        camera = new CameraCarteTuiles(niveauCourant.getCarte(), DIMENSION_CAMERA_PAR_DEFAUT);
    }
}
