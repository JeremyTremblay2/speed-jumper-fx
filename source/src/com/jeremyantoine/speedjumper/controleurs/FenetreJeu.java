package com.jeremyantoine.speedjumper.controleurs;

import com.jeremyantoine.speedjumper.donnees.CollectionRessources;
import com.jeremyantoine.speedjumper.donnees.GestionnaireDeRessourcesFX;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.PersonnageJouable;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouchesFX;
import com.jeremyantoine.speedjumper.jeu.*;
import com.jeremyantoine.speedjumper.monde.CameraCarteTuiles;
import com.jeremyantoine.speedjumper.monde.Niveau;
import com.jeremyantoine.speedjumper.monde.Tuile;
import com.jeremyantoine.speedjumper.observateurs.Observateur;
import com.jeremyantoine.speedjumper.utilitaire.Navigateur;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.List;

public class FenetreJeu implements Observateur {
    private Navigateur navigateur;

    private Scene scene;
    private StackPane lesCouches;
    private GridPane grille;
    private ImageView[][] lesTuilesDuPane;
    private ImageView imageJoueur;

    private Jeu jeu;
    private EtatDeJeu etatCourant;
    private Niveau niveauCourant;
    private HashMap<Tuile, Image> lestuilesImagees;
    private List<Entite> lesEntites;
    private CameraCarteTuiles camera;
    private PersonnageJouable joueur;

    private CollectionRessources ressources;
    private GestionnaireDeRessourcesFX gestionnaireDeRessources;

    private int largeurCamera;
    private int hauteurCamera;


    public FenetreJeu(Navigateur navigateur, Jeu jeu) {
        if (navigateur == null || jeu == null) {
            throw new IllegalArgumentException("Le navigateur ou le jeu passé en paramètre ne peut pas être null.");
        }
        this.navigateur = navigateur;
        this.jeu = jeu;

        ressources = CollectionRessources.getInstance();
        gestionnaireDeRessources = new GestionnaireDeRessourcesFX();
        lestuilesImagees = new HashMap<>();
        grille = new GridPane();
        lesCouches = new StackPane();
        scene = new Scene(lesCouches);
        lesCouches.getChildren().add(grille);
        initialisation();
    }

    public Scene getScene() {
        return scene;
    }

    private void initialisation() {
        etatCourant = jeu.getManagerEtats().getEtatCourant();
        try {
            gestionnaireDeRessources.charge();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Tuile> lestuiles;
        List<Image> lesimages;

        imageJoueur = new ImageView(new Image(ressources.getLesEntites().get(0)));
        lesCouches.getChildren().add(imageJoueur);

        if (!(etatCourant instanceof EtatDeJeuJoue etatCourant)) {
            throw new IllegalStateException("Le jeu doit normalement se trouver en état de jeu au démarrage de la fenêtre");
        }

        etatCourant.attacher(this);
        niveauCourant = etatCourant.getNiveauCourant();
        camera = etatCourant.getCamera();
        lesEntites = niveauCourant.getLesEntites();
        joueur = etatCourant.getJoueur();
        lestuiles = etatCourant.getGestionnaireDeRessources().getLesTuiles();
        lesimages = gestionnaireDeRessources.getLesTuilesImagees();

        for (int i = 0; i < lestuiles.size(); i++) {
            lestuilesImagees.put(lestuiles.get(i), lesimages.get(i));
        }
        initialisationGrille();
    }

    private void initialisationGrille() {
        largeurCamera = (int) camera.getZoneVisuelle().getLargeur();
        hauteurCamera = (int) camera.getZoneVisuelle().getHauteur();
        lesTuilesDuPane = new ImageView[hauteurCamera][largeurCamera];

        //System.out.println(scene);

        for (int x = 0; x < hauteurCamera; x++) {
            for (int y = 0; y < largeurCamera; y++) {
                ImageView image = new ImageView();
                image.setImage(lestuilesImagees.get(camera.getTuile(x, y)));
                lesTuilesDuPane[x][y] = image;
                grille.add(lesTuilesDuPane[x][y], y, x);
            }
        }
    }

    @Override
    public void miseAjour() {

    }
}