package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.monde.Tuile;
import com.jeremyantoine.speedjumper.utilitaire.InvalidFormatException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

public class ChargeurDeJeuxDeTuilesTextuel implements ChargeurDeJeuxDeTuiles {
    private static final String DELIMITEUR = " ";
    private List<Tuile> lesTuiles;

    public ChargeurDeJeuxDeTuilesTextuel() {
        lesTuiles = new ArrayList<>();
    }

    @Override
    public List<Tuile> charge(String chemin) {
        int nombreTuile = 0, indexTuile;

        try (BufferedReader lecteur = new BufferedReader(new FileReader(chemin))) {
            String ligne;
            ligne = lecteur.readLine();
            String[] uneTuile = ligne.split(DELIMITEUR);
            Dimension dimension = new Dimension(Float.parseFloat(uneTuile[0].trim()),
                    Float.parseFloat(uneTuile[1].trim()));

            while ((ligne = lecteur.readLine()) != null) {
                uneTuile = ligne.split(DELIMITEUR);
                indexTuile = Integer.parseInt(uneTuile[0].trim());

                while (nombreTuile < indexTuile) {
                    lesTuiles.add(new Tuile(null, dimension));
                    nombreTuile++;
                }

                lesTuiles.add(new Tuile(
                        new Rectangle(Float.parseFloat(uneTuile[1].trim()),
                                Float.parseFloat(uneTuile[2].trim()),
                                Float.parseFloat(uneTuile[3].trim()),
                                Float.parseFloat(uneTuile[4].trim())),
                                dimension));
                nombreTuile++;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return lesTuiles;
    }
}
