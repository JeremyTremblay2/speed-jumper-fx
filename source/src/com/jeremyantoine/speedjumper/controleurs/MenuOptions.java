package com.jeremyantoine.speedjumper.controleurs;

import com.jeremyantoine.speedjumper.utilitaire.Navigateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuOptions {
    private Navigateur navigateur;

    public MenuOptions(Navigateur navigateur) throws IllegalArgumentException {
        if (navigateur == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ne peut aps être null.");
        }
        this.navigateur = navigateur;
    }

    @FXML
    public void retourMenu(ActionEvent event) {
        navigateur.faireDemiTour();
    }
}
