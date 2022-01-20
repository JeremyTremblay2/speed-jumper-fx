package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Score;
import com.jeremyantoine.speedjumper.monde.Carte2D;
import com.jeremyantoine.speedjumper.monde.Tuile;
import com.jeremyantoine.speedjumper.utilitaire.InvalidFormatException;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de gerer les ressources non visuel
 */
public class GestionnaireDeRessources {
    private ChargeurDeCarteTiled chargeurDeCartes;
    private ChargeurDeJeuxDeTuiles chargeurDeTuiles;
    private ChargeurScore chargeurScore;

    private List<String> lesCartesChemins;
    private List<String> lesJeuxDeTuiles;
    private String cheminScores;

    private List<Carte2D> lesCartes;
    private List<Tuile> lesTuiles;
    private List<List<Score>> lesScores;

    /**
     * constructeur de la classe
     * @param chargeurDeCartes chargeur de la carte
     * @param chargeurDeTuiles chargeur des tuiles
     * @param chargeurScore chargeur du score
     */
    public GestionnaireDeRessources(ChargeurDeCarteTiled chargeurDeCartes, ChargeurDeJeuxDeTuiles chargeurDeTuiles,
                                    ChargeurScore chargeurScore) throws IllegalArgumentException {
        if (chargeurDeCartes == null || chargeurDeTuiles == null || chargeurScore == null) {
            throw new IllegalArgumentException("Les chargeurs fournis en paramètre du gestionnaire de ressources ne "
                    + "peuvent pas être null.");
        }
        this.chargeurDeCartes = chargeurDeCartes;
        this.chargeurDeTuiles = chargeurDeTuiles;
        this.chargeurScore = chargeurScore;

        lesCartes = new ArrayList<>();
        lesTuiles = new ArrayList<>();
        lesScores = new ArrayList<>();

        CollectionRessources ressources = CollectionRessources.getInstance();
        lesCartesChemins = ressources.getLesCartes();
        lesJeuxDeTuiles = ressources.getLesJeuxDeTuilesCollisions();
        cheminScores = ressources.getFichierScores();
    }

    /**
     * retourne la liste des cartes
     * @return
     */
    public List<Carte2D> getLesCartes() {
        return lesCartes;
    }

    /**
     * retourne la liste des tuiles
     * @return
     */
    public List<Tuile> getLesTuiles() {
        return lesTuiles;
    }

    /**
     * retourne la liste des scores
     * @return
     */
    public List<List<Score>> getLesScores() {
        return lesScores;
    }

    /**
     * charge les différents données
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws InvalidFormatException
     */
    public void charge() throws FileNotFoundException, ParseException, InvalidFormatException {
        lesTuiles = chargeTuiles();
        lesCartes = chargeCartes();
        lesScores = chargeScores();
    }

    /**
     * charge les cartes et les retourne dans une liste
     * @return
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws InvalidFormatException
     */
    private List<Carte2D> chargeCartes() throws FileNotFoundException, ParseException,
            InvalidFormatException {
        Carte2D carte;
        System.out.println(lesTuiles);
        for (String chemin : lesCartesChemins) {
            carte = chargeurDeCartes.charge(chemin, lesTuiles);
            lesCartes.add(carte);
        }
        return lesCartes;
    }

    /**
     * charge les tuiles et les retournes dans une liste de tuile
     * @return
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws InvalidFormatException
     */
    private List<Tuile> chargeTuiles() throws FileNotFoundException, ParseException,
            InvalidFormatException {
        List<Tuile> tuiles;
        for (String chemin : lesJeuxDeTuiles) {
            tuiles = chargeurDeTuiles.charge(chemin);
            lesTuiles.addAll(tuiles);
        }
        return lesTuiles;
    }

    /**
     * charge les score et les retournes dans une liste
     * @return
     */
    private List<List<Score>> chargeScores() {
        return chargeurScore.charge(cheminScores);
    }
}
