package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.monde.Tuile;

import java.util.List;

/**
 * interface permettant de charger un niveau
 */
public interface ChargeurNiveau {
    List<Tuile> charge(String chemin);
}
