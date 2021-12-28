package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.donnees.ChargeurDeCalqueTiledCSV;
import com.jeremyantoine.speedjumper.utilitaire.InvalidFormatException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

public class FenetrePrincipale extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL vueMenu = getClass().getResource("/menuPrincipal.fxml");
        //URL vueMenu = getClass().getResource("/menuJouer.fxml");
        if (vueMenu == null) {
            throw new IOException("Le fichier de la vue du menu principal n a pas été trouvé.");
        }

        Parent parent = FXMLLoader.load(vueMenu);
        Scene scene = new Scene(parent);
        scene.getStylesheets().getClass().getResource("../styles/styleMenuPrincipal.css");
        stage.setScene(scene);
        stage.show();
        Jeu jeu = new JeuFX();
        jeu.initialise();
        jeu.joue();
        jeu.ferme();
    }

    public static void main(String[] args) {
        //launch(args);
        ChargeurDeCalqueTiledCSV chargeurDeCarteTiled = new ChargeurDeCalqueTiledCSV();
        try {
            chargeurDeCarteTiled.charge(new URL("file:D:\\Cours\\2021-2022\\S1\\Conception et Prog Avancée\\speed-jumper\\source\\ressources\\cartes\\carteTest.csv"),
                    ",");
        } catch (ParseException | MalformedURLException | FileNotFoundException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
