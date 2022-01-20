package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.observateurs.Sujet;
import javafx.application.Platform;

/**
 * Classe de la boucle du jeu
 */
public class BoucleDeJeu extends Sujet implements Runnable {
    private static final long SECONDE_EN_NANOSECONDE = 1000000000;
    public static final long FPS_CIBLE = 60;
    public static final long NOMBRE_NANOSECONDES_AVANT_NOTIFICATION =  SECONDE_EN_NANOSECONDE / FPS_CIBLE;
    private boolean enCours;
    private long tempsEcoule;

    /**
     * Constructeur de la boucle et passe en cours en true
     */
    public BoucleDeJeu() {
        enCours = true;
    }

    /**
     * set la boucle de jeu comme étant en cour
     * @param enCours
     */
    public void setEnCours(boolean enCours) {
        this.enCours = enCours;
    }


    /**
     * retourne le temps écoulé depuis le lancement de la boucle
     * @return
     */
    public long getTempsEcoule() {
        return tempsEcoule;
    }

    /**
     * Methode pour lancer la boucle de jeu
     */
    @Override
    public void run() {
        long tempsCourant, ecoule;
        long tempsDerniereIteration = System.nanoTime();
        while (enCours) {
            tempsCourant = System.nanoTime();
            ecoule = tempsCourant - tempsDerniereIteration;
            if (ecoule >= NOMBRE_NANOSECONDES_AVANT_NOTIFICATION) {
                tempsEcoule = ecoule;
                Platform.runLater(this::notifier);
                tempsDerniereIteration = tempsCourant;
            }
        }
    }
}
