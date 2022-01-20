package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.monde.Carte2D;
import com.jeremyantoine.speedjumper.monde.Tuile;
import com.jeremyantoine.speedjumper.utilitaire.InvalidFormatException;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

/**
 * interface permettant de charger les tuiles depuis un fichier CSV
 */
public interface ChargeurDeCarteTiled {
    Carte2D charge(String cheminFichier, List<Tuile> lesTuiles) throws FileNotFoundException, ParseException, InvalidFormatException;
}
