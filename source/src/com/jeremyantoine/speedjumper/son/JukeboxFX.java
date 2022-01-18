package com.jeremyantoine.speedjumper.son;

import com.jeremyantoine.speedjumper.jeu.Options;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class JukeboxFX extends Jukebox {
    private MediaPlayer jukebox;

    public JukeboxFX(Options options) throws IllegalArgumentException {
        super(options);
    }

    @Override
    public void joue(String audio) throws IllegalArgumentException {
        Media musique = new Media(audio);
        jukebox = new MediaPlayer(musique);
        jukebox.play();
    }

    @Override
    public void stop() {
        jukebox.stop();
    }

    @Override
    public void pause() {
        jukebox.pause();
    }

    @Override
    public void rendreMuet() {
        jukebox.setMute(true);
    }

    @Override
    public void retablirSon() {
        jukebox.setMute(false);
    }
}
