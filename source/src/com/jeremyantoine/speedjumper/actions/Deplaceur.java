package com.jeremyantoine.speedjumper.actions;

import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.entites.Entite;

public class Deplaceur {

    public void deplace(Entite entite, Position2D position) throws IllegalArgumentException {
        if (entite == null) {
            throw new IllegalArgumentException("L'entité fournie en paramètre du déplaceur est nulle.");
        }

        if (position == null) {
            throw new IllegalArgumentException("La position fournie en paramètre du déplaceur est nulle.");
        }
        entite.setPosition(position);
    }
}
