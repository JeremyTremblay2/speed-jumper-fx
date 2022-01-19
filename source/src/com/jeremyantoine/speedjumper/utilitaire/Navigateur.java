package com.jeremyantoine.speedjumper.utilitaire;

import com.jeremyantoine.speedjumper.donnees.CollectionRessources;
import com.jeremyantoine.speedjumper.donnees.NomFenetre;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Stack;

public class Navigateur {
    private Stage stage;
    private Scene sceneCourante;
    private Stack<Scene> laPileDeScenes;
    private Map<NomFenetre, URL> vues;

    public Navigateur(Stage stage) throws IllegalArgumentException {
        if (stage == null) {
            throw new IllegalArgumentException("Le stage donné en paramètre ne peut pas être null.");
        }
        this.stage = stage;
        CollectionRessources ressources = CollectionRessources.getInstance();
        vues = ressources.getLesCheminsDesVues();
        laPileDeScenes = new Stack<>();
    }

    public void naviguerVers(NomFenetre fenetre, Object controleur) {
        Scene scene = null;
        FXMLLoader chargeur = new FXMLLoader();
        if (!vues.containsKey(fenetre)) {
            throw new IllegalArgumentException("La fenetre spécifiée ne possède pas de fichier FXML");
        }

        chargeur.setController(controleur);
        chargeur.setLocation(vues.get(fenetre));

        try {
            Parent racine = chargeur.load();
            scene = new Scene(racine);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        naviguerVers(scene);
    }

    public void naviguerVers(NomFenetre fenetre) throws IllegalArgumentException {
        Scene scene = null;
        if (!vues.containsKey(fenetre)) {
            throw new IllegalArgumentException("La fenetre spécifiée ne possède pas de fichier FXML");
        }

        try {
            Parent racine = FXMLLoader.load(vues.get(fenetre));
            scene = new Scene(racine);
        } catch (IOException e) {
            e.printStackTrace();
        }

        naviguerVers(scene);
    }

    public void naviguerVers(Scene scene) {
        if (scene != null) {
            laPileDeScenes.add(scene);
            miseAJourScene();
        }
    }

    public void faireDemiTour() {
        if (laPileDeScenes.size() != 0) {
            laPileDeScenes.pop();
            miseAJourScene();
        }
    }

    public Scene getSceneCourante() {
        return sceneCourante;
    }

    public Stage getStage() {
        return stage;
    }

    private void miseAJourScene() {
        sceneCourante = laPileDeScenes.peek();
        stage.setScene(sceneCourante);
    }
}
