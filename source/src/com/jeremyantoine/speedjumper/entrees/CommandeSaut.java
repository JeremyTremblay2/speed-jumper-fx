package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.monde.Niveau;

public class CommandeSaut implements Commande {
    private Niveau niveau;

    public CommandeSaut(Niveau niveau) {
        this.niveau = niveau;
    }

    @Override
    public void execute(Entite entite, float temps) {

    }
}
