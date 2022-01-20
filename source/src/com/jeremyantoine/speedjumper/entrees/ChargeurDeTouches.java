package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.utilitaire.InvalidFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Classe abstraite permettant de charger le contenu d'un fichier de touche dans une liste
 */
public abstract class ChargeurDeTouches {
    private static final String CARACTERES_IGNORES ="#.*";
    private static final String DELIMITEUR = ":";
    private Map<String, String> lesTouches = new HashMap<>();

    /**
     * Permet d'ouvrir le fichier et de le lire
     * @param ressource liens du fichier
     * @return
     */
    public Map<String, String> charge(String ressource) {
        try (BufferedReader tampon = new BufferedReader(new FileReader(ressource))) {
            StringBuilder accumulateurDeChaine = new StringBuilder();
            String ligne;
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

    /**
     * Ajoute la touche
     * @param donnees
     * @throws InvalidFormatException
     */
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
