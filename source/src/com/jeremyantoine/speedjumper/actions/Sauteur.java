package com.jeremyantoine.speedjumper.actions;

import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.jeu.BoucleDeJeu;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.monde.Carte2D;


public class Sauteur implements Simulation, Runnable {
    private static final float DELTA = 1f / BoucleDeJeu.FPS_CIBLE;
    private static final float HAUTEUR_MAXIMALE_SAUT = 600;
    private static final float HAUTEUR_SAUT = 500;
    private static final float DUREE_SAUT = 0.44f;
    private CollisionneurCarte collisionneur;
    private Carte2D carteCourante;
    private double temps;
    private Entite entite;

    public Sauteur(Carte2D carteCourante) throws IllegalArgumentException {
        if (carteCourante == null) {
            throw new IllegalArgumentException("La carte passée en paramètre ne peut pas être null.");
        }
        this.carteCourante = carteCourante;
        collisionneur = new CollisionneurCarte();
    }

    @Override
    public void miseAJourEtatDeJeu(Entite entite, double temps) {
        this.entite = entite;
        this.temps = temps;
    }

    @Override
    public void run() {
        if (entite.isChute() || !entite.isSurSol()) {
            return;
        }

        entite.setChute(false);
        entite.setSurSol(false);

        float gravite = (2 * HAUTEUR_MAXIMALE_SAUT) / (DUREE_SAUT * DUREE_SAUT);
        float velociteSaut = (float)Math.sqrt(2 * gravite * HAUTEUR_SAUT);

        float velocite = -velociteSaut;
        float position = 0, positionPrecedente = 0;

        Rectangle collisionFuture;

        while (velocite < 0 || entite.isChute()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            velocite += gravite * DELTA;
            position += velocite * DELTA;

            collisionFuture = new Rectangle(entite.getPosition().getX() + entite.getCollision().getPosition().getX(),
                    entite.getPosition().getY() + entite.getCollision().getPosition().getY() + (position - positionPrecedente),
                    entite.getCollision().getDimension());

            if (collisionneur.collisionne(collisionFuture, carteCourante)) {
                entite.setChute(true);
                return;
            }
            else {
                entite.setPosition(new Position2D(entite.getPosition().getX(), entite.getPosition().getY()
                        + (position - positionPrecedente)));
            }
            positionPrecedente = position;
        }
        entite.setChute(true);
    }
}
