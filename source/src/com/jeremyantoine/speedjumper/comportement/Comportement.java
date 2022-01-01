package com.jeremyantoine.speedjumper.comportement;

import com.jeremyantoine.speedjumper.entites.Entite;

public interface Comportement {
    void agit(Entite entite, double temps);
}
