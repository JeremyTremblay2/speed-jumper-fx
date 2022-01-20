package com.jeremyantoine.speedjumper.actions;

import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.monde.ArrierePlan;

/**
 * classe permettant d'animer le fond  et de le faire bouger
 */
public class DeplaceurArrierePlan {
    public void deplace(ArrierePlan arrierePlan, float temps, Direction direction) {
        float vitesse = arrierePlan.getVitesseDefilement();
        float deplacement = vitesse * (temps / 1000000000);
        Position2D nouvellePosition = switch (direction) {
            case DROITE -> new Position2D(arrierePlan.getPosition().getX() + deplacement,
                    arrierePlan.getPosition().getY());
            case GAUCHE -> new Position2D(arrierePlan.getPosition().getX() - deplacement,
                    arrierePlan.getPosition().getY());
            case HAUT -> new Position2D(arrierePlan.getPosition().getX(),
                    arrierePlan.getPosition().getY() - deplacement);
            case BAS -> new Position2D(arrierePlan.getPosition().getX() + deplacement,
                    arrierePlan.getPosition().getY() + deplacement);
        };
        arrierePlan.setPosition(nouvellePosition);
    }
}
