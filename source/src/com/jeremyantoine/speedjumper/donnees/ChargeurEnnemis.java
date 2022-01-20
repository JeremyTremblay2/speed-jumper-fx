package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.entites.Entite;

import java.util.List;

/**
 * interface pour charger les enemis
 */
public interface ChargeurEnnemis {
    List<List<Entite>> charge(String chemin);
}
