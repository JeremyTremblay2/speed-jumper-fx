package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.actions.Deplaceur;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Direction;

public class CommandeDeplacement implements Commande {
    private static Deplaceur deplaceur = new Deplaceur();
    private Direction direction;

    public CommandeDeplacement(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void execute(Entite entite, float temps) {

    }
}
