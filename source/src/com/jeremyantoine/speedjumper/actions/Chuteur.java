package com.jeremyantoine.speedjumper.actions;

import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.jeu.BoucleDeJeu;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.monde.Carte2D;

/**
 * Classe permettant de  faire l'action de chuter
 */
public class Chuteur implements Simulation, Runnable {
    private static final float DELTA = 1f / BoucleDeJeu.FPS_CIBLE;
    private static final float HAUTEUR_MAXIMALE_CHUTE = 200;
    private static final float HAUTEUR_CHUTE = 100;
    private static final float DUREE_CHUTE = 5.44f;
    private Carte2D carteCourante;
    private CollisionneurCarte collisionneur;
    private Entite entite;

    /**
     * Constructeur de la classe
     * @param carteCourante carte courrante
     * @throws IllegalArgumentException
     */
    public Chuteur(Carte2D carteCourante) throws IllegalArgumentException {
        if (carteCourante == null) {
            throw new IllegalArgumentException("Impossible d'instancier un chuteur car la carte passée en paramètre "
                    + "est nulle.");
        }
        this.carteCourante = carteCourante;
        collisionneur = new CollisionneurCarte();
    }

    /**
     * Methode mettant a jour l'etat de jeu
     * @param entite entite chuttant
     * @param temps temps
     */
    @Override
    public void miseAJourEtatDeJeu(Entite entite, double temps) {
        this.entite = entite;
    }

    /**
     * Methode lançant l'action
     */
    @Override
    public void run() {
        if (!entite.isChute() || entite.isSurSol()) {
            return;
        }

        float gravite = (2 * HAUTEUR_MAXIMALE_CHUTE) / (DUREE_CHUTE * DUREE_CHUTE);
        float velocite = (float)Math.sqrt(2 * gravite * HAUTEUR_CHUTE);
        float position = 0, positionPrecedente = 0;
        Rectangle collisionFuture;

        while (velocite > 0 || !entite.isSurSol()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            velocite += gravite * DELTA;
            position += velocite * DELTA;

            collisionFuture = new Rectangle(entite.getPosition().getX() + entite.getCollision().getPosition().getX(),
                    entite.getPosition().getY() + entite.getCollision().getPosition().getY() + 2 * (position - positionPrecedente),
                    entite.getCollision().getDimension());

            if (collisionneur.collisionne(collisionFuture, carteCourante)) {
                entite.setChute(false);
                entite.setSurSol(true);
                return;
            }
            else {
                entite.setPosition(new Position2D(entite.getPosition().getX(), entite.getPosition().getY()
                        + (position - positionPrecedente)));
            }
            positionPrecedente = position;
        }
    }
}
