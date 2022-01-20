package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.monde.Niveau;

import java.io.IOException;
import java.util.List;

/**
 * Interface permettant de sauvegarder les scores
 */
public interface SauvegardeDeScore {

    void sauvegarde(List<Niveau> lesniveaux) throws IOException;
}
