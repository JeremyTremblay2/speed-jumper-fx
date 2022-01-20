package com.jeremyantoine.speedjumper.actions;

import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.jeu.BoucleDeJeu;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.monde.Carte2D;
import com.jeremyantoine.speedjumper.monde.Niveau;

public class Chuteur implements Simulation, Runnable {
    private Carte2D carteCourante;
    private CollisionneurCarte collisionneur;
    private Entite entite;
    private double temps;

    public Chuteur(Carte2D carteCourante) throws IllegalArgumentException {
        if (carteCourante == null) {
            throw new IllegalArgumentException("Impossible d'instancier un chuteur car la carte passée en paramètre "
                    + "est nulle.");
        }
        this.carteCourante = carteCourante;
        collisionneur = new CollisionneurCarte();
    }

    @Override
    public void miseAJourEtatDeJeu(Entite entite, double temps) {
        this.entite = entite;
        this.temps = temps;
    }


        /*if (entite.isChute()) {
            tempsTotal += temps;
            double positionY = Math.sin(((tempsTotal / 60f) / BoucleDeJeu.NOMBRE_MILLISECONDES_AVANT_NOTIFICATION) * 3);
            Position2D positionFuture = new Position2D(entite.getPosition().getX(), positionY);
            Rectangle collisionFuture = new Rectangle(positionFuture.getX() + entite.getCollision().getPosition().getX(),
                    positionFuture.getY() + entite.getCollision().getPosition().getY(),
                    entite.getCollision().getDimension());
            System.out.println(positionY);

            if (!collisionneur.collisionne(collisionFuture, niveauCourant.getCarte())) {
                entite.setPosition(positionFuture);
            }
        }

        tempsTotal += temps;
        double positionY = Math.sin(((tempsTotal / 60f) / BoucleDeJeu.NOMBRE_MILLISECONDES_AVANT_NOTIFICATION) * 3);
        Position2D positionFuture = new Position2D(entite.getPosition().getX(), positionY);
        Rectangle collisionFuture = new Rectangle(positionFuture.getX() + entite.getCollision().getPosition().getX(),
                positionFuture.getY() + entite.getCollision().getPosition().getY(),
                entite.getCollision().getDimension());
        System.out.println(positionY);

        if (!collisionneur.collisionne(collisionFuture, niveauCourant.getCarte())) {
            entite.setPosition(positionFuture);
        }

        //new Position2D(entite.getPosition().getX(), entite.getPosition().getY() + entite.getVelocite() * (temps / 1000000000));*/

    @Override
    public void run() {
        if (!entite.isChute() || entite.isSurSol()) {
            return;
        }

        float gravity = (2 * 2500) / (0.44f * 0.44f);
        float jumpVelocity = (float)Math.sqrt(2 * gravity * 600);

        float velocity = jumpVelocity;
        float position = 0, positionPrecedente = 0;

        Rectangle collisionFuture;

        while (velocity > 0 || !entite.isSurSol()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            velocity += gravity * (1 / 60f);
            position += velocity * (1/60f);

            collisionFuture = new Rectangle(entite.getPosition().getX() + entite.getCollision().getPosition().getX(),
                    entite.getPosition().getY() + entite.getCollision().getPosition().getY() + (position - positionPrecedente),
                    entite.getCollision().getDimension());

            if (collisionneur.collisionne(collisionFuture, carteCourante)) {
                entite.setChute(false);
                entite.setSurSol(true);
            }
            else {
                entite.setPosition(new Position2D(entite.getPosition().getX(), entite.getPosition().getY()
                        + (position - positionPrecedente)));
            }
            positionPrecedente = position;
        }

    }
}
