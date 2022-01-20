package com.jeremyantoine.speedjumper.comportement;

import com.jeremyantoine.speedjumper.entites.Entite;

/**
 * classe permettant de gerer le comportement null
 */
public class ComportementNull implements Comportement {
    @Override
    public void agit(Entite entite, double temps) {
        // Ne fait rien.
    }
}
