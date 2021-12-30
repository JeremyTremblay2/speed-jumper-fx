package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.monde.Carte2D;
import com.jeremyantoine.speedjumper.monde.Tuile;
import com.jeremyantoine.speedjumper.utilitaire.InvalidFormatException;

import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class AdaptateurChargeurDeCarteTiledCSV implements ChargeurDeCarteTiled {
    private static final ChargeurDeCarteTiledCSV chargeur = new ChargeurDeCarteTiledCSV();
    private String separateur;

    public AdaptateurChargeurDeCarteTiledCSV(String separateur) {
        this.separateur = separateur;
    }

    @Override
    public Carte2D charge(String cheminFichier, List<Tuile> lesTuiles) throws FileNotFoundException, ParseException,
            InvalidFormatException {
        int[][] identifiantsTuiles = chargeur.charge(cheminFichier, separateur);
        Tuile[][] tuilesCartes = recupereCarte(identifiantsTuiles, lesTuiles);

        Dimension dimension = new Dimension(tuilesCartes[0][0].getDimension().getLargeur(),
                tuilesCartes[0][0].getDimension().getHauteur());
        return new Carte2D(tuilesCartes, dimension);
    }

    private Tuile[][] recupereCarte(int[][] identifiantsTuiles, List<Tuile> lesTuiles) throws InvalidFormatException {
        Tuile tuileCourante;
        int id;
        Tuile[][] tuilesCartes = new Tuile[identifiantsTuiles.length][identifiantsTuiles[0].length];

        for (int x = 0; x < identifiantsTuiles.length; x++) {
            for (int y = 0; y < identifiantsTuiles[x].length; y++) {
                id = identifiantsTuiles[x][y];
                if (id == -1) {
                    id = 0;
                }
                tuileCourante = lesTuiles.get(id);
                if (tuileCourante == null) {
                    throw new InvalidFormatException("Une tuile du fichier possède un ID qui n'est pas référencé dans la "
                            + "collection de tuiles. ID : " + id);
                }
                tuilesCartes[x][y] = tuileCourante;
            }
        }
        return tuilesCartes;
    }
}
