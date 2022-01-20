package com.jeremyantoine.speedjumper.controleurs;

import com.jeremyantoine.speedjumper.jeu.Jeu;
import com.jeremyantoine.speedjumper.utilitaire.Navigateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controleur de la vue option
 */
public class MenuOptions {
    private Navigateur navigateur;

    /**
     * controleur de la vue
     * @param navigateur
     * @param jeu
     * @throws IllegalArgumentException
     */
    public MenuOptions(Navigateur navigateur, Jeu jeu) throws IllegalArgumentException {
        if (navigateur == null || jeu == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ou le jeu ne peuvent pas être null.");
        }
        this.navigateur = navigateur;
    }

    /**
     * methode pour revenir a la page d'avant
     * @param event
     */
    @FXML
    public void retourMenu(ActionEvent event) {
        navigateur.faireDemiTour();
    }
}
