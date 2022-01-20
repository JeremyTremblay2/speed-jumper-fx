package com.jeremyantoine.speedjumper.controleurs;

import com.jeremyantoine.speedjumper.jeu.Jeu;
import com.jeremyantoine.speedjumper.logique.Score;
import com.jeremyantoine.speedjumper.monde.Niveau;
import com.jeremyantoine.speedjumper.utilitaire.Navigateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * Controleur pour la vue du debut niveau
 */
public class MenuDebutNiveau {
    private Navigateur navigateur;
    private Jeu jeu;

    @FXML
    private ListView<Score> laListeDeScores;

    public MenuDebutNiveau(Navigateur navigateur, Jeu jeu) throws IllegalArgumentException {
        if (jeu == null || navigateur == null) {
            throw new IllegalArgumentException("Le navigateur ou le jeu passé en paramètre ne peuvent pas être null.");
        }
        this.navigateur = navigateur;
        this.jeu = jeu;
    }

    public void initialize() {
        laListeDeScores.itemsProperty().bind(jeu.getJeu().getNiveauCourant().lesScoresProperty());

        laListeDeScores.setCellFactory(__ -> new ListCell<>(){
            @Override
            protected void updateItem(Score score, boolean present) {
                super.updateItem(score, present);
                if (!present) {
                    textProperty().bind(score.pseudoProperty().concat("     ").concat(score.scoreProperty()));
                    //setStyle("-fx-background-color : green;");
                }
                else {
                    textProperty().unbind();
                    setText("");
                }
            }
        });
    }

    /**
     * Methode pour revnir a la vue d'avant
     * @param event
     */
    @FXML
    public void retourMenu(ActionEvent event) {
        navigateur.faireDemiTour();
    }

    /**
     * methode pour le jeu
     * @param event
     */
    @FXML
    public void ouvrirJeu(ActionEvent event) {
        FenetreJeu fenetre = new FenetreJeu(navigateur, jeu);
        navigateur.naviguerVers(fenetre.getScene());
    }
}
