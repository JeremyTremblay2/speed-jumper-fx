package com.jeremyantoine.speedjumper.comportement;

import com.jeremyantoine.speedjumper.actions.AdaptateurDeplaceur;
import com.jeremyantoine.speedjumper.actions.CollisionneurCarte;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.Vivant;
import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.monde.Niveau;

public class ComportementMarche implements Comportement {
    private AdaptateurDeplaceur deplaceur = new AdaptateurDeplaceur(Direction.GAUCHE);
    private CollisionneurCarte collisionneur = new CollisionneurCarte();
    private Niveau niveau;

    public ComportementMarche(Niveau niveau) {
        this.niveau = niveau;
    }

    @Override
    public void agit(Entite entite, double temps) {
        if (!(entite instanceof Vivant vivant)) {
            return;
        }

        double decalage = entite.getVelocite() * (temps / 1000000000);

        if (vivant.getDirection() == Direction.GAUCHE) {
            Rectangle collisionEntite = new Rectangle(entite.getCollision().getPosition().getX()
                    + entite.getPosition().getX() - decalage,
                    entite.getCollision().getPosition().getY() + entite.getPosition().getY(),
                    entite.getCollision().getDimension().getLargeur(),
                    entite.getCollision().getDimension().getHauteur());
        }
        else {
            Rectangle collisionEntite = new Rectangle(entite.getCollision().getPosition().getX()
                    + entite.getPosition().getX() + decalage,
                    entite.getCollision().getPosition().getY() + entite.getPosition().getY(),
                    entite.getCollision().getDimension().getLargeur(),
                    entite.getCollision().getDimension().getHauteur());
        }

        if (!collisionneur.collisionne(entite.getCollision(), niveau.getCarte())) {
            deplaceur.miseAJourEtatDeJeu(entite, temps);
        }
        else {
            inverseDirection(vivant);
        }
    }

    private void inverseDirection(Vivant vivant) {
        if (vivant.getDirection() == Direction.DROITE) {
            vivant.setDirection(Direction.GAUCHE);
            deplaceur.setDirection(Direction.GAUCHE);
        }
        else {
            vivant.setDirection(Direction.DROITE);
            deplaceur.setDirection(Direction.DROITE);
        }
    }
}
