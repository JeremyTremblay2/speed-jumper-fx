package com.jeremyantoine.speedjumper.actions;

import com.jeremyantoine.speedjumper.coordonnees.Position;
import com.jeremyantoine.speedjumper.entites.Entite;

public class Deplaceur {

    public void deplace(Entite entite, Position position) throws IllegalArgumentException {
        if (entite == null || position == null) {
            throw new IllegalArgumentException("Un argument fournit au paramètre du déplaceur est null.");
        }
        entite.setPosition(position);
    }
}
