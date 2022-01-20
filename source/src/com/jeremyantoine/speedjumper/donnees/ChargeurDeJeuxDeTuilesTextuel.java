package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.monde.Tuile;
import com.jeremyantoine.speedjumper.utilitaire.InvalidFormatException;

import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

public class ChargeurDeJeuxDeTuilesTextuel implements ChargeurDeJeuxDeTuiles {
    private static final String CARACTERES_IGNORES ="#.*";
    private static final String DELIMITEUR_ID = ":";
    private static final String DELIMITEUR_COORDONNEES = " ";
    private List<Tuile> lesTuiles;

    public ChargeurDeJeuxDeTuilesTextuel() {
        lesTuiles = new ArrayList<>();
    }

    @Override
    public List<Tuile> charge(String chemin) throws FileNotFoundException, ParseException, InvalidFormatException {
        lesTuiles.add(new Tuile(null, new Dimension(64, 64)));
        for (int i = 0; i < 239; i++) {
            lesTuiles.add(new Tuile(new Rectangle(new Position2D(0, 0), new Dimension(64, 64)), new Dimension(64, 64)));
        }

        /*
        try (BufferedReader tampon = new BufferedReader(new FileReader(chemin.toString().replace("file:", "")))) {
            StringBuilder accumulateurDeChaine = new StringBuilder();
            String ligne;
            while ((ligne = tampon.readLine()) != null) {
                ligne = ligne.replaceAll(CARACTERES_IGNORES, "");
                ligne = ligne.replaceAll(DELIMITEUR_COORDONNEES + DELIMITEUR_COORDONNEES, DELIMITEUR_COORDONNEES);
                accumulateurDeChaine.append(ligne);
                accumulateurDeChaine.append("\n");
            }
            String chaine = accumulateurDeChaine.toString().trim();
            ajouterTuiles(chaine, chemin.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        */
        return lesTuiles;
    }

    /*
    private void ajouterTuiles(String chaine, String chemin) throws InvalidFormatException {
        StringTokenizer delimiteur = new StringTokenizer(chaine, DELIMITEUR_ID);
        int nombreJetons = delimiteur.countTokens();
        if (nombreJetons % 2 != 0) {
            throw new InvalidFormatException("Il manque des données dans le jeu de tuiles du fichier " + chemin);
        }

        while (delimiteur.hasMoreTokens()) {
            //lesTouches.put(delimiteur.nextToken().trim(), delimiteur.nextToken().trim());
        }
    }
    */
}
