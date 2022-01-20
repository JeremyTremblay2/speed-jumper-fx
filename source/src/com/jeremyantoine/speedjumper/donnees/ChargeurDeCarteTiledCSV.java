package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.utilitaire.InvalidFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe permettant de charger depuis un fichier CSV des tuiles
 */
public class ChargeurDeCarteTiledCSV {
    /**
     * Methode permettant de charger les tuiles
     * @param cheminFichier
     * @param separateur
     * @return
     * @throws FileNotFoundException
     * @throws NumberFormatException
     * @throws ParseException
     * @throws InvalidFormatException
     */
    public int[][] charge(String cheminFichier, String separateur) throws FileNotFoundException,
            NumberFormatException, ParseException, InvalidFormatException {
        List<Integer> donnees = new ArrayList<>();
        String ligne;
        String[] lesElements;
        int idTuile, nombreColonnes = -1, nombreLignes = 0;

        Scanner fichier = new Scanner(new File(cheminFichier));
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

    /**
     * methode permettant de remplir le tableau des tuiles chargées
     * @param donnees
     * @param nombreLignes
     * @param nombreColonnes
     * @return
     * @throws IndexOutOfBoundsException
     */
    private int[][] remplissageTableau(List<Integer> donnees, int nombreLignes, int nombreColonnes)
            throws IndexOutOfBoundsException {
        int[][] lesTuiles = new int[nombreLignes][nombreColonnes];
        for (int x = 0; x < nombreLignes; x++) {
            for (int y = 0; y < nombreColonnes; y++) {
                lesTuiles[x][y] = donnees.get(x * nombreColonnes + y);
            }
        }
        return lesTuiles;
    }
}
