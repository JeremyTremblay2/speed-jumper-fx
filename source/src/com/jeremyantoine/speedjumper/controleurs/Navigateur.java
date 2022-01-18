package com.jeremyantoine.speedjumper.controleurs;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class Navigateur {
    private HashMap<Fenetre, Pane> lesFenetres = new HashMap<>();
    private Scene scene;

    public Navigateur(Scene scene) {
        this.scene = scene;
    }

    protected void addScreen(Fenetre nom, Pane pane){
        lesFenetres.put(nom, pane);
    }

    protected void removeScreen(Fenetre nom){
        lesFenetres.remove(nom);
    }

    protected void activate(Fenetre nom){
        scene.setRoot( lesFenetres.get(nom) );
    }
}
