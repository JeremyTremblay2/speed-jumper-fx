package com.jeremyantoine.speedjumper.son;

import com.jeremyantoine.speedjumper.jeu.Options;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Classe pour lire une musique
 */
public class JukeboxFX extends Jukebox {
    private MediaPlayer jukebox;

    /**
     * Constructeur du JukeboxFX
     * @param options les options ( volumes )
     * @throws IllegalArgumentException
     */
    public JukeboxFX(Options options) throws IllegalArgumentException {
        super(options);
    }

    /**
     * lance l'audio
     * @param audio chemin de l'audio
     * @throws IllegalArgumentException
     */
    @Override
    public void joue(String audio) throws IllegalArgumentException {
        Media musique = new Media(audio);
        jukebox = new MediaPlayer(musique);
        jukebox.play();
    }

    /**
     * Stop la musique en cour
     */
    @Override
    public void stop() {
        jukebox.stop();
    }

    /**
     * Met en pause la musique
     */
    @Override
    public void pause() {
        jukebox.pause();
    }

    /**
     * Mute l'audio
     */
    @Override
    public void rendreMuet() {
        jukebox.setMute(true);
    }

    /**
     * Remet le son
     */
    @Override
    public void retablirSon() {
        jukebox.setMute(false);
    }
}
