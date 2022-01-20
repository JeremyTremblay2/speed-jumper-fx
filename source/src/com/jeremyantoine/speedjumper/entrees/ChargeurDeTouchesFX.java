package com.jeremyantoine.speedjumper.entrees;

import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Classes permettant de gerer le chargement des touches dans une liste
 */
public class ChargeurDeTouchesFX extends ChargeurDeTouches {
    public Map<KeyCode, Touche> recupereTouches(String fichier) throws IllegalArgumentException {
        Map<String, String> lesTouches = super.charge(fichier);
        Map<KeyCode, Touche> lesTouchesFX = new HashMap<>();

        String valeurToucheFX;
        String valeurTouche;

        for (Map.Entry<String, String> ensemble : lesTouches.entrySet()) {
            String toucheFX = ensemble.getKey();
            String touche = ensemble.getValue();
            valeurToucheFX = Enum.valueOf(KeyCode.class, toucheFX).toString();
            valeurTouche = Enum.valueOf(Touche.class, touche).toString();
            lesTouchesFX.put(KeyCode.valueOf(valeurToucheFX), Touche.valueOf(valeurTouche));
        }

        return lesTouchesFX;
    }
}
