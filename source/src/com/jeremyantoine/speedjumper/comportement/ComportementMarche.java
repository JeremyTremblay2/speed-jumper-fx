package com.jeremyantoine.speedjumper.comportement;

import com.jeremyantoine.speedjumper.actions.AdaptateurDeplaceur;
import com.jeremyantoine.speedjumper.actions.CollisionneurCarte;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.Vivant;
import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.monde.Niveau;

public class ComportementMarche implements Comportement {
    private Niveau niveau;
    private static final AdaptateurDeplaceur deplaceur = new AdaptateurDeplaceur(Direction.DROITE);

    public ComportementMarche(Niveau niveau) {
        this.niveau = niveau;
    }

    @Override
    public void agit(Entite entite, double temps) {
        if (!(entite instanceof Vivant vivant)) {
            return;
        }

        deplaceur.miseAJourEtatDeJeu(entite, temps);

        Rectangle collisionEntite = new Rectangle(entite.getCollision().getPosition().getX() + entite.getPosition().getX() + 3,
                entite.getCollision().getPosition().getY() + entite.getPosition().getY(),
                entite.getCollision().getDimension().getLargeur(),
                entite.getCollision().getDimension().getHauteur());



        if (!CollisionneurCarte.collisionne(entite.getCollision(), niveau.getCarte())) {
            deplaceur.miseAJourEtatDeJeu(entite, temps);
        }
        else {
            inverseDirection(vivant);
        }
    }

    private void inverseDirection(Vivant vivant) {
        if (vivant.getDirection() == Direction.DROITE) {
            vivant.setDirection(Direction.GAUCHE);
        }
        else {
            vivant.setDirection(Direction.DROITE);
        }
    }
}
