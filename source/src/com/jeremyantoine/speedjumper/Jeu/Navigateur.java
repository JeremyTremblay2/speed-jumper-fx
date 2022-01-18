package com.jeremyantoine.speedjumper.Jeu;

import com.jeremyantoine.speedjumper.jeu.Observateur;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

public class Navigateur implements Observateur {


    private Scene main;
    private Parent parent;

    public void Navig(String fxmlPath) throws IOException {

        URL vue = getClass().getResource(fxmlPath);
        if (vue == null) {
            throw new IOException("Le fichier de la vue  n a pas été trouvé.");
        }
        parent = FXMLLoader.load(vue);
    }

    @Override
    public void miseAjour() {

    }
}
