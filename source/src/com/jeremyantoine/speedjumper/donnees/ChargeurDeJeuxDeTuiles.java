package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.monde.Tuile;
import com.jeremyantoine.speedjumper.utilitaire.InvalidFormatException;

import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

public interface ChargeurDeJeuxDeTuiles {
    List<Tuile> charge(String chemin)  throws FileNotFoundException, ParseException, InvalidFormatException;;
}
