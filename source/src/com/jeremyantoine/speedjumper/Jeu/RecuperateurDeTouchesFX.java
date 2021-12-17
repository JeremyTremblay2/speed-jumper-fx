package com.jeremyantoine.speedjumper.Jeu;

import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecuperateurDeTouchesFX extends RecuperateurDeTouches {

    private Map<KeyCode, Touche> lesTouches;

    public RecuperateurDeTouchesFX() {
        super();
        lesTouches = new HashMap<>();
    }

    public void ajouteTouche(KeyCode toucheFX, Touche touche) {
        lesTouches.put(toucheFX, touche);
    }

    @Override
    public List<Touche> detecte() {
        return null;
    }

    private Touche compareEntree(KeyCode entree) {
        return lesTouches.get(entree);
    }
}
