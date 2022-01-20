package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.monde.Niveau;

import java.io.IOException;
import java.util.List;

public interface SauveurScore {
    void sauvegarde(List<Niveau> lesNiveaux, String chemin);
}
