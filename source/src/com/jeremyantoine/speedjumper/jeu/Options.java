package com.jeremyantoine.speedjumper.jeu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Options {

    @FXML
    private Button boutonHome;

    public void retourAccueil(ActionEvent event)throws IOException{
        Stage stage = null;

        URL vueJouer = getClass().getResource("/menuPrincipal.fxml");

        if (vueJouer == null) {
            throw new IOException("Le fichier de la vue  n a pas été trouvé.");
        }


        Parent parent = FXMLLoader.load(vueJouer);
        Scene scene = new Scene(parent);
        stage = (Stage) boutonHome.getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }
}
