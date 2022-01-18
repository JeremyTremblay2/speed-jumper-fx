package com.jeremyantoine.speedjumper.controleurs;

import com.jeremyantoine.speedjumper.jeu.Jeu;
import com.jeremyantoine.speedjumper.utilitaire.Navigateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuOptions {
    private Navigateur navigateur;

    public MenuOptions(Navigateur navigateur, Jeu jeu) throws IllegalArgumentException {
        if (navigateur == null || jeu == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ou le jeu ne peuvent pas être null.");
        }
        this.navigateur = navigateur;
    }

    @FXML
    public void retourMenu(ActionEvent event) {
        navigateur.faireDemiTour();
    }
}
