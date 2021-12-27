package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.monde.Carte;
import com.jeremyantoine.speedjumper.utilitaire.InvalidFormatException;

import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;

public interface ChargeurDeCarteTiled {
    Carte charge(URL cheminFichier) throws FileNotFoundException, ParseException, InvalidFormatException;
}
