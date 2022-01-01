package com.jeremyantoine.speedjumper.actions;

import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.entites.Entite;

public class Deplaceur {
    public void deplace(Entite entite, double temps, Direction direction) throws IllegalArgumentException {
        if (entite == null) {
            throw new IllegalArgumentException("L'entité fournie en paramètre du déplaceur est nulle.");
        }
        if (direction == null) {
            throw new IllegalArgumentException("La position fournie en paramètre du déplaceur est nulle.");
        }

        double nouvellePositionX = entite.getPosition().getX();

        switch (direction) {
            case DROITE -> nouvellePositionX = entite.getPosition().getX() +
                    entite.getVelocite() * (temps / 1000000000);
            case GAUCHE -> nouvellePositionX = entite.getPosition().getX() -
                    entite.getVelocite() * (temps / 1000000000);
        }
        entite.setPosition(new Position2D(nouvellePositionX, entite.getPosition().getY()));
    }
}
