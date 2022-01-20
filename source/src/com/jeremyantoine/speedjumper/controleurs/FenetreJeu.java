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

/**
 * Controleur pour la fenetre de jeu
 */
public class FenetreJeu implements Observateur {
    private static final Dimension DIMENSION_CAMERA_PAR_DEFAUT = new Dimension(30,22);
    private static final float ECHELLE_CARTE = 2;

    private final Navigateur navigateur;
    private final Jeu jeu;

    private final Scene scene;
    private final GraphicsContext contexteGraphique;

    private final Niveau niveauCourant;
    private final List<TuileFX> lesTuilesGraphiques;
    private final List<EntiteFX> lesEntitesGraphiques;
    private final CameraCarteTuilesFX camera;
    private final PersonnageJouable joueur;

    private final GestionnaireDeRessourcesFX gestionnaireDeRessources;

    private final Dimension tailleCaneva;
    private final int largeurCamera;
    private final int hauteurCamera;
    private final int largeurTuile;
    private final int hauteurTuile;

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
                    entite.getEntite().getDimension().getLargeur() / ECHELLE_CARTE,
                    entite.getEntite().getDimension().getHauteur() / ECHELLE_CARTE);
        }
    }

    /**
     * Methode qui met a jour l'affichage
     */
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

        //Initialiase la détection des touches, le système de notification, et démarre le jeu
        ((RecuperateurDeTouchesFX) jeu.getManagerEtats().getEtatCourant()
                .getGestionnaireActions().getRecuperateurDeTouches()).setSceneCourante(scene);
        jeu.getManagerEtats().setEtatCourant(EtatJeu.ETAT_JEU_JOUE);
        jeu.getManagerEtats().getEtatCourant().attacher(this);

        affichage();
    }
}
