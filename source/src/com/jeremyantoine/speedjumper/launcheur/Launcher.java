package com.jeremyantoine.speedjumper.launcheur;

import com.jeremyantoine.speedjumper.controleurs.MenuPrincipal;
import com.jeremyantoine.speedjumper.donnees.NomFenetre;
import com.jeremyantoine.speedjumper.utilitaire.Navigateur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Navigateur navigateur = new Navigateur(stage);
        MenuPrincipal menu = new MenuPrincipal(navigateur);
        navigateur.naviguerVers(NomFenetre.MENU_PRINCIPAL, menu);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
