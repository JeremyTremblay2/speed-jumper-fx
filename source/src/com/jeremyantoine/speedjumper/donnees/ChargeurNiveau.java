package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.monde.Niveau;
import com.jeremyantoine.speedjumper.monde.Tuile;

import java.util.List;

public interface ChargeurNiveau {

    List<Tuile> charge(String chemin);

}
