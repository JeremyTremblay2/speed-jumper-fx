package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Score;

import java.util.List;

public interface ChargeurScore {
    List<List<Score>> charge(String chemin);
}
