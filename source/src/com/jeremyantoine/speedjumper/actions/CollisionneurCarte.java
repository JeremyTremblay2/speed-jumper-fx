package com.jeremyantoine.speedjumper.actions;

import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.monde.Carte2D;

public class CollisionneurCarte {
    private static final CollisionneurAABB collisionneur = new CollisionneurAABB();

    public static boolean collisionne(Rectangle collision, Carte2D carte) {
        if (collision == null || carte == null || carte.getLesTuiles() == null) {
            return false;
        }

        int coinGauche = (int) (collision.getPosition().getX() / carte.getDimensionTuiles().getLargeur());;
        int coinSuperieur = (int) (collision.getPosition().getY() / carte.getDimensionTuiles().getHauteur());
        int coinDroite = (int) ((collision.getPosition().getX() + collision.getDimension().getLargeur() - 1)
                / carte.getDimensionTuiles().getLargeur());
        int coinInferieur = (int) ((collision.getPosition().getY() + collision.getDimension().getLargeur() - 1)
                / carte.getDimensionTuiles().getHauteur());

        Rectangle collisionTuile;

        for(int x = coinGauche; x <= coinDroite; x++) {
            for(int y = coinSuperieur; y <= coinInferieur; y++) {
                collisionTuile = carte.getTuile(x, y).getCollision();
                if (collisionTuile != null && collisionneur.collisionne(collision, collisionTuile))
                    return true;
            }
        }
        return false;
    }
}
