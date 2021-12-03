package com.jeremyantoine.speedjumper.Jeu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuPrincipal {
    @FXML
    private Button boutonFermeture;

    public void fermetureFenetre(ActionEvent actionEvent) {
        Stage stage = (Stage) boutonFermeture.getScene().getWindow();
        stage.close();
    }
}
