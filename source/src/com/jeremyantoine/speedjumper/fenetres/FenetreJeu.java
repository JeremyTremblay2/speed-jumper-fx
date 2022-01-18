package com.jeremyantoine.speedjumper.fenetres;

import com.jeremyantoine.speedjumper.donnees.CollectionRessources;
import com.jeremyantoine.speedjumper.donnees.GestionnaireDeRessourcesFX;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.PersonnageJouable;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouchesFX;
import com.jeremyantoine.speedjumper.jeu.EtatDeJeu;
import com.jeremyantoine.speedjumper.jeu.EtatDeJeuJoue;
import com.jeremyantoine.speedjumper.jeu.Jeu;
import com.jeremyantoine.speedjumper.jeu.JeuFX;
import com.jeremyantoine.speedjumper.monde.CameraCarteTuiles;
import com.jeremyantoine.speedjumper.monde.Niveau;
import com.jeremyantoine.speedjumper.monde.Tuile;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;

public class FenetreJeu {


    private final GridPane grille;

    private final HashMap<Tuile, Image> lestuilesImagees;

    private CameraCarteTuiles camera;
    private PersonnageJouable joueur;

    private final GestionnaireDeRessourcesFX gestionnaireDeRessources;


    public FenetreJeu(Stage stage) {
        gestionnaireDeRessources = new GestionnaireDeRessourcesFX();
        lestuilesImagees = new HashMap<>();
        grille = new GridPane();
        FenetrePrincipale.getStage().setScene(new Scene(grille));
        initialisation();
    }

    private void initialisation() {


        RecuperateurDeTouchesFX recuperateur = new RecuperateurDeTouchesFX(CollectionRessources.getRecuperateurDeTouches(),
                FenetrePrincipale.getStage().getScene());

        recuperateur.detection();

        Jeu jeu = new JeuFX(recuperateur);



        EtatDeJeu etatCourant1 = jeu.getManagerEtats().getEtatCourant();
        try {
            gestionnaireDeRessources.charge();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Tuile> lestuiles;
        List<Image> lesimages;

        if (etatCourant1 instanceof EtatDeJeuJoue etatCourant) {
            Niveau niveauCourant = etatCourant.getNiveauCourant();
            camera = etatCourant.getCamera();
            List<Entite> lesEntites = niveauCourant.getLesEntites();
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


        initialiserPerso();
    }

    private void initialisationGrille() {
        int largeurCamera = (int) camera.getZoneVisuelle().getLargeur();
        int hauteurCamera = (int) camera.getZoneVisuelle().getHauteur();
        ImageView[][] lesTuilesDuPane = new ImageView[hauteurCamera][largeurCamera];

        //System.out.println(camera);

        for (int x = 0; x < hauteurCamera; x++) {
            for (int y = 0; y < largeurCamera; y++) {
                ImageView image = new ImageView();
                image.setImage(lestuilesImagees.get(camera.getTuile(x, y)));
                //System.out.print(camera.getTuile(x, y).getIdTuile() + " ");
                lesTuilesDuPane[x][y] = image;
                grille.add(lesTuilesDuPane[x][y], y, x);
            }
        }
    }

    private void miseAJourGrille() {

    }

    private void initialiserPerso(){
        ImageView perso = new ImageView();

        //recuperer image de base
        //if(choix==homme)
        perso.setImage(gestionnaireDeRessources.getLeJoueursFImages().get(0));

        grille.add(perso, (int) joueur.getPosition().getX(), (int) joueur.getPosition().getY());

    }
}
