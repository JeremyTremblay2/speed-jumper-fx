package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.entites.Entite;

public class CommandeNulle implements Commande {

    @Override
    public void execute(Entite entite, float temps) {
        //Ne fait rien
    }
}
