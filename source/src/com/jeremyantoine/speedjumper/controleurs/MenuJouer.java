package com.jeremyantoine.speedjumper.controleurs;


import com.jeremyantoine.speedjumper.jeu.EtatJeu;
import com.jeremyantoine.speedjumper.jeu.Jeu;
import com.jeremyantoine.speedjumper.utilitaire.Navigateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controleur du menu de jeu
 */
public class MenuJouer {
    private static final Pattern NOMBRE_ENTIER = Pattern.compile("[^0-9]+([0-9]+)$");
    private static final int NOMBRE_NIVEAU_PAR_PAGE = 3;
    private static final int NOMBRE_NIVEAU_PAR_LIGNE = 2;
    private Navigateur navigateur;
    private Jeu jeu;
    private int nombreNiveaux;
    private int pageCourante = 0;

    @FXML
    private Button boutonPageSuivante;
    @FXML
    private VBox conteneur;
    @FXML
    private Button boutonPagePrecedente;

    /**
     * constructeur de la vie
     * @param navigateur
     * @param jeu
     * @throws IllegalArgumentException
     */
    public MenuJouer(Navigateur navigateur, Jeu jeu) throws IllegalArgumentException {
        if (navigateur == null || jeu == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ou le jeu ne peuvent pas être null.");
        }
        this.navigateur = navigateur;
        this.jeu = jeu;
        nombreNiveaux = jeu.getJeu().getLesNiveaux().size();
    }

    /**
     * initialisation de la vue
     */
    public void initialize() {
        conteneur.getChildren().clear();
        List<HBox> emplacementBoutons = new ArrayList<>();
        List<Button> lesBoutons = new ArrayList<>();
        Button bouton;
        HBox hbox = null;

        for (int i = 0; (i + pageCourante * NOMBRE_NIVEAU_PAR_PAGE) < nombreNiveaux && i < NOMBRE_NIVEAU_PAR_PAGE; i++) {
            bouton = new Button("Niveau " + (i + 1 + pageCourante * NOMBRE_NIVEAU_PAR_PAGE));

            bouton.getStyleClass().add("niveau");
            bouton.setAlignment(Pos.CENTER);
            bouton.setOnAction(this::ouvertureMenuDebutNiveau);

            if (i % NOMBRE_NIVEAU_PAR_LIGNE == 0) {
                hbox = new HBox();
                hbox.setAlignment(Pos.BASELINE_CENTER);
                hbox.setSpacing(50);
                emplacementBoutons.add(hbox);
            }
            hbox.getChildren().add(bouton);
        }
        conteneur.getChildren().addAll(emplacementBoutons);
        cacherBoutons();
    }

    /**
     * methode permettant de revenir a la page d'avant
     * @param event
     */
    @FXML
    public void retourMenu(ActionEvent event) {
        navigateur.faireDemiTour();
    }

    /**
     * Ouverture du menu avec la liste des niveaux
     * @param event
     */
    @FXML
    public void ouvertureMenuDebutNiveau(ActionEvent event) {
        Button bouton = (Button) event.getSource();
        String chaine = bouton.getText();
        Matcher matcher = NOMBRE_ENTIER.matcher(chaine);
        if (matcher.find()) {
            String nombre = matcher.group(1);
            int numeroNiveau = Integer.parseInt(nombre);
            if (jeu.getJeu().getLesNiveaux().size() >= numeroNiveau) {
                jeu.getJeu().setNiveauCourant(numeroNiveau - 1);
                jeu.getManagerEtats().getLesEtats().get(EtatJeu.ETAT_JEU_JOUE).raffraichirNiveauCourant();
                MenuDebutNiveau fenetre = new MenuDebutNiveau(navigateur, jeu);
                navigateur.naviguerVers(NomFenetre.MENU_DEBUT_NIVEAU, fenetre);
            }
        }
    }

    /**
     * methode pour naviguer a la page suivant
     * @param event
     */
    @FXML
    public void pageSuivante(ActionEvent event) {
        pageCourante++;
        initialize();
    }

    /**
     * metohode pour aller a la page suivante
     * @param event
     */
    @FXML
    public void pagePrecedente(ActionEvent event) {
        if (pageCourante > 0) {
            pageCourante--;
            initialize();
        }
    }

    /**
     * Methode permettant de cacher des boutons si pas necessaire
     */
    private void cacherBoutons() {
        if ((NOMBRE_NIVEAU_PAR_PAGE + NOMBRE_NIVEAU_PAR_PAGE * pageCourante) < nombreNiveaux) {
            boutonPageSuivante.setVisible(true);
        }
        else {
            boutonPageSuivante.setVisible(false);
        }

        if (pageCourante == 0) {
            boutonPagePrecedente.setVisible(false);
        }
        else {
            boutonPagePrecedente.setVisible(true);
        }
    }
}
