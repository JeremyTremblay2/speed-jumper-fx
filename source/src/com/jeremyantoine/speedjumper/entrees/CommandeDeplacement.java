package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.actions.AdaptateurDeplaceur;
import com.jeremyantoine.speedjumper.actions.CollisionneurCarte;
import com.jeremyantoine.speedjumper.actions.Deplaceur;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.monde.Niveau;

public class CommandeDeplacement implements Commande {
    private AdaptateurDeplaceur deplaceur;
    private CollisionneurCarte collisionneur;
    private Direction direction;
    private Niveau niveau;

    public CommandeDeplacement(Direction direction, Niveau niveau) {
        this.direction = direction;
        this.niveau = niveau;
        deplaceur = new AdaptateurDeplaceur(Direction.DROITE);
        collisionneur = new CollisionneurCarte();
    }

    @Override
    public void execute(Entite entite, float temps) {
        Position2D positionFuture = null;

        if (direction == Direction.DROITE) {
            positionFuture = new Position2D(entite.getPosition().getX() + entite.getVelocite() * (temps / 1000000000),
                    entite.getPosition().getY());
            deplaceur.setDirection(Direction.DROITE);
        }
        if (direction == Direction.GAUCHE) {
            positionFuture = new Position2D(entite.getPosition().getX() - entite.getVelocite() * (temps / 1000000000),
                    entite.getPosition().getY());
            deplaceur.setDirection(Direction.GAUCHE);
        }

        Rectangle collisionJoueur = new Rectangle(entite.getCollision().getPosition().getX() + positionFuture.getX(),
                entite.getCollision().getPosition().getY() + positionFuture.getY(),
                entite.getCollision().getDimension().getLargeur(),
                entite.getCollision().getDimension().getHauteur());

        if (!collisionneur.collisionne(collisionJoueur, niveau.getCarte())) {
            deplaceur.miseAJourEtatDeJeu(entite, temps);
        }
    }
}
