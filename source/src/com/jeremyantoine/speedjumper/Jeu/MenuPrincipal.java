package com.jeremyantoine.speedjumper.Jeu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MenuPrincipal {
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
        Stage stage = null;

        URL vueJouer = getClass().getResource("/UCboutonMenuJeu.fxml");

        if (vueJouer == null) {
            throw new IOException("Le fichier de la vue  n a pas été trouvé.");
        }


        Parent parent = FXMLLoader.load(vueJouer);
        Scene scene = new Scene(parent);
        stage = (Stage) boutonJouer.getScene().getWindow();

        stage.setScene(scene);
        stage.show();


    }

    public void ouvrirOptions(ActionEvent event) throws  IOException {
        Stage stage = null;

        URL vueJouer = getClass().getResource("/option.fxml");

        if (vueJouer == null) {
            throw new IOException("Le fichier de la vue  n a pas été trouvé.");
        }


        Parent parent = FXMLLoader.load(vueJouer);
        Scene scene = new Scene(parent);
        stage = (Stage) boutonOptions.getScene().getWindow();

        stage.setScene(scene);
        stage.show();



    }
}
