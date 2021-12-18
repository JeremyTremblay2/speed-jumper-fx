package com.jeremyantoine.speedjumper.Jeu;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class RecuperateurDeTouchesFX extends RecuperateurDeTouches implements EventHandler {
    private Map<KeyCode, Touche> dicoTouches;
    private Scene sceneCourante;
    private ChargeurDeTouchesFX chargeur = new ChargeurDeTouchesFX();

    public RecuperateurDeTouchesFX(URL fichier) {
        dicoTouches = chargeur.recupereTouches(fichier);
    }

    public Map<KeyCode, Touche> getDicoTouches() {
        return dicoTouches;
    }

    public Scene getSceneCourante() {
        return sceneCourante;
    }

    private void setSceneCourante(Scene sceneCourante) {
        this.sceneCourante = sceneCourante;
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

    @Override
    public void handle(Event event) {
        sceneCourante.addEventHandler(KeyEvent.KEY_PRESSED, (cle) -> {
            KeyCode touche = cle.getCode();
            if (dicoTouches.containsKey(touche)) {
                super.ajouteTouche(dicoTouches.get(touche));
            }
        });

        sceneCourante.addEventHandler(KeyEvent.KEY_RELEASED, (cle) -> {
            KeyCode touche = cle.getCode();
            if (dicoTouches.containsKey(touche)) {
                super.retireTouche(dicoTouches.get(touche));
            }
        });
    }
}
