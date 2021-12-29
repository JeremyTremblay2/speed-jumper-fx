package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.monde.Carte2D;
import com.jeremyantoine.speedjumper.monde.Tuile;
import com.jeremyantoine.speedjumper.utilitaire.InvalidFormatException;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestionnaireDeRessources {
    private List<String> lesCartesChemins = CollectionRessources.getLesCartes();
    private Map<String, Dimension> lesJeuxDeTuiles = CollectionRessources.getLesJeuxDeTuiles();
    private ChargeurDeCarteTiled chargeurDeCartes;
    private ChargeurDeJeuxDeTuiles chargeurDeTuiles;
    private List<Carte2D> lesCartes;
    private List<Tuile> lesTuiles;

    public GestionnaireDeRessources(ChargeurDeCarteTiled chargeurDeCartes, ChargeurDeJeuxDeTuiles chargeurDeTuiles) {
        this.chargeurDeCartes = chargeurDeCartes;
        this.chargeurDeTuiles = chargeurDeTuiles;
        lesCartes = new ArrayList<>();
        lesTuiles = new ArrayList<>();
    }

    public List<Carte2D> getLesCartes() {
        return lesCartes;
    }

    public List<Tuile> getLesTuiles() {
        return lesTuiles;
    }

    public void charge() throws Exception {
        chargeCartes();
        chargeTuiles();
    }

    private void chargeCartes() throws MalformedURLException, FileNotFoundException, ParseException,
            InvalidFormatException {
        Carte2D carte;
        for (String chemin : lesCartesChemins) {
            carte = chargeurDeCartes.charge(new URL(chemin));
            lesCartes.add(carte);
        }
    }

    private void chargeTuiles() throws MalformedURLException, FileNotFoundException, ParseException,
            InvalidFormatException {
        List<Tuile> tuiles = new ArrayList<>();
        for (Map.Entry<String, Dimension> paire : lesJeuxDeTuiles.entrySet()) {
            tuiles = chargeurDeTuiles.charge(new URL(paire.getKey()));
            lesTuiles.addAll(tuiles);
        }
    }
}
