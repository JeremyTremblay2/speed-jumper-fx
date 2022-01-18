package com.jeremyantoine.speedjumper.Jeu;

import com.jeremyantoine.speedjumper.jeu.Sujet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

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
