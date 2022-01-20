package com.jeremyantoine.speedjumper.launcheur;

import com.jeremyantoine.speedjumper.controleurs.MenuPrincipal;
import com.jeremyantoine.speedjumper.controleurs.NomFenetre;
import com.jeremyantoine.speedjumper.utilitaire.Navigateur;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Classe appele au lancement du jeu
 */
public class Launcher extends Application {
    /**
     * Methode start initialisant le navigateur ainsi que la premiere vue
     * @param stage le stage ou afficher la vue
     */
    @Override
    public void start(Stage stage) {
        Navigateur navigateur = new Navigateur(stage);
        MenuPrincipal menu = new MenuPrincipal(navigateur);
        navigateur.naviguerVers(NomFenetre.MENU_PRINCIPAL, menu);
        stage.show();
    }

    /**
     * methode maib appelé au lancement
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
