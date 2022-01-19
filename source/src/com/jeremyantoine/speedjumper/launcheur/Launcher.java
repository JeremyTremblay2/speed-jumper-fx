package com.jeremyantoine.speedjumper.launcheur;

import com.jeremyantoine.speedjumper.controleurs.MenuPrincipal;
import com.jeremyantoine.speedjumper.controleurs.NomFenetre;
import com.jeremyantoine.speedjumper.utilitaire.Navigateur;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) {
        Navigateur navigateur = new Navigateur(stage);
        MenuPrincipal menu = new MenuPrincipal(navigateur);
        navigateur.naviguerVers(NomFenetre.MENU_PRINCIPAL, menu);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
