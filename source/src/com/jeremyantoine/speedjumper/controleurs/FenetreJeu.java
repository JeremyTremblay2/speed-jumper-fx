package com.jeremyantoine.speedjumper.controleurs;

import com.jeremyantoine.speedjumper.affichages.ArrierePlanFX;
import com.jeremyantoine.speedjumper.affichages.EntiteFX;
import com.jeremyantoine.speedjumper.affichages.TuileFX;
import com.jeremyantoine.speedjumper.cameras.CameraCarteTuilesFX;
import com.jeremyantoine.speedjumper.donnees.GestionnaireDeRessourcesFX;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.PersonnageJouable;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouchesFX;
import com.jeremyantoine.speedjumper.jeu.*;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.monde.ArrierePlan;
import com.jeremyantoine.speedjumper.monde.Niveau;
import com.jeremyantoine.speedjumper.monde.Tuile;
import com.jeremyantoine.speedjumper.observateurs.Observateur;
import com.jeremyantoine.speedjumper.utilitaire.Navigateur;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;

/**
 * Controleur pour la fenetre de jeu
 */
public class FenetreJeu implements Observateur {
    private static final Dimension DIMENSION_CAMERA_PAR_DEFAUT = new Dimension(30,22);
    private static final float ECHELLE_CARTE = 2;

    private Navigateur navigateur;
    private Jeu jeu;

    private Scene scene;
    private GraphicsContext contexteGraphique;

    private Niveau niveauCourant;
    private List<TuileFX> lesTuilesGraphiques;
    private List<EntiteFX> lesEntitesGraphiques;
    private CameraCarteTuilesFX camera;
    private PersonnageJouable joueur;
    private List<ArrierePlanFX> lesArrierePlansGraphiques;

    private GestionnaireDeRessourcesFX gestionnaireDeRessources;

    private Dimension tailleCaneva;
    private int largeurCamera;
    private int hauteurCamera;
    private int largeurTuile;
    private int hauteurTuile;

    /**
     * Constructeur de la fenetre
     * @param jeu
     */
    public FenetreJeu(Navigateur navigateur, Jeu jeu) {
        if (navigateur == null || jeu == null) {
            throw new IllegalArgumentException("Le navigateur ou le jeu passé en paramètre ne peut pas être null.");
        }
        this.navigateur = navigateur;
        this.jeu = jeu;

        lesTuilesGraphiques = new ArrayList<>();
        lesEntitesGraphiques = new ArrayList<>();
        lesArrierePlansGraphiques = new ArrayList<>();


        gestionnaireDeRessources = new GestionnaireDeRessourcesFX();
        niveauCourant = jeu.getJeu().getNiveauCourant();
        joueur = jeu.getJeu().getJoueur();
        camera = new CameraCarteTuilesFX(niveauCourant.getCarte(), DIMENSION_CAMERA_PAR_DEFAUT, lesTuilesGraphiques);

        largeurCamera = (int) camera.getZoneVisuelle().getLargeur();
        hauteurCamera = (int) camera.getZoneVisuelle().getHauteur();
        largeurTuile = (int) niveauCourant.getCarte().getDimensionTuiles().getLargeur();
        hauteurTuile = (int) niveauCourant.getCarte().getDimensionTuiles().getHauteur();

        tailleCaneva = new Dimension((DIMENSION_CAMERA_PAR_DEFAUT.getLargeur() - 2) * (largeurTuile / ECHELLE_CARTE),
                (DIMENSION_CAMERA_PAR_DEFAUT.getHauteur() - 2) * (largeurTuile / ECHELLE_CARTE));

        StackPane lesCouches = new StackPane();
        Canvas caneva = new Canvas(tailleCaneva.getLargeur(), tailleCaneva.getHauteur());
        scene = new Scene(lesCouches);
        lesCouches.getChildren().add(caneva);
        contexteGraphique = caneva.getGraphicsContext2D();

        initialisation();
    }

    /**
     * retourne la scene actuelle
     * @return
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Methode qui gere l'affichage
     */
    public void affichage() {
        camera.centrerSurEntite(joueur);
        contexteGraphique.clearRect(0, 0, tailleCaneva.getLargeur(), tailleCaneva.getHauteur());

        contexteGraphique.drawImage(lesArrierePlansGraphiques.get(0).getImage(), 0, 0);

        for (int x = 0; x < hauteurCamera - 1; x++) {
            for (int y = 0; y < largeurCamera - 1; y++) {
                contexteGraphique.drawImage(camera.getTuileGraphique(x, y).getImage(),
                        y * (largeurTuile / ECHELLE_CARTE) - camera.getDecalageRelatif().getLargeur() / ECHELLE_CARTE,
                        x * (hauteurTuile / ECHELLE_CARTE) - camera.getDecalageRelatif().getHauteur() / ECHELLE_CARTE,
                        largeurTuile / ECHELLE_CARTE,
                        hauteurTuile / ECHELLE_CARTE);
            }
        }

        for (EntiteFX entite : lesEntitesGraphiques) {
            contexteGraphique.drawImage(entite.getImageCourante(),
                    entite.getEntite().getPosition().getX() / ECHELLE_CARTE
                            - camera.getPosition().getX() * (largeurTuile / ECHELLE_CARTE)
                            - camera.getDecalageRelatif().getLargeur() / ECHELLE_CARTE,
                    entite.getEntite().getPosition().getY() / ECHELLE_CARTE
                            - camera.getPosition().getY() * (hauteurTuile / ECHELLE_CARTE)
                            - camera.getDecalageRelatif().getHauteur() / ECHELLE_CARTE,
                    20,
                    40);
        }
    }

    /**
     * Methode qui met a jour l'affichage
     */
    @Override
    public void miseAjour() {
        if (jeu.getManagerEtats().getEtatJeuCourant() == EtatJeu.ETAT_MENU_PAUSE) {
            System.out.println("navigation");
            MenuPause menu = new MenuPause(navigateur, jeu);
            navigateur.naviguerVers(NomFenetre.MENU_PAUSE, menu);
        }
        if (jeu.getManagerEtats().getEtatJeuCourant() == EtatJeu.ETAT_JEU_PERDU) {
            //MenuDefaite menu = new MenuDefaite(navigateur, jeu);
            //navigateur.naviguerVers(NomFenetre.MENU_DEFAITE, menu);
        }
        if (jeu.getManagerEtats().getEtatJeuCourant() == EtatJeu.ETAT_JEU_VICTOIRE) {
            //MenuVictoire menu = new MenuVictoire(navigateur, jeu);
            //navigateur.naviguerVers(NomFenetre.MENU_VICTOIRE, menu);
        }
        else {
            affichage();
        }
    }

    /**
     * Initialiosation des données
     */
    private void initialisation() {
        try {
            gestionnaireDeRessources.charge();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        List<Tuile> lesTuiles;
        List<Entite> lesEntites;
        List<Image> lesTuilesImagees, lesEntitesImagees, lesPersonnagesImages, lesArrieresPlans;

        lesTuiles = jeu.getJeu().getGestionnaireDeRessources().getLesTuiles();
        lesTuilesImagees = gestionnaireDeRessources.getLesTuilesImagees();

        lesEntites = niveauCourant.getLesEntites();
        lesEntitesImagees = gestionnaireDeRessources.getLesEnnemisImages();
        lesArrieresPlans = gestionnaireDeRessources.getLesArrieresPlans();

        lesPersonnagesImages = gestionnaireDeRessources.getLesPersonnagesImages();
        lesEntitesGraphiques.add(new EntiteFX(joueur, lesPersonnagesImages));

        for (int i = 0; i < lesTuiles.size(); i++) {
            lesTuilesGraphiques.add(new TuileFX(lesTuiles.get(i), lesTuilesImagees.get(i)));
        }

        for (int i = 0; i < lesEntites.size(); i++) {
            lesEntitesGraphiques.add(new EntiteFX(lesEntites.get(i), lesEntitesImagees));
        }

        ArrierePlan arrierePlan = new ArrierePlan(0, 0, new Dimension(1920, 1080));

        for (Image lesArrieresPlan : lesArrieresPlans) {
            lesArrierePlansGraphiques.add(new ArrierePlanFX(arrierePlan, lesArrieresPlan));
        }

        //Initialiase la détection des touches, le système de notification, et démarre le jeu
        ((RecuperateurDeTouchesFX) jeu.getManagerEtats().getEtatCourant()
                .getGestionnaireActions().getRecuperateurDeTouches()).setSceneCourante(scene);
        jeu.getManagerEtats().setEtatCourant(EtatJeu.ETAT_JEU_JOUE);
        jeu.getManagerEtats().attacher(this);
        jeu.getManagerEtats().getEtatCourant().attacher(this);

        affichage();
    }
}
