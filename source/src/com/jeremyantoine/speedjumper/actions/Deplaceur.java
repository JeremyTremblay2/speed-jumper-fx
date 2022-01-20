package com.jeremyantoine.speedjumper.actions;

import com.jeremyantoine.speedjumper.jeu.BoucleDeJeu;
import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.monde.Carte2D;

/**
 * Classe permettant de gerer l'action deplacement
 */
public class Deplaceur {
    private static final float VELOCITE_DANS_LES_AIRS = 1.4f;
    private static final double NOMBRE_PIXEL_VERIFICATION_VIDE = 4;
    private CollisionneurCarte collisionneur;
    private Carte2D carteCourante;
    private final double decalagePixelParMouvement;

    /**
     * Constructeur de la clase
     * @param carte carte ou a lieu l'action
     * @throws IllegalArgumentException
     */
    public Deplaceur(Carte2D carte) throws IllegalArgumentException {
        carteCourante = carte;
        collisionneur = new CollisionneurCarte();
        decalagePixelParMouvement = carte.getDimensionTuiles().getLargeur() * 0.1;
    }

    /**
     * methode permettant le deplacement d'une entite dans une certaine direction
     * @param entite entite faisant l'action
     * @param temps
     * @param direction direction dans laquelle deplacée l'entité
     * @throws IllegalArgumentException
     */
    public void deplace(Entite entite, double temps, Direction direction) throws IllegalArgumentException {
        if (entite == null) {
            throw new IllegalArgumentException("L'entité fournie en paramètre du déplaceur est nulle.");
        }
        if (direction == null) {
            throw new IllegalArgumentException("La direction fournie en paramètre du déplaceur est nulle.");
        }

        Position2D nouvellePosition = entite.getPosition();
        double increment;

        if (entite.isSurSol()) {
            increment = entite.getVelocite() * (temps / BoucleDeJeu.NOMBRE_NANOSECONDES_AVANT_NOTIFICATION);
        }
        else {
            increment = entite.getVelocite() * (temps / BoucleDeJeu.NOMBRE_NANOSECONDES_AVANT_NOTIFICATION) / VELOCITE_DANS_LES_AIRS;
        }

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

        Position2D nouvellePositionSuperieure = new Position2D(nouvellePosition.getX(),
                nouvellePosition.getY() + decalagePixelParMouvement);

        Rectangle nouvelleCollisionSuperieure = new Rectangle(nouvellePositionSuperieure.getX()
                + entite.getCollision().getPosition().getX(),
                nouvellePositionSuperieure.getY() + entite.getCollision().getPosition().getY(),
                entite.getCollision().getDimension());

        if (nouvellePosition.getX() >= 0 && nouvellePosition.getY() >= 0
                && nouvellePosition.getX() <= carteCourante.getDimension().getLargeur() * carteCourante.getDimensionTuiles().getLargeur()
                && nouvellePosition.getY() <= carteCourante.getDimension().getHauteur() * carteCourante.getDimensionTuiles().getHauteur()) {
            if (!collisionneur.collisionne(nouvelleCollision, carteCourante)) {
                entite.setPosition(nouvellePosition);
                detectionVide(entite);
            }
            else if (!collisionneur.collisionne(nouvelleCollisionSuperieure, carteCourante)) {
                entite.setPosition(nouvellePositionSuperieure);
                detectionVide(entite);
            }
        }
    }

    private void detectionVide(Entite entite) {
        Position2D positionBas = new Position2D(entite.getPosition().getX() + entite.getCollision().getPosition().getX(),
                entite.getPosition().getY() + entite.getCollision().getPosition().getY() + NOMBRE_PIXEL_VERIFICATION_VIDE);

        Rectangle nouvelleCollision = new Rectangle(positionBas, entite.getCollision().getDimension());

        if (!collisionneur.collisionne(nouvelleCollision, carteCourante)) {
            entite.setSurSol(false);
            entite.setChute(true);
        }
    }
}
