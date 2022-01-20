package com.jeremyantoine.speedjumper.actions;

import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.monde.Carte2D;

public class Deplaceur {
    private CollisionneurCarte collisionneur;
    private Carte2D carteCourante;

    public Deplaceur(Carte2D carte) {
        carteCourante = carte;
        collisionneur = new CollisionneurCarte();
    }

    public void deplace(Entite entite, double temps, Direction direction) throws IllegalArgumentException {
        if (entite == null) {
            throw new IllegalArgumentException("L'entité fournie en paramètre du déplaceur est nulle.");
        }
        if (direction == null) {
            throw new IllegalArgumentException("La direction fournie en paramètre du déplaceur est nulle.");
        }

        Position2D nouvellePosition = entite.getPosition();
        double increment = entite.getVelocite() * (temps / 1000000000);

        switch (direction) {
            case DROITE -> nouvellePosition = new Position2D(entite.getPosition().getX() + increment,
                    entite.getPosition().getY());
            case GAUCHE -> nouvellePosition = new Position2D(entite.getPosition().getX() - increment,
                    entite.getPosition().getY());
            case HAUT -> nouvellePosition = new Position2D(entite.getPosition().getX(),
                    entite.getPosition().getY() - increment);
            case BAS -> nouvellePosition = new Position2D(entite.getPosition().getX(),
                    entite.getPosition().getY() + increment);
        }

        Rectangle nouvelleCollision = new Rectangle(nouvellePosition.getX()
                + entite.getCollision().getPosition().getX(),
                nouvellePosition.getY() + entite.getCollision().getPosition().getY(),
                entite.getCollision().getDimension());

        if (nouvellePosition.getX() >= 0 && nouvellePosition.getY() >= 0
                && nouvellePosition.getX() <= carteCourante.getDimension().getLargeur() * carteCourante.getDimensionTuiles().getLargeur()
                && nouvellePosition.getY() <= carteCourante.getDimension().getHauteur() * carteCourante.getDimensionTuiles().getHauteur()
                && !collisionneur.collisionne(nouvelleCollision, carteCourante)) {
            entite.setPosition(nouvellePosition);
        }
    }
}
