package com.jeremyantoine.speedjumper.comportement;

import com.jeremyantoine.speedjumper.entites.Entite;

/**
 * Interface pour les comportements d'entite
 */
public interface Comportement {
    void agit(Entite entite, double temps);
}
