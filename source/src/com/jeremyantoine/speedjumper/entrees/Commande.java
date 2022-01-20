package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.entites.Entite;

/**
 * Classe permettant d'executer une commande sur une entite
 */
public interface Commande {
    void execute(Entite entite, float temps);
}
