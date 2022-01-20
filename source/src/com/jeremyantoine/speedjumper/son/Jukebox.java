package com.jeremyantoine.speedjumper.son;

import com.jeremyantoine.speedjumper.jeu.Options;


/**
 * Classe abstraite pour jouer un son
 */
public abstract class Jukebox {
    private Options options;

    public Jukebox(Options options) throws IllegalArgumentException {
        if (options == null) {
            throw new IllegalArgumentException("Les options ne peuvent pas être nulle pour jouer du son");
        }
        this.options = options;
    }

    /**
     * Joue un audio
     * @param audio chemin de l'audio a jouer
     */
    public abstract void joue(String audio);

    /**
     * Stop l'audio
     */
    public abstract void stop();

    /**
     * Met en pause l'audio
     */
    public abstract void pause();


    /**
     * Coupe le son de l'audio
     */
    public abstract void rendreMuet();

    /**
     * Remet le son
     */
    public abstract void retablirSon();
}
