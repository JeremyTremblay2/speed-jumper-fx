package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.entites.Entite;

import java.util.List;

public interface ChargeurEnnemis {
    List<List<Entite>> charge(String chemin);
}
