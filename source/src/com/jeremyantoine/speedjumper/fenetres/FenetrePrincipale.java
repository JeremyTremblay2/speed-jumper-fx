package com.jeremyantoine.speedjumper.fenetres;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class FenetrePrincipale extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FenetrePrincipale.stage = stage;

        FenetreJeu controller = new FenetreJeu(stage);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static  Stage getStage() {
        return stage;
    }

}

