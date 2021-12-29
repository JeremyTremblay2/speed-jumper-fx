package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.utilitaire.InvalidFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChargeurDeCarteTiledCSV {
    public int[][] charge(URL cheminFichier, String separateur) throws FileNotFoundException,
            NumberFormatException, ParseException, InvalidFormatException {
        List<Integer> donnees = new ArrayList<>();
        String ligne;
        String[] lesElements;
        int idTuile, nombreColonnes = -1, nombreLignes = 0;

        Scanner fichier = new Scanner(new File(String.valueOf(cheminFichier).replaceAll("file:", "")));
        while (fichier.hasNextLine()) {
            ligne = fichier.nextLine().trim();
            lesElements = ligne.split(separateur);
            if (nombreColonnes == -1) {
                nombreColonnes = lesElements.length;
            }
            if (lesElements.length != nombreColonnes) {
                throw new InvalidFormatException("Le fichier comporte des données manquantes, toutes les lignes n'ont pas "
                        + " le même nombre de colonnes.");
            }

            for (String valeur : lesElements) {
                idTuile = Integer.parseInt(valeur.trim());
                donnees.add(idTuile);
            }
            nombreLignes++;
        }
        fichier.close();
        return remplissageTableau(donnees, nombreLignes, nombreColonnes);
    }

    private int[][] remplissageTableau(List<Integer> donnees, int nombreLignes, int nombreColonnes)
            throws IndexOutOfBoundsException {
        int[][] lesTuiles = new int[nombreLignes][nombreColonnes];
        for (int x = 0; x < nombreLignes; x++) {
            for (int y = 0; y < nombreColonnes; y++) {
                lesTuiles[x][y] = donnees.get(y + x * nombreLignes);
            }
        }
        return lesTuiles;
    }
}
