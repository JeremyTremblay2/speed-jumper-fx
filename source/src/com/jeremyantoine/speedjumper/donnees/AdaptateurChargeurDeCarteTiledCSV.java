package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.monde.Carte2D;
import com.jeremyantoine.speedjumper.monde.Tuile;
import com.jeremyantoine.speedjumper.utilitaire.InvalidFormatException;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

/**
 * Classe permettant d'adapter des tuiles en tableau de tuile
 */
public class AdaptateurChargeurDeCarteTiledCSV implements ChargeurDeCarteTiled {
    private static final ChargeurDeCarteTiledCSV chargeur = new ChargeurDeCarteTiledCSV();
    private String separateur;

    /**
     * constructeur de la classe
     * @param separateur
     */
    public AdaptateurChargeurDeCarteTiledCSV(String separateur) {
        this.separateur = separateur;
    }

    /**
     * Methode permettant de charger les tuiles et de créer une carte
     * @param cheminFichier
     * @param lesTuiles
     * @return
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws InvalidFormatException
     */
    @Override
    public Carte2D charge(String cheminFichier, List<Tuile> lesTuiles) throws FileNotFoundException, ParseException,
            InvalidFormatException {
        int[][] identifiantsTuiles = chargeur.charge(cheminFichier, separateur);
        Tuile[][] tuilesCartes = recupereCarte(identifiantsTuiles, lesTuiles);

        Dimension dimension = new Dimension(tuilesCartes[0][0].getDimension().getLargeur(),
                tuilesCartes[0][0].getDimension().getHauteur());
        return new Carte2D(tuilesCartes, dimension);
    }

    /**
     * methode de recuperer une carte.
     * @param identifiantsTuiles
     * @param lesTuiles
     * @return
     * @throws InvalidFormatException
     */
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
