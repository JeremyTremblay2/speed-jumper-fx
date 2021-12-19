package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.entites.Entite;

public interface Commande {
    void execute(Entite entite, float temps);
}
