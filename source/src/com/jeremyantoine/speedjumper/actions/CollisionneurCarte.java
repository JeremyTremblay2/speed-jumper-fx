package com.jeremyantoine.speedjumper.actions;

import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.monde.Carte2D;

public class CollisionneurCarte {
    public boolean collisionne(Rectangle collision, Carte2D carte) {
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

        double largeurCarte = carte.getDimension().getLargeur();
        double hauteurCarte = carte.getDimension().getHauteur();

        for(int x = coinSuperieur; x <= coinInferieur && x < largeurCarte; x++) {
            for(int y = coinGauche; y <= coinDroite && y < hauteurCarte; y++) {
                collisionTuile = carte.getTuile(x, y).getCollision();
                if (collisionTuile != null && CollisionneurAABB.collisionne(collision, collisionTuile))
                    return true;
            }
        }
        return false;
    }
}
