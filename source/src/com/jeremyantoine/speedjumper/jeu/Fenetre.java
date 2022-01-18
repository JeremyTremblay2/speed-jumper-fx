package com.jeremyantoine.speedjumper.jeu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Fenetre extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL vueMenu = getClass().getResource("/menuPrincipal.fxml");
        if (vueMenu == null) {
            throw new IOException("Le fichier de la vue du menu principal n a pas été trouvé.");
        }



        Parent parent = FXMLLoader.load(vueMenu);
        Scene scene = new Scene(parent);
        scene.getStylesheets().getClass().getResource("../styles/styleMenuPrincipal.css");
        stage.setScene(scene);
        stage.show();
        //new Jeu();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
