package com.jeremyantoine.speedjumper.entrees;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraire pour gerer la recuperation des touches pressées
 */
public abstract class RecuperateurDeTouches {
    protected List<Touche> lesTouchesPressees = new ArrayList<>();

    public abstract List<Touche> detecte();

    public List<Touche> getLesTouchesPressees() {
        return lesTouchesPressees;
    }
}