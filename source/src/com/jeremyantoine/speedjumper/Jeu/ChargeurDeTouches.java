package com.jeremyantoine.speedjumper.Jeu;

import javafx.scene.input.KeyCode;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public abstract class ChargeurDeTouches {
    private static final String CARACTERES_IGNORES ="#.*";
    private static final String DELIMITEUR = ":";
    private Map<String, String> lesTouches = new HashMap<>();

    public Map<String, String> charge(URL ressource) {
        try (BufferedReader tampon = new BufferedReader(new FileReader(ressource.toString().replace("file:", "")))) {
            StringBuilder accumulateurDeChaine = new StringBuilder();
            String ligne, touche;
            while ((ligne = tampon.readLine()) != null) {
                ligne = ligne.replaceAll(CARACTERES_IGNORES, "");
                accumulateurDeChaine.append(ligne);
                accumulateurDeChaine.append("\n");
            }
            String chaine = accumulateurDeChaine.toString().trim().toUpperCase(Locale.ROOT);
            chaine = chaine.replaceAll("\n", ":");
            ajouterTouches(chaine);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return lesTouches;
    }

    private void ajouterTouches(String donnees) throws InvalidFormatException {
        StringTokenizer delimiteur = new StringTokenizer(donnees, DELIMITEUR);
        int nombreJetons = delimiteur.countTokens();
        if (nombreJetons % 2 != 0) {
            throw new InvalidFormatException("Le nombre de touches spécifiées dans le fichier"
                    + " de configuration est incorrect.");
        }

        while (delimiteur.hasMoreTokens()) {
            lesTouches.put(delimiteur.nextToken().trim(), delimiteur.nextToken().trim());
        }
    }
}
