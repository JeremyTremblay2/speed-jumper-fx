package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Score;

import java.util.List;

/**
 * interface pour charger les scores
 */
public interface ChargeurScore {
    List<List<Score>> charge(String chemin);
}
