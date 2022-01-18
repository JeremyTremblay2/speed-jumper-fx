package com.jeremyantoine.speedjumper.jeu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPrincipal extends Sujet {
    @FXML
    private Button boutonFermeture;

    @FXML
    private Button boutonJouer;

    @FXML
    private Button boutonOptions;

    public void fermetureFenetre(ActionEvent actionEvent) {
        Stage stage = (Stage) boutonFermeture.getScene().getWindow();
        stage.close();
    }

    public void ouvrirMenuJeu(ActionEvent event) throws IOException {

    }

    public void ouvrirOptions(ActionEvent event) throws  IOException {

    }
}
