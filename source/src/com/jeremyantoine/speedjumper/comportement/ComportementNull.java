package com.jeremyantoine.speedjumper.comportement;

import com.jeremyantoine.speedjumper.entites.Entite;

public class ComportementNull implements Comportement {
    @Override
    public void agit(Entite entite, double temps) {
        // Ne fait rien.
    }
}
