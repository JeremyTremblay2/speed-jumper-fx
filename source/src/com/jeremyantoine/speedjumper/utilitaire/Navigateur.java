package com.jeremyantoine.speedjumper.utilitaire;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class Navigateur {
    private Stage stage;
    private Scene sceneCourante;
    private Stack<Scene> laPileDeScenes;

    public Navigateur(Stage stage) throws IllegalArgumentException {
        if (stage == null) {
            throw new IllegalArgumentException("Le stage donné en paramètre ne peut pas être null.");
        }
        this.stage = stage;
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

    private void miseAJourScene() {
        sceneCourante = laPileDeScenes.peek();
        stage.setScene(sceneCourante);
    }
}
