package com.jeremyantoine.speedjumper.fenetres;

import com.jeremyantoine.speedjumper.donnees.GestionnaireDeRessourcesFX;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.PersonnageJouable;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouchesFX;
import com.jeremyantoine.speedjumper.jeu.EtatDeJeu;
import com.jeremyantoine.speedjumper.jeu.EtatDeJeuJoue;
import com.jeremyantoine.speedjumper.jeu.Jeu;
import com.jeremyantoine.speedjumper.jeu.JeuFX;
import com.jeremyantoine.speedjumper.monde.CameraCarteTuiles;
import com.jeremyantoine.speedjumper.monde.Niveau;
import com.jeremyantoine.speedjumper.monde.Tuile;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;

public class FenetreJeu {
    private Stage stage;
    @FXML
    private TilePane grille;

    private Jeu jeu;
    private EtatDeJeu etatCourant;
    private Niveau niveauCourant;
    private HashMap<Tuile, Image> lestuilesImagees;
    private List<Entite> lesEntites;
    private CameraCarteTuiles camera;
    private PersonnageJouable joueur;

    private GestionnaireDeRessourcesFX gestionnaireDeRessources;


    public FenetreJeu() {
        gestionnaireDeRessources = new GestionnaireDeRessourcesFX();
        lestuilesImagees = new HashMap<>();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        initialisation();
    }

    private void initialisation() {
        RecuperateurDeTouches recuperateur = new RecuperateurDeTouchesFX(getClass().getResource("/touches.txt").getPath(),
                stage.getScene());
        jeu = new JeuFX(recuperateur);
        etatCourant = jeu.getManagerEtats().getEtatCourant();
        try {
            gestionnaireDeRessources.charge();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Tuile> lestuiles;
        List<Image> lesimages;

        if (etatCourant instanceof EtatDeJeuJoue etatCourant) {
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
        else {
            throw new IllegalStateException("Le jeu doit normalement se trouver en état de jeu au démarrage de la fenêtre");
        }



        System.out.println("coucou");
    }

    private void initialisationGrille() {
        grille.setHgap(8);
        grille.setPrefColumns(4);
        grille.setPrefRows(10);
        miseAJourGrille();
    }

    private void miseAJourGrille() {
        for (int i = 0; i < camera.getZoneVisuelle().getLargeur(); i++) {
            System.out.println(lestuilesImagees.get(camera.getTuile(i, 0)));
            grille.getChildren().add(new ImageView(lestuilesImagees.get(camera.getTuile(i, 0))));
        }
    }
}
