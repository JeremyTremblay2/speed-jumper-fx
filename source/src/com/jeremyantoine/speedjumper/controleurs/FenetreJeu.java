package com.jeremyantoine.speedjumper.controleurs;

import com.jeremyantoine.speedjumper.affichages.EntiteFX;
import com.jeremyantoine.speedjumper.affichages.TuileFX;
import com.jeremyantoine.speedjumper.cameras.CameraCarteTuilesFX;
import com.jeremyantoine.speedjumper.donnees.GestionnaireDeRessourcesFX;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.PersonnageJouable;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouchesFX;
import com.jeremyantoine.speedjumper.jeu.*;
import com.jeremyantoine.speedjumper.logique.Dimension;
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

public class FenetreJeu implements Observateur {
    private static final Dimension DIMENSION_CAMERA_PAR_DEFAUT = new Dimension(25,18);
    private Navigateur navigateur;
    private Jeu jeu;

    private Scene scene;
    private Canvas caneva;
    private StackPane lesCouches;
    private GraphicsContext contexteGraphique;

    private Niveau niveauCourant;
    private List<TuileFX> lesTuilesGraphiques;
    private List<EntiteFX> lesEntitesGraphiques;
    private CameraCarteTuilesFX camera;
    private PersonnageJouable joueur;

    private GestionnaireDeRessourcesFX gestionnaireDeRessources;

    private int largeurCamera;
    private int hauteurCamera;

    private int largeurTuile;
    private int hauteurTuile;


    public FenetreJeu(Navigateur navigateur, Jeu jeu) {
        if (navigateur == null || jeu == null) {
            throw new IllegalArgumentException("Le navigateur ou le jeu passé en paramètre ne peut pas être null.");
        }
        this.navigateur = navigateur;
        this.jeu = jeu;

        lesTuilesGraphiques = new ArrayList<>();
        lesEntitesGraphiques = new ArrayList<>();

        lesCouches = new StackPane();
        caneva = new Canvas(1000, 1000);
        scene = new Scene(lesCouches);
        lesCouches.getChildren().add(caneva);
        contexteGraphique = caneva.getGraphicsContext2D();

        gestionnaireDeRessources = new GestionnaireDeRessourcesFX();
        niveauCourant = jeu.getJeu().getNiveauCourant();
        joueur = jeu.getJeu().getJoueur();
        initialisation();
    }

    public Scene getScene() {
        return scene;
    }

    public void affichage() {
        System.out.println("dans laffichage'");

        for (int x = 0; x < hauteurCamera; x++) {
            for (int y = 0; y < largeurCamera; y++) {
                contexteGraphique.drawImage(camera.getTuileGraphique(x, y).getImage(),
                        y * largeurTuile,
                        x * hauteurTuile,
                        largeurTuile,
                        hauteurTuile);
            }
        }

        for (EntiteFX entite : lesEntitesGraphiques) {
            contexteGraphique.drawImage(entite.getImageCourante(),
                    entite.getEntite().getPosition().getX(),
                    entite.getEntite().getPosition().getY(),
                    entite.getEntite().getDimension().getLargeur(),
                    entite.getEntite().getDimension().getHauteur());
        }
    }

    @Override
    public void miseAjour() {
        if (jeu.getManagerEtats().getEtatJeuCourant() == EtatJeu.ETAT_MENU_PAUSE) {
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

    private void initialisation() {
        try {
            gestionnaireDeRessources.charge();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //Initialiase la détection des touches, le système de notification, et démarre le jeu
        ((RecuperateurDeTouchesFX) jeu.getManagerEtats().getEtatCourant()
                .getGestionnaireActions().getRecuperateurDeTouches()).setSceneCourante(scene);
        jeu.getManagerEtats().setEtatCourant(EtatJeu.ETAT_JEU_JOUE);
        jeu.getManagerEtats().getEtatCourant().attacher(this);

        List<Tuile> lesTuiles;
        List<Entite> lesEntites;
        List<Image> lesTuilesImagees, lesEntitesImagees, lesPersonnagesImages;

        lesTuiles = jeu.getJeu().getGestionnaireDeRessources().getLesTuiles();
        lesTuilesImagees = gestionnaireDeRessources.getLesTuilesImagees();

        lesEntites = niveauCourant.getLesEntites();
        lesEntitesImagees = gestionnaireDeRessources.getLesEnnemisImages();

        lesPersonnagesImages = gestionnaireDeRessources.getLesPersonnagesImages();
        lesEntitesGraphiques.add(new EntiteFX(joueur, lesPersonnagesImages));

        for (int i = 0; i < lesTuiles.size(); i++) {
            lesTuilesGraphiques.add(new TuileFX(lesTuiles.get(i), lesTuilesImagees.get(i)));
        }

        for (int i = 0; i < lesEntites.size(); i++) {
            lesEntitesGraphiques.add(new EntiteFX(lesEntites.get(i), lesEntitesImagees));
        }

        camera = new CameraCarteTuilesFX(niveauCourant.getCarte(), DIMENSION_CAMERA_PAR_DEFAUT, lesTuilesGraphiques);
        camera.centrerSurEntite(joueur);

        largeurCamera = (int) camera.getZoneVisuelle().getLargeur();
        hauteurCamera = (int) camera.getZoneVisuelle().getHauteur();

        largeurTuile = (int) niveauCourant.getCarte().getDimensionTuiles().getLargeur();
        hauteurTuile = (int) niveauCourant.getCarte().getDimensionTuiles().getHauteur();

        affichage();
    }
}
