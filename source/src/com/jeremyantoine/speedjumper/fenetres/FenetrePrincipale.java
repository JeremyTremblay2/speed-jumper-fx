package com.jeremyantoine.speedjumper.fenetres;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FenetrePrincipale extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/jeu.fxml"));
        Parent parent = loader.load();
        FenetreJeu controller = loader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(parent);
        scene.getStylesheets().getClass().getResource("../styles/styleMenuPrincipal.css");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
