package com.jeremyantoine.speedjumper.Jeu;

import java.util.ArrayList;
import java.util.List;

public abstract class RecuperateurDeTouches {
    private List<Touche> lesTouchesPressees = new ArrayList<>();

    public abstract List<Touche> detecte();

    public void ajouteTouche(Touche touche) {
        lesTouchesPressees.add(touche);
    }

    public void retireTouche(Touche touche) {
        lesTouchesPressees.remove(touche);
    }

    public List<Touche> getLesTouchesPressees() {
        return lesTouchesPressees;
    }
}