package com.jeremyantoine.speedjumper.controleurs;


import com.jeremyantoine.speedjumper.donnees.NomFenetre;
import com.jeremyantoine.speedjumper.jeu.Jeu;
import com.jeremyantoine.speedjumper.utilitaire.Navigateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class MenuJouer {
    private static final int NOMBRE_NIVEAU_PAR_PAGE = 3;
    private Navigateur navigateur;
    private Jeu jeu;
    private int nombreNiveaux;

    @FXML
    private HBox emplacementBoutons;
    @FXML
    private Button boutonPageSuivante;
    @FXML
    private GridPane grille;

    public MenuJouer(Navigateur navigateur, Jeu jeu) throws IllegalArgumentException {
        if (navigateur == null || jeu == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ou le jeu ne peuvent pas être null.");
        }
        this.navigateur = navigateur;
        this.jeu = jeu;
        nombreNiveaux = jeu.getJeu().getLesNiveaux().size();
    }

    public void initialize() {
        Button bouton;
        for (int i = 0; i < nombreNiveaux && i <= NOMBRE_NIVEAU_PAR_PAGE; i++) {
            bouton = new Button("Niveau " + i + 1);
            bouton.getStyleClass().add("niveau");
            bouton.setAlignment(Pos.CENTER);
            grille.add(bouton, i % NOMBRE_NIVEAU_PAR_PAGE, i / NOMBRE_NIVEAU_PAR_PAGE + 1);
            emplacementBoutons.getChildren().add(bouton);
        }
    }

    @FXML
    public void retourMenu(ActionEvent event) {
        navigateur.faireDemiTour();
    }

    @FXML
    public void ouvertureMenuDebutNiveau(ActionEvent event) {
        MenuDebutNiveau fenetre = new MenuDebutNiveau(navigateur, jeu);
        navigateur.naviguerVers(NomFenetre.MENU_DEBUT_NIVEAU, fenetre);
    }
}
