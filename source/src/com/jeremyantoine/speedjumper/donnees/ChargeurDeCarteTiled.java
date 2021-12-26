package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.monde.Carte;

import java.net.URL;

public interface ChargeurDeCarteTiled {
    Carte charge(URL cheminFichier);
}
