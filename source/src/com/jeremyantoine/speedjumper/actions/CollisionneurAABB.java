package com.jeremyantoine.speedjumper.actions;

import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;

public class CollisionneurAABB {

    public static boolean collisionne(Rectangle collision1, Rectangle collision2) {
        double gauche, droite, haut, bas;
        if (collision1 == null || collision2 == null) {
            return false;
        }
        Position2D position1 = collision1.getPosition();
        Position2D position2 = collision2.getPosition();

        bas = Math.min(position1.getY() + collision1.getDimension().getHauteur(),
                position2.getY() + collision2.getDimension().getHauteur());
        haut = Math.max(position1.getY(), position2.getY());

        droite = Math.min(position1.getX() + collision1.getDimension().getLargeur(),
                position2.getX() + collision2.getDimension().getLargeur());
        gauche = Math.max(position1.getX(), position2.getX());

        return (haut < bas) && (gauche < droite);
    }

    /*
    private void verificationCollision(Rectangle collision) throws IllegalArgumentException {
        if (collision == null) {
            throw new IllegalArgumentException("Lr rectangle de collision donnée en paramètre est null.");
        }
    }
    */
}
