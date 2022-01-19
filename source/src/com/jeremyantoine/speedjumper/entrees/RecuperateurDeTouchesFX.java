package com.jeremyantoine.speedjumper.entrees;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;
import java.util.Map;

    public class RecuperateurDeTouchesFX extends RecuperateurDeTouches {
    private final Map<KeyCode, Touche> dicoTouches;
    private Scene sceneCourante;

    public RecuperateurDeTouchesFX(String fichier, Scene scene) {
        sceneCourante = scene;
        ChargeurDeTouchesFX chargeur = new ChargeurDeTouchesFX();
        dicoTouches = chargeur.recupereTouches(fichier);
        if (scene != null) {
            detection();
        }
    }

    public Map<KeyCode, Touche> getDicoTouches() {
        return dicoTouches;
    }

    public Scene getSceneCourante() {
        return sceneCourante;
    }

    public void setSceneCourante(Scene sceneCourante) {
        this.sceneCourante = sceneCourante;
        detection();
    }

    public void ajouteToucheFX(KeyCode toucheFX, Touche touche) {
        dicoTouches.put(toucheFX, touche);
    }

    @Override
    public List<Touche> detecte() {
        return super.getLesTouchesPressees();
    }

    private Touche compareEntree(KeyCode entree) {
        return dicoTouches.get(entree);
    }

    public void detection() {
        sceneCourante.addEventHandler(KeyEvent.KEY_PRESSED, (cle) -> {
            KeyCode touche = cle.getCode();
            if (dicoTouches.containsKey(touche) && !lesTouchesPressees.contains(dicoTouches.get(touche))){
                lesTouchesPressees.add(dicoTouches.get(touche));
                System.out.println(lesTouchesPressees);
            }
        });

        sceneCourante.addEventHandler(KeyEvent.KEY_RELEASED, (cle) -> {
            KeyCode touche = cle.getCode();
            lesTouchesPressees.remove(dicoTouches.get(touche));
            System.out.println(lesTouchesPressees);

        });
    }
}
