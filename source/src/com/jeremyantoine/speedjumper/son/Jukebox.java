package com.jeremyantoine.speedjumper.son;

import com.jeremyantoine.speedjumper.jeu.Options;

public abstract class Jukebox {
    private Options options;

    public Jukebox(Options options) throws IllegalArgumentException {
        if (options == null) {
            throw new IllegalArgumentException("Les options ne peuvent pas être nulle pour jouer du son");
        }
        this.options = options;
    }

    public abstract void joue(String audio);

    public abstract void stop();

    public abstract void pause();

    public abstract void rendreMuet();

    public abstract void retablirSon();
}
