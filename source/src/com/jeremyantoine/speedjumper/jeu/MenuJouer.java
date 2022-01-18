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

public class MenuJouer {


    @FXML
    private Button boutonAcceuil;

    public void ouvertureAccueil(ActionEvent event)throws IOException {



        //A modifier car peut etre pas ube window . Creer classe gerer changement de scene
        Stage stage = null;

        URL vueJouer = getClass().getResource("/menuPrincipal.fxml");

        if (vueJouer == null) {
            throw new IOException("Le fichier de la vue  n a pas été trouvé.");
        }





        Parent parent = FXMLLoader.load(vueJouer);
        Scene scene = new Scene(parent);
        stage = (Stage) boutonAcceuil.getScene().getWindow();

        stage.setScene(scene);
    }


}
