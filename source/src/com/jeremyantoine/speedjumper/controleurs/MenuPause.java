package com.jeremyantoine.speedjumper.controleurs;

import com.jeremyantoine.speedjumper.jeu.Jeu;
import com.jeremyantoine.speedjumper.utilitaire.Navigateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuPause {
    private Navigateur navigateur;

    public MenuPause(Navigateur navigateur, Jeu jeu) throws IllegalArgumentException {
        if (navigateur == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ne peut pas être null.");
        }
        this.navigateur = navigateur;
    }

    @FXML
    public void retourMenu(ActionEvent event) {
        navigateur.faireDemiTour();
        navigateur.faireDemiTour();
    }

    @FXML
    public void reprendreJeu(ActionEvent event) {
        navigateur.faireDemiTour();
    }
}
