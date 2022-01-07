package com.jeremyantoine.speedjumper.comportement;

import com.jeremyantoine.speedjumper.actions.AdaptateurDeplaceur;
import com.jeremyantoine.speedjumper.actions.CollisionneurCarte;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.Vivant;
import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.monde.Niveau;

public class ComportementMarche implements Comportement {
    private AdaptateurDeplaceur deplaceur;
    private Niveau niveau;
    private Position2D dernierePosition = null;

    public ComportementMarche(Niveau niveau) {
        if (niveau == null) {
            throw new IllegalArgumentException("Le niveau est necessaire pour le comportement de l'ennemi et ne peut "
                    + "pas être null.");
        }
        this.niveau = niveau;
        deplaceur = new AdaptateurDeplaceur(Direction.GAUCHE, niveau.getCarte());
    }

    @Override
    public void agit(Entite entite, double temps) {
        if (!(entite instanceof Vivant vivant)) {
            return;
        }

        if (vivant.getPosition() == dernierePosition) {
            inverseDirection(vivant);
        }

        deplaceur.miseAJourEtatDeJeu(vivant, temps);
        dernierePosition = entite.getPosition();
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
