package com.jeremyantoine.speedjumper.Jeu;

import java.util.ArrayList;
import java.util.List;

public abstract class RecuperateurDeTouches {
    private List<Touche> lesEntrees;

    public RecuperateurDeTouches() {
        lesEntrees = new ArrayList<>();
    }

    public List<Touche> getLesEntrees() {
        return lesEntrees;
    }

    public abstract List<Touche> detecte();
}