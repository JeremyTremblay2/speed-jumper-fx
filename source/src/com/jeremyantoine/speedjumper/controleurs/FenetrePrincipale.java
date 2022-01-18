package com.jeremyantoine.speedjumper.controleurs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FenetrePrincipale extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/jeu.fxml"));
        //Parent parent = loader.load();
        //FenetreJeu controller = loader.getController();
        //controller.setStage(stage);
        //Scene scene = new Scene(parent);
        //scene.getStylesheets().getClass().getResource("../styles/styleMenuPrincipal.css");
        //stage.setScene(scene);
        FenetreJeu fenetre = new FenetreJeu();
        Scene scene = fenetre.getScene();
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
