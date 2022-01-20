package com.jeremyantoine.speedjumper.comportement;

import com.jeremyantoine.speedjumper.actions.AdaptateurDeplaceur;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.Vivant;
import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.monde.Niveau;

/**
 * classe gerant le comportement marche
 */
public class ComportementMarche implements Comportement {
    private AdaptateurDeplaceur deplaceur;
    private Position2D dernierePosition = null;

    /**
     * constructeur de la classe
     * @param niveau niveau ou s'effectue le comportement
     */
    public ComportementMarche(Niveau niveau) {
        if (niveau == null) {
            throw new IllegalArgumentException("Le niveau est necessaire pour le comportement de l'ennemi et ne peut "
                    + "pas être null.");
        }
        deplaceur = new AdaptateurDeplaceur(Direction.GAUCHE, niveau.getCarte());
    }

    /**
     * effectue automatiquement des deplacements
     * @param entite
     * @param temps
     */
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

    /**
     * inverse la direction du comportement
     * @param vivant
     */
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
