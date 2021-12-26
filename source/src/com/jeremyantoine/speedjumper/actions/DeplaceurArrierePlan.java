package com.jeremyantoine.speedjumper.actions;

import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.monde.ArrierePlan;

public class DeplaceurArrierePlan {
    public void deplace(ArrierePlan arrierePlan, float temps, Direction direction) {
        float vitesse = arrierePlan.getVitesseDefilement();
        float deplacement = vitesse * temps;
        Position2D nouvellePosition;
        switch(direction) {
            case DROITE:
                nouvellePosition = new Position2D(arrierePlan.getPosition().getX() + deplacement,
                        arrierePlan.getPosition().getY());
                break;
            case GAUCHE:
                nouvellePosition = new Position2D(arrierePlan.getPosition().getX() - deplacement,
                        arrierePlan.getPosition().getY());
                break;
            case HAUT:
                nouvellePosition = new Position2D(arrierePlan.getPosition().getX(),
                        arrierePlan.getPosition().getY() - deplacement);
                break;
            case BAS:
                nouvellePosition = new Position2D(arrierePlan.getPosition().getX() + deplacement,
                        arrierePlan.getPosition().getY() + deplacement);
                break;
            default:
                nouvellePosition = arrierePlan.getPosition();
                break;
        }
        arrierePlan.setPosition(nouvellePosition);
    }
}
